import { useHistory, useParams } from "react-router";
import { useEffect, useState } from "react";
import { Container, Spinner, Form, Jumbotron, Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import ContestPresentation from "./ContestPresentation";
import backend from "../../../backend";
import commonFunctions from "../../commons/functions";
import exifr from "exifr";
import * as selectors from "../../user/selectors";
import { useSelector } from 'react-redux';
import Errors from "../../commons/components/Errors";
import { useIntl } from "react-intl";

const Participate = () => {

    const { contestId, contestName } = useParams();
    const userName = useSelector(selectors.getUserName);
    const history = useHistory();
    const intl = useIntl();

    const [backendErrors, setBackendErrors] = useState(null);
    const [listaCategoriasMultiselect, setListaCategoriasMultiselect] = useState([]);
    const [idCategoria, setIdCategoria] = useState("");
    const [contestData, setContestData] = useState("");
    const [imagenJPG, setImagenJPG] = useState("");
    const [imagenRAW, setImagenRAW] = useState("");
    const [tituloFotografia, setTituloFotografia] = useState("");
    const [descripcionFotografia, setDescripcionFotografia] = useState("");
    const [cameraBrand, setCameraBrand] = useState("");
    const [cameraModel, setCameraModel] = useState("");
    const [focalDistance, setFocalDistance] = useState("");
    const [aperture, setAperture] = useState("");
    const [shutterSpeed, setShutterSpeed] = useState("");
    const [ISO, setISO] = useState("");
    const [resolution, setResolution] = useState("");
    const [rulesAcceptance, setRulesAcceptance] = useState(false);

    const getEXIFData = async () => {

        let imgb64 = `data:image/jpeg;base64,${imagenJPG}`;

        let exifData = await exifr.parse(imgb64);

        if (exifData !== undefined) {
            setCameraBrand(exifData.Make);
            setCameraModel(exifData.Model);
            setFocalDistance(exifData.FocalLength);
            setAperture(exifData.ApertureValue);
            setShutterSpeed(exifData.ShutterSpeedValue);
            setISO(exifData.ISO);
            setResolution(`${exifData.XResolution}x${exifData.YResolution}`);
        } else {
            setCameraBrand("");
            setCameraModel("");
            setFocalDistance("");
            setAperture("");
            setShutterSpeed("");
            setISO("");
            setResolution("");
        }


    }

    const handleSubmit = event => {

        event.preventDefault();

        if (!rulesAcceptance) {
            let msg = intl.formatMessage({ id: 'contest.participate.dataSection.RulesAcceptanceMsg' });
            setBackendErrors({ errorGlobal: msg });
            return;
        }

        backend.catalogService.participate(
            {
                aceptoLasNormas: rulesAcceptance,
                idConcurso: contestId,
                nombreUsuario: userName,
                datosJpg: imagenJPG,
                datosRaw: imagenRAW,
                tituloFotografia,
                descripcionFotografia,
                aperturaDiafragma: aperture,
                fabricanteCamara: cameraBrand,
                modeloCamara: cameraModel,
                distanciaFocal: focalDistance,
                velocidadObturacion: shutterSpeed,
                iso: ISO ? ISO : "100",
                resolucion: resolution,
                idCategoria
            },
            contestName,
            () => history.push("/"),
            errors => setBackendErrors(errors)
        )
    }

    useEffect(() => {
        backend.catalogService.getContestData(
            {
                contestId,
                contestName
            },
            result => setContestData(result)
        )
        // eslint-disable-next-line
    }, [])

    useEffect(() => {

        if (imagenJPG !== "") {
            getEXIFData();
        }
        // eslint-disable-next-line
    }, [imagenJPG])

    useEffect(() => {
        backend.catalogService.findCategoriesOfContest(
            {
                contestId
            },
            result => setListaCategoriasMultiselect(result));
        // eslint-disable-next-line
    }, [])

    // While backend responds
    if (contestData === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <Container className="centering">
            <ContestPresentation contestData={contestData} />
            <h4>
                <FormattedMessage id='contest.participate.Title' />
            </h4><br />

            <form>
                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
                {/*Foto y bases*/}
                <Jumbotron>
                    <h5>
                        <FormattedMessage id='contest.participate.photographySection.Title' /></h5><br />
                    <br />
                    {
                        contestData.formatoRequerido === "RAW" ?

                            null
                            :
                            <Form.Group className="mb-3" controlId="contestPhoto">
                                <Form.Label><FormattedMessage id='contest.participate.photographySection.JPG' /></Form.Label>
                                <Form.Control type="file" disabled={contestData.formatoRequerido === "RAW"} required={contestData.formatoRequerido !== "RAW"} accept="image/jpeg" onChange={e => commonFunctions.fileToBase64(e.target.files[0], setImagenJPG)} />
                                <Form.Text className="text-muted">
                                </Form.Text>
                            </Form.Group>
                    }
                    {
                        contestData.formatoRequerido === "JPG" ?

                            null
                            :

                            <Form.Group className="mb-3" controlId="contestRules">
                                <Form.Label><FormattedMessage id='contest.participate.photographySection.RAW' /></Form.Label>
                                <Form.Control type="file" disabled={contestData.formatoRequerido === "JPG"} required={contestData.formatoRequerido !== "JPG"} accept=".braw, .dng, .crw, .cr2, orf, nef, arw, rw2, raw, dcr, pef" onChange={e => commonFunctions.fileToBase64(e.target.files[0], setImagenRAW)} />
                                <Form.Text className="text-muted">
                                </Form.Text>
                            </Form.Group>

                    }

                </Jumbotron>
                {/*Nombre del concurso*/}
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.PhotoTitle' /></Form.Label>
                    <Form.Control value={tituloFotografia} maxLength="50" onChange={e => setTituloFotografia(e.target.value)} required={true} />
                </Form.Group>
                {/*Nombre del concurso*/}
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.PhotoDesc' /></Form.Label>
                    <Form.Control as="textarea" value={descripcionFotografia} maxLength="200" onChange={e => setDescripcionFotografia(e.target.value)} required={true} />
                </Form.Group>
                <br />
                <h5>
                    <FormattedMessage id='contest.participate.dataSection.ExifData' /><br />
                </h5>
                <br />
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.CameraBrand' /></Form.Label>
                    <Form.Control value={cameraBrand} maxLength="50" onChange={e => setCameraBrand(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.CameraModel' /></Form.Label>
                    <Form.Control value={cameraModel} maxLength="50" onChange={e => setCameraModel(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.FocalDistance' /></Form.Label>
                    <Form.Control value={focalDistance} maxLength="50" onChange={e => setFocalDistance(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.Aperture' /></Form.Label>
                    <Form.Control value={aperture} maxLength="50" onChange={e => setAperture(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.ShutterSpeed' /></Form.Label>
                    <Form.Control value={shutterSpeed} maxLength="50" onChange={e => setShutterSpeed(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.ISO' /></Form.Label>
                    <Form.Control value={ISO} maxLength="200" onChange={e => setISO(e.target.value)} required={true} />
                </Form.Group>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.participate.dataSection.Resolution' /></Form.Label>
                    <Form.Control value={resolution} maxLength="50" onChange={e => setResolution(e.target.value)} required={true} />
                </Form.Group>
                <br />
                {/* Selector de categoria unica */}
                <select id="categoryId" className="custom-select my-1 mr-sm-2"
                    required value={idCategoria} onChange={e => setIdCategoria(e.target.value)}>

                    <FormattedMessage id='catalog.CategorySelector.allCategories'>
                        {message => (<option value="">{message}</option>)}
                    </FormattedMessage>

                    {listaCategoriasMultiselect && listaCategoriasMultiselect.map(category =>
                        <option key={category.idCategoria} value={category.idCategoria}>{category.nombreCategoria}</option>
                    )}
                </select>
                <br />
                <br />
                <a
                    className="centeredLink"
                    href={`data:application/pdf;base64,${contestData.basesConcurso}`}
                    download={`${contestData.nombreConcurso}-RULES.pdf`}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z" />
                    </svg>
                    &nbsp;
                    <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.DownloadRules' />
                </a>
                <br />
                <br />
                <label><FormattedMessage id='contest.participate.dataSection.RulesAcceptance' /></label><br />
                <input required type="checkbox" name="descripcionRequerida" checked={rulesAcceptance} onChange={e => setRulesAcceptance(e.target.checked)} />&nbsp;
                <br />
                <br />
                <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
                <br />
                <br />
                <Button
                    variant="success"
                    type="submit"
                    onClick={e => handleSubmit(e)}>
                    <FormattedMessage id='contest.participate.Title' />
                </Button>
                <br />
                <br />
            </form>
        </Container>
    );

}

export default Participate;