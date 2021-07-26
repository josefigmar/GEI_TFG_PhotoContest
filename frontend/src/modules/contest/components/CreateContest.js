import { Button, Container, Form, Jumbotron } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";
import { useState } from "react";
import CategorySelector from "./CategorySelector";
import commonFunctions from "../../commons/functions";
import { Multiselect } from 'multiselect-react-dropdown';

const CreateContest = () => {

    const intl = useIntl();
    const [nombreConcurso, setNombreConcurso] = useState('');
    const [descripcionConcurso, setDescripcionConcurso] = useState('');
    const [fotoConcurso, setFotoConcurso] = useState('');
    const [basesConcurso, setBasesConcurso] = useState('');
    const [categoriaUnica, setCategoriaUnica] = useState(true);
    const [listaCategorias, setListaCategorias] = useState('');
    const [fechaInicio, setFechaInicio] = useState('');
    const [fechaInicioVotacion, setFechaInicioVotacion] = useState('');
    const [nombreNuevaCategoria, setnombreNuevaCategoria] = useState("");
    const [idCategoria, setIdCategoria] = useState("");
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


    const handleSubmit = (event) => {
        event.preventDefault();

    }

    return (

        <Container>
            <h2><FormattedMessage id="contest.CreateContest.Title" /></h2>
            <hr />
            <h4><FormattedMessage id="contest.CreateContest.GeneralInfoSection" /></h4>
            <Form onSubmit={e => handleSubmit(e)}>
                <Form.Group className="mb-3" controlId="NombreConcurso">
                    <Form.Label><FormattedMessage id='contest.CreateContest.NombreConcurso' /></Form.Label>
                    <Form.Control value={nombreConcurso} onChange={e => setNombreConcurso(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="DescripcionConcurso">
                    <Form.Label><FormattedMessage id='contest.CreateContest.DescripcionConcurso' /></Form.Label>
                    <Form.Control as="textarea" type="placeholder" value={descripcionConcurso} onChange={e => setDescripcionConcurso(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Jumbotron>
                    <h5><FormattedMessage id='contest.CreateContest.Foto&BasesTitle' /></h5>
                    <br />
                    <Form.Group className="mb-3" controlId="formOldPassword">
                        <Form.Label><FormattedMessage id='contest.CreateContest.SubirFoto' /></Form.Label>
                        <Form.Control type="file" value={fotoConcurso} onChange={e => setFotoConcurso(commonFunctions.imgToBase64(e.target.value))} />
                        <Form.Text className="text-muted">
                        </Form.Text>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="formOldPassword">
                        <Form.Label><FormattedMessage id='contest.CreateContest.SubirBases' /></Form.Label>
                        <Form.Control type="file" accept="application/pdf" value={basesConcurso} onChange={e => setBasesConcurso(e.target.value)} />
                        <Form.Text className="text-muted">
                        </Form.Text>
                    </Form.Group>
                </Jumbotron>
                <Form.Group className="mb-3" controlId="formOldPassword">
                    <Form.Label><FormattedMessage id='contest.CreateContest.FechaInicio' /></Form.Label>
                    <Form.Control type="datetime-local" value={fechaInicio} onChange={e => setFechaInicio(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formOldPassword">
                    <Form.Label><FormattedMessage id='contest.CreateContest.FechaLimiteSubida' /></Form.Label>
                    <Form.Control type="datetime-local" value={fechaInicioVotacion} onChange={e => setFechaInicioVotacion(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <input type="checkbox" checked={categoriaUnica} onChange={e => setCategoriaUnica(e.target.checked)} />
                &nbsp;
                <label><FormattedMessage id='contest.CreateContest.UniqueCategory' /></label>
                <br />
                <label><FormattedMessage id='contest.CreateContest.CreateCategory' /></label>:&nbsp;
                <input className="mb-3" type="text" value={nombreNuevaCategoria} onChange={e => setnombreNuevaCategoria(e.target.value)}></input>&nbsp;
                <Button variant="success"><FormattedMessage id='contest.CreateContest.CreateCategoryConfirm' /></Button>
                <CategorySelector id="categoryId" className="custom-select my-1 mr-sm-2"
                    value={idCategoria} disabled={!categoriaUnica} />
                <br /><br />
                <div hidden={categoriaUnica}>
                    <Multiselect placeholder={intl.formatMessage({ id: 'contest.CreateContest.CategoryList' })} options={[{ name: "Bodegón", id: 1 }, { name: "Naturaleza", id: 2 }]} displayValue="name" onSelect={() => setListaCategorias()} />
                </div>

                <br /><br />
                {/*Seccion de Miembros de la organización*/}
                <h4><FormattedMessage id="contest.CreateContest.OrganizationMembersSection" /></h4>
                <br />
                <Multiselect placeholder={intl.formatMessage({ id: 'contest.CreateContest.StaffList' })} options={[{ name: "@Julio", id: 1 }, { name: "@Alex", id: 2 }]}
                    displayValue="name" onSelect={() => setListaCategorias()} showArrow="true" />
                <br /><br />

                {/*Seccion de participantes*/}
                <h4><FormattedMessage id='contest.CreateContest.ContendersSection' /></h4>
                <br />
                <h6><FormattedMessage id='contest.CreateContest.ParticipantTypeQuestion' /></h6>
                <input type="radio" name="tipoParticipante" checked={participanteAbierto} onChange={e => setParticipanteAbierto(e.target.checked)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.ParticipantTypeOpen' /></label>&emsp;
                <input type="radio" name="tipoParticipante" onChange={e => setParticipanteAbierto(false)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.ParticipantTypeRestricted' /></label><br /><br />
                <Multiselect placeholder={intl.formatMessage({ id: 'contest.CreateContest.ParticipantList' })} options={[{ name: "@Julio", id: 1 }, { name: "@Alex", id: 2 }]}
                    displayValue="name" onSelect={() => setListaCategorias()} showArrow="true" disable={participanteAbierto} />
                <br /><br />
                {/*Seccion de fotografias*/}
                <h4><FormattedMessage id='contest.CreateContest.Photographies' /></h4><br />
                <label><FormattedMessage id='contest.CreateContest.MaxPhotographies' /></label>:&nbsp;
                <input type="number" value={numeroMaximoFotografias} min="2" max="200" onChange={e => setNumeroMaximoFotografias(e.target.value)} /><br />
                <label><FormattedMessage id='contest.CreateContest.MaxPhotographiesPerUser' /></label>:&nbsp;
                <input type="number" value={numeroMaximoFotografiasParticipante} min="1" max="3" onChange={e => setNumeroMaximoFotografiasParticipante(e.target.value)} /><br /><br />

                <h6><FormattedMessage id='contest.CreateContest.FormatQuestion' /></h6>
                <input type="radio" name="tipoFormato" value="JPEG" onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatJPEG' /></label>&emsp;
                <input type="radio" name="tipoFormato" value="RAW" onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatRAW' /></label>&emsp;
                <input type="radio" name="tipoFormato" value="JPEG_Y_RAW" onChange={e => setFormatoRequerido(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.FormatJPEG+RAW' /></label><br /><br />

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

                <br /><br />
                {/* Proceso de votación */}
                <h4><FormattedMessage id='contest.CreateContest.VotingSection' /></h4><br />

                <h6><FormattedMessage id='contest.CreateContest.VotingTypeQuestion' /></h6>
                <input type="radio" name="tipoVotante" value="CUALQUIERA" onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeEveryone' /></label>&emsp;
                <input type="radio" name="tipoVotante" value="JURADO" onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeJury' /></label>&emsp;
                <input type="radio" name="tipoVotante" value="PARTICIPANTE" onChange={e => setTipoVotante(e.target.value)} />&nbsp;
                <label><FormattedMessage id='contest.CreateContest.VotingTypeContenders' /></label><br /><br />

                <Form.Group className="mb-3" controlId="DesripcionVotacionJurado">
                    <Form.Label><FormattedMessage id='contest.CreateContest.VotingDescription' /></Form.Label>
                    <Form.Control as="textarea" disabled={tipoVotante === "JURADO" ? false : true} value={descripcionVotacionJurado} onChange={e => setDescripcionVotacionJurado(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>
                <Multiselect placeholder={intl.formatMessage({ id: 'contest.CreateContest.JuryList' })} options={[{ name: "@Julio", id: 1 }, { name: "@Alex", id: 2 }]}
                    displayValue="name" onSelect={() => setListaCategorias()} showArrow="true" disable={tipoVotante === "JURADO" ? false : true} /><br /><br />
                <labe><FormattedMessage id='contest.CreateContest.VotingEndDate' /></labe>:&nbsp;
                <input type="datetime-local" value={fechaLimiteVotacion} onChange={e => setFechaLimiteVotacion(e.target.value)} /><br /><br />
                <label><FormattedMessage id='contest.CreateContest.VotingModel' /></label>&emsp;
                <select name="metodoVoto" onChange={e => setMetodoVoto(e.target.value)}>
                    <option value=""></option>
                    <option value="SIMPLE">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelSimple' })}</option>
                    <option value="CINCO_ESTRELLAS">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelFiveStars' })}</option>
                    <option value="EUROVISION">{intl.formatMessage({ id: 'contest.CreateContest.VotingModelEurovision' })}</option>
                </select><br />

                <label><FormattedMessage id='contest.CreateContest.MaxVotesPerUser' /></label>:&nbsp;
                <input type="number" disabled={metodoVoto === "SIMPLE" ? false : true} value={numeroMaximoVotosPorUsuario} min="1" max="5" onChange={e => setNumeroMaximoVotosPorUsuario(e.target.value)} /><br />
                <label><FormattedMessage id='contest.CreateContest.NumberOfWiningPhotos' /></label>:&nbsp;
                <input type="number" value={numeroMaximoDeFotografiasGanadoras} min="1" max="10" onChange={e => setNumeroMaximoDeFotografiasGanadoras(e.target.value)} /><br /><br />
                <Button variant="success" type="submit" onClick={e => handleSubmit(e)}>
                    <FormattedMessage id='app.RedirectHome.ButtonCreateContest' />
                </Button>
            </Form>
            <br /><br /><br /><br /><br /><br /><br />
        </Container>
    );
}

export default CreateContest;