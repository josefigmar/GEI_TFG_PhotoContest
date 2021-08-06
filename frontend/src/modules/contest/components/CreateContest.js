import { Button, Container, Form, Jumbotron } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";
import { useEffect, useState } from "react";
import { useHistory, useParams } from "react-router";
import commonFunctions from "../../commons/functions";
import { Multiselect } from 'multiselect-react-dropdown';
import Errors from "../../commons/components/Errors";
import backend from "../../../backend";
import CreateCategory from "./CreateCategory";

const CreateContest = () => {

    const intl = useIntl();
    const { contestId, contestName } = useParams();
    const history = useHistory();
    const [listaUsuariosMultiselect, setListaUsuariosMultiselect] = useState(null);
    const [listaOrganizadoresMultiselect, setListaOrganizadoresMultiselect] = useState([]);
    const [listaParticipantesMultiselect, setListaParticipantesMultiselect] = useState([]);
    const [listaJuradoMultiselect, setListaJuradoMultiselect] = useState([]);
    const [backendErrors, setBackendErrors] = useState('');
    const [nombreConcurso, setNombreConcurso] = useState('');
    const [descripcionConcurso, setDescripcionConcurso] = useState('');
    const [fotoConcurso, setFotoConcurso] = useState('');
    const [basesConcurso, setBasesConcurso] = useState('');
    const [categoriaUnica, setCategoriaUnica] = useState(true);
    const [idCategoria, setIdCategoria] = useState("");
    const [listaCategoriasMultiselect, setListaCategoriasMultiselect] = useState([]);
    const [listaCategorias, setListaCategorias] = useState([]);
    const [miembrosDeLaOrganizacion, setMiembrosDeLaOrganizacion] = useState([]);
    const [participantes, setParticipantes] = useState([]);
    const [miembrosDelJurado, setMiembrosDelJurado] = useState([]);
    const [fechaInicio, setFechaInicio] = useState('');
    const [fechaInicioVotacion, setFechaInicioVotacion] = useState('');
    const [participanteAbierto, setParticipanteAbierto] = useState(true);
    const [numeroMaximoFotografias, setNumeroMaximoFotografias] = useState("");
    const [numeroMaximoFotografiasParticipante, setNumeroMaximoFotografiasParticipante] = useState("");
    const [formatoRequerido, setFormatoRequerido] = useState("");
    const [tituloRequerido, setTituloRequerido] = useState("");
    const [descripcionRequerida, setDescripcionRequerida] = useState("");
    const [datosExifRequeridos, setDatosExifRequeridos] = useState("");
    const [localizacionRequerida, setLocalizacionRequerida] = useState("");
    const [ocultarFotosHastaVotacion, setOcultarFotosHastaVotacion] = useState("");
    const [ocultarResultadosHastaFinal, setOcultarResultadosHastaFinal] = useState("");
    const [activarModeracion, setActivarModeracion] = useState("");
    const [tipoVotante, setTipoVotante] = useState("");
    const [metodoVoto, setMetodoVoto] = useState("");
    const [descripcionVotacionJurado, setDescripcionVotacionJurado] = useState("");
    const [fechaLimiteVotacion, setFechaLimiteVotacion] = useState("");
    const [numeroMaximoVotosPorUsuario, setNumeroMaximoVotosPorUsuario] = useState("");
    const [numeroMaximoDeFotografiasGanadoras, setNumeroMaximoDeFotografiasGanadoras] = useState("");
    const [triggerCategoryUpdate, setTriggerCategoryUpdate] = useState([]);
    let form;

    const handleSubmit = (event) => {
        event.preventDefault();

        if (form.checkValidity()) {
            backend.catalogService.createContest(
                {
                    idConcurso: contestId,
                    nombreConcurso,
                    descripcionConcurso,
                    fotoConcurso,
                    basesConcurso,
                    categoriaUnica,
                    listaCategorias,
                    miembrosDeLaOrganizacion,
                    participantes,
                    miembrosDelJurado,
                    fechaInicio,
                    fechaInicioVotacion,
                    idCategoria,
                    participanteAbierto,
                    numeroMaximoFotografias,
                    numeroMaximoFotografiasParticipante,
                    formatoRequerido,
                    tituloRequerido,
                    descripcionRequerida,
                    datosExifRequeridos,
                    localizacionRequerida,
                    ocultarFotosHastaVotacion,
                    ocultarResultadosHastaFinal,
                    activarModeracion,
                    tipoVotante,
                    metodoVoto,
                    descripcionVotacionJurado,
                    fechaLimiteVotacion,
                    numeroMaximoVotosPorUsuario,
                    numeroMaximoDeFotografiasGanadoras
                },
                "josefigueirasm",
                () => history.push("/"),
                errors => setBackendErrors(errors)


            )
        } else {
            form.classList.add('was-validated');
        }
    }

    const setContestData = result => {

        setNombreConcurso(result.nombreConcurso)
        setDescripcionConcurso(result.descripcionConcurso)
        setFotoConcurso(result.fotoConcurso)
        setBasesConcurso(result.basesConcurso)
        setCategoriaUnica(result.categoriaUnica)
        setListaCategorias(result.listaCategorias)
        setMiembrosDeLaOrganizacion(result.miembrosDeLaOrganizacion)
        setParticipantes(result.participantes)
        setMiembrosDelJurado(result.miembrosDelJurado)
        setFechaInicio(result.fechaInicio)
        setFechaInicioVotacion(result.fechaInicioVotacion)
        setIdCategoria(result.idCategoria)
        setParticipanteAbierto(result.participanteAbierto)
        setNumeroMaximoFotografias(result.numeroMaximoFotografias)
        setNumeroMaximoFotografiasParticipante(result.numeroMaximoFotografiasParticipante)
        setFormatoRequerido(result.formatoRequerido)
        setTituloRequerido(result.tituloRequerido)
        setDescripcionRequerida(result.descripcionRequerida)
        setDatosExifRequeridos(result.datosExifRequeridos)
        setLocalizacionRequerida(result.localizacionRequerida)
        setOcultarFotosHastaVotacion(result.ocultarFotosHastaVotacion)
        setOcultarResultadosHastaFinal(result.ocultarResultadosHastaFinal)
        setActivarModeracion(result.activarModeracion)
        setTipoVotante(result.tipoVotante)
        setMetodoVoto(result.metodoVoto)
        setDescripcionVotacionJurado(result.descripcionVotacionJurado)
        setFechaLimiteVotacion(result.fechaLimiteVotacion)
        setNumeroMaximoVotosPorUsuario(result.numeroMaximoVotosPorUsuario)
        setNumeroMaximoDeFotografiasGanadoras(result.numeroMaximoDeFotografiasGanadoras)
    }

    const removeElementsFromTwoLists = (list, collection1, collection2) => {
        let auxList = list.filter(e => !collection1.includes(e));
        auxList = auxList.filter(e => !collection2.includes(e));
        return auxList;
    }

    useEffect(() => {
        // This page is reused for editing a contest. The flag is contestId, if it
        // has a value, it is because the user is trying to edit a contest, not
        // to create it.
        if (contestId !== undefined) {
            backend.catalogService.getContestData(
                {
                    contestId,
                    contestName
                },
                result => setContestData(result)
            );
        }
        // eslint-disable-next-line
    }, [])

    useEffect(() => {

        // Primera carga de la página, la lista de usuarios tiene el valor 0
        if (listaUsuariosMultiselect === null) {
            backend.userService.findUserNames(result => setListaUsuariosMultiselect(result));
            // Se setea al usuario que está creando el concurso como organizador. Esto puede ser cambiado en el futuro.
            setMiembrosDeLaOrganizacion(["josefigueirasm"])
        } else {

            // Un organizador no puede ni participar ni ser jurado
            setListaOrganizadoresMultiselect(removeElementsFromTwoLists(listaUsuariosMultiselect, participantes, miembrosDelJurado));
            // Un participante no puede ser organizador ni jurado
            setListaParticipantesMultiselect(removeElementsFromTwoLists(listaUsuariosMultiselect, miembrosDeLaOrganizacion, miembrosDelJurado));
            // Un juez no puede ni participar ni ser organizador a no ser que se seleccione expresamente
            // la opción de que los jueces son los participantes
            setListaJuradoMultiselect(removeElementsFromTwoLists(listaUsuariosMultiselect, miembrosDeLaOrganizacion, participantes));
        }
        // eslint-disable-next-line
    }, [listaUsuariosMultiselect, miembrosDeLaOrganizacion, participantes, miembrosDelJurado]);

    useEffect(() => {
        backend.catalogService.findCategories(result => setListaCategoriasMultiselect(result));
    }, [triggerCategoryUpdate])

    return (

        <Container>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <br />
            {
                // This page is valid for create and for edit a contest
                contestId !== undefined ?
                    <h2><FormattedMessage id="contest.EditContest.Title" /></h2>
                    :
                    <h2><FormattedMessage id="contest.CreateContest.Title" /></h2>
            }

            <hr />
            <h4><FormattedMessage id="contest.CreateContest.GeneralInfoSection" /></h4>
            <form ref={node => form = node} onSubmit={e => handleSubmit(e)} noValidate={false}>
                {/*Nombre del concurso*/}
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.CreateContest.NombreConcurso' /></Form.Label>
                    <Form.Control value={nombreConcurso} maxLength="50" onChange={e => setNombreConcurso(e.target.value)} required={true} />
                </Form.Group>
                {/*Descripción del concurso*/}
                <Form.Group className="mb-3" controlId="DescripcionConcurso">
                    <Form.Label><FormattedMessage id='contest.CreateContest.DescripcionConcurso' /></Form.Label>
                    <Form.Control as="textarea" maxLength="500" value={descripcionConcurso} onChange={e => setDescripcionConcurso(e.target.value)} required />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                {/*Foto y bases*/}
                <Jumbotron>
                    <h5><FormattedMessage id='contest.CreateContest.Foto&BasesTitle' /></h5><br />
                    <h6><FormattedMessage id='contest.CreateContest.Foto&BasesDesc' /></h6>
                    <br />
                    <Form.Group className="mb-3" controlId="contestPhoto">
                        <Form.Label><FormattedMessage id='contest.CreateContest.SubirFoto' /></Form.Label>
                        <Form.Control type="file" required={contestId === undefined} accept="image/jpeg, image/png" onChange={e => commonFunctions.fileToBase64(e.target.files[0], setFotoConcurso)} />
                        <Form.Text className="text-muted">
                        </Form.Text>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="contestRules">
                        <Form.Label><FormattedMessage id='contest.CreateContest.SubirBases' /></Form.Label>
                        <Form.Control type="file" accept="application/pdf" required={contestId === undefined} onChange={e => commonFunctions.fileToBase64(e.target.files[0], setBasesConcurso)} />
                        <Form.Text className="text-muted">
                        </Form.Text>
                    </Form.Group>
                </Jumbotron>

                <Form.Group className="mb-3" controlId="fechaInicio">
                    <Form.Label><FormattedMessage id='contest.CreateContest.FechaInicio' /></Form.Label>
                    <Form.Control type="datetime-local" step="0" required value={fechaInicio} onChange={e => setFechaInicio(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="fechaInicioVotacion">
                    <Form.Label><FormattedMessage id='contest.CreateContest.FechaLimiteSubida' /></Form.Label>
                    <Form.Control type="datetime-local" min={fechaInicio} required value={fechaInicioVotacion} onChange={e => setFechaInicioVotacion(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <input type="checkbox" checked={categoriaUnica} onChange={e => setCategoriaUnica(e.target.checked)} />
                &nbsp;
                <label><FormattedMessage id='contest.CreateContest.UniqueCategory' /></label>
                <br />
                <CreateCategory onNew={(a) => setTriggerCategoryUpdate(a)} />
                {/* Selector de categoria unica */}
                <select id="categoryId" className="custom-select my-1 mr-sm-2"
                    required={categoriaUnica} value={idCategoria} onChange={e => setIdCategoria(e.target.value)} disabled={!categoriaUnica} >

                    <FormattedMessage id='catalog.CategorySelector.allCategories'>
                        {message => (<option value="">{message}</option>)}
                    </FormattedMessage>

                    {listaCategoriasMultiselect && listaCategoriasMultiselect.map(category =>
                        <option key={category.idCategoria} value={category.idCategoria}>{category.nombreCategoria}</option>
                    )}

                </select>

                <br /><br />
                <div hidden={categoriaUnica}>
                    <Multiselect placeholder={intl.formatMessage({ id: 'contest.CreateContest.CategoryList' })} options={listaCategoriasMultiselect} displayValue="nombreCategoria" onSelect={() => setListaCategorias()} />
                </div>

                <br /><hr />
                {/*Seccion de Miembros de la organización*/}
                <h4><FormattedMessage id="contest.CreateContest.OrganizationMembersSection" /></h4><br />
                <h6><FormattedMessage id='contest.CreateContest.OrganizationMembersSection.Desc' /></h6>
                <br />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'contest.CreateContest.StaffList' })}
                    isObject={false}
                    selectedValues={[`josefigueirasm`]}
                    options={listaOrganizadoresMultiselect}
                    onSelect={selectedList => setMiembrosDeLaOrganizacion(Array.from(selectedList))}
                    onRemove={selectedList => setMiembrosDeLaOrganizacion(Array.from(selectedList))}
                    showArrow="true" />
                <br /><br /><hr />

                {/*Seccion de participantes*/}
                <h4><FormattedMessage id='contest.CreateContest.ContendersSection' /></h4>
                <br />
                <h6><FormattedMessage id='contest.CreateContest.ParticipantTypeQuestion' /></h6>
                <input type="radio" name="tipoParticipante" checked={participanteAbierto} onChange={e => setParticipanteAbierto(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.ParticipantTypeOpen' /></label>&emsp;
                <input type="radio" name="tipoParticipante" checked={!participanteAbierto} onChange={e => setParticipanteAbierto(false)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.ParticipantTypeRestricted' /></label><br /><br />
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'contest.CreateContest.ParticipantList' })}
                    options={listaParticipantesMultiselect}
                    isObject={false}
                    onSelect={selectedList => setParticipantes(Array.from(selectedList))}
                    onRemove={selectedList => setParticipantes(Array.from(selectedList))}
                    showArrow="true"
                    disable={participanteAbierto} />
                <br /><br /><hr />

                {/*Seccion de fotografias*/}
                <h4><FormattedMessage id='contest.CreateContest.Photographies' /></h4><br />
                <h6><FormattedMessage id='contest.CreateContest.MaxPhotographies.Desc' /></h6>
                <label><FormattedMessage id='contest.CreateContest.MaxPhotographies' /></label>:&nbsp;
                <input type="number" value={numeroMaximoFotografias} min="2" max="200" required onChange={e => setNumeroMaximoFotografias(e.target.value)} /><br />
                <h6><FormattedMessage id='contest.CreateContest.MaxPhotographiesPerUser.Desc' /></h6>
                <label><FormattedMessage id='contest.CreateContest.MaxPhotographiesPerUser' /></label>:&nbsp;
                <input type="number" value={numeroMaximoFotografiasParticipante} min="1" max="3" required onChange={e => setNumeroMaximoFotografiasParticipante(e.target.value)} /><br /><br />

                <h6><FormattedMessage id='contest.CreateContest.FormatQuestion' /></h6>
                <input type="radio" name="tipoFormato" checked={formatoRequerido === "JPG"} value="JPG" required onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatJPG' /></label>&emsp;
                <input type="radio" name="tipoFormato" checked={formatoRequerido === "RAW"} value="RAW" onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatRAW' /></label>&emsp;
                <input type="radio" name="tipoFormato" checked={formatoRequerido === "JPG_Y_RAW"} value="JPG_Y_RAW" onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatJPG+RAW' /></label><br /><br />

                <h6><FormattedMessage id='contest.CreateContest.Configurables' /></h6><br />
                <input type="checkbox" name="tituloRequerido" checked={tituloRequerido} onChange={e => setTituloRequerido(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.RequiredTitle' /></label><br />
                <input type="checkbox" name="descripcionRequerida" checked={descripcionRequerida} onChange={e => setDescripcionRequerida(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.RequiredDescription' /></label><br />
                <input type="checkbox" name="datosExifRequeridos" checked={datosExifRequeridos} onChange={e => setDatosExifRequeridos(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.RequiredExifData' /></label><br />
                <input type="checkbox" name="localizacionRequerida" checked={localizacionRequerida} onChange={e => setLocalizacionRequerida(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.LocalizationRequired' /></label><br />
                <input type="checkbox" name="ocultarFotosVotacion" checked={ocultarFotosHastaVotacion} onChange={e => setOcultarFotosHastaVotacion(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.HidePhotosUntilVoting' /></label><br />
                <input type="checkbox" name="ocultarResultadosFinal" checked={ocultarResultadosHastaFinal} onChange={e => setOcultarResultadosHastaFinal(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.HideResultsUntilEnd' /></label><br />
                <input type="checkbox" name="activarModeracion" checked={activarModeracion} onChange={e => setActivarModeracion(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.ActiveModeration' /></label>

                <br /><br /><hr />
                {/* Proceso de votación */}
                <h4><FormattedMessage id='contest.CreateContest.VotingSection' /></h4><br />

                <h6><FormattedMessage id='contest.CreateContest.VotingTypeQuestion' /></h6>
                <input type="radio" name="tipoVotante" checked={tipoVotante === "CUALQUIERA"} value="CUALQUIERA" required onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeEveryone' /></label>&emsp;
                <input type="radio" name="tipoVotante" checked={tipoVotante === "JURADO"} value="JURADO" onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeJury' /></label>&emsp;
                <input type="radio" name="tipoVotante" checked={tipoVotante === "PARTICIPANTE"} value="PARTICIPANTE" onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeContenders' /></label><br /><br />

                <Form.Group className="mb-3" controlId="DesripcionVotacionJurado">
                    <Form.Label><FormattedMessage id='contest.CreateContest.VotingDescription' /></Form.Label>
                    <Form.Control as="textarea" required={tipoVotante === "JURADO" ? true : false} disabled={tipoVotante === "JURADO" ? false : true} value={descripcionVotacionJurado} onChange={e => setDescripcionVotacionJurado(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Multiselect
                    placeholder={intl.formatMessage({ id: 'contest.CreateContest.JuryList' })}
                    options={listaJuradoMultiselect}
                    isObject={false}
                    onSelect={selectedList => setMiembrosDelJurado(Array.from(selectedList))}
                    onRemove={selectedList => setMiembrosDelJurado(Array.from(selectedList))}
                    showArrow="true"
                    disable={tipoVotante === "JURADO" ? false : true} /><br /><br />
                <labe><FormattedMessage id='contest.CreateContest.VotingEndDate' /></labe>:&nbsp;
                <input type="datetime-local" value={fechaLimiteVotacion} min={fechaInicioVotacion} required onChange={e => setFechaLimiteVotacion(e.target.value)} /><br /><br />
                <label><FormattedMessage id='contest.CreateContest.VotingModel' /></label>&emsp;
                <select name="metodoVoto" selec required onChange={e => setMetodoVoto(e.target.value)}>
                    <option value=""></option>
                    <option selected={metodoVoto === "SIMPLE"} value="SIMPLE">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelSimple' })}</option>
                    <option selected={metodoVoto === "CINCO_ESTRELLAS"} value="CINCO_ESTRELLAS">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelFiveStars' })}</option>
                    <option selected={metodoVoto === "EUROVISION"} value="EUROVISION">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelEurovision' })}</option>
                </select><br /><br />

                <h6><FormattedMessage id='contest.CreateContest.MaxVotesPerUser.Desc' /></h6>
                <label><FormattedMessage id='contest.CreateContest.MaxVotesPerUser' /></label>:&nbsp;
                <input type="number" required={metodoVoto === "SIMPLE" ? true : false} disabled={metodoVoto === "SIMPLE" ? false : true} value={numeroMaximoVotosPorUsuario} min="1" max="5" onChange={e => setNumeroMaximoVotosPorUsuario(e.target.value)} /><br />
                <h6><FormattedMessage id='contest.CreateContest.NumberOfWiningPhotos.Desc' /></h6>
                <label><FormattedMessage id='contest.CreateContest.NumberOfWiningPhotos' /></label>:&nbsp;
                <input type="number" value={numeroMaximoDeFotografiasGanadoras} min="1" max="10" required onChange={e => setNumeroMaximoDeFotografiasGanadoras(e.target.value)} /><br /><br />
                <div className="d-flex justify-content-center">
                    <Button variant="success" type="submit" onClick={e => handleSubmit(e)}>

                        {
                            // This page is valid for create and for edit a contest
                            contestId !== undefined ?
                                <FormattedMessage id='contest.EditContest.SaveChangues' />
                                :
                                <FormattedMessage id='app.RedirectHome.ButtonCreateContest' />

                        }
                    </Button>
                </div>

            </form>
            <br /><br /><br /><br /><br /><br /><br />
        </Container>
    );
}

export default CreateContest;