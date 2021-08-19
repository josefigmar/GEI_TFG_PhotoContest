import { Container, Spinner } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import commonsExportObj from "../../commons";

const Photography = (props) => {

    let photographyData = props.photographyData;
    let userData = props.userData;

    const userPhoto = (databasePhoto) => {
        if (databasePhoto === null || databasePhoto === undefined) {
            return commonsExportObj.constants.IMG_PROFILE_DEFAULT;
        }

        return databasePhoto;
    }


    if (photographyData === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <Container>
            <div className="d-flex justify-content-center">
                {
                    photographyData.datosJpg === "" ?

                        <img className="photographyDetail" alt="previsualization" src={"data:image/jpeg;base64," + photographyData.datosRaw} />

                        :

                        <img className="photographyDetail" alt="previsualization" src={"data:image/jpeg;base64," + photographyData.datosJpg} />
                }
            </div>
            <br/>
            <div className="d-flex justify-content-center">
                <br/>
                <a
                    className="centeredLink"
                    href={`data:image/jpeg;base64,${photographyData.datosJpg}`}
                    download={`${photographyData.tituloFotografia}-IMG.jpg`}>
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                        <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z" />
                    </svg>
                    &nbsp;
                    <FormattedMessage id='contest.photographyPresentation.DownloadImg' />
                </a>
            </div>
            <hr />
            <div className="photographyPresentationUserData">
                <div className="centeredDiv">
                    <img className="photograhyPresentationProfileImg" alt="previsualization" src={"data:image/jpeg;base64," + userPhoto(userData.fotoPerfil)} />
                </div>
                <div className="centeredDiv">
                    {userData.nombrePilaUsuario} {userData.apellidosUsuario}
                </div>

            </div>
            <hr />
            <br />

            {
                photographyData.tituloFotografia ?

                    <div className="centeredDiv">
                        <h6 className="centeredParagraph">
                            &ensp;
                            <FormattedMessage id='contest.participate.dataSection.PhotoTitle' /><br />
                            <p className="p2">{photographyData.tituloFotografia}</p>
                        </h6>
                    </div>

                    :

                    null
            }
            <br />

            {
                photographyData.descripcionFotografia ?
                    <div className="photographyDescDiv centeredParagraph">
                        <h6>
                            &ensp;
                            <FormattedMessage id='contest.participate.dataSection.PhotoDesc' /><br />
                            <p className="p2">{photographyData.descripcionFotografia}</p>
                        </h6>
                    </div>
                    :
                    null
            }
            <hr />
            <br />

            <div className="divFlexDirectionColumn">
                <div className="exifDataDiv">
                    <div className="centeredParagraph centeredHorizontalDiv">
                        <h6>
                            <FormattedMessage id='contest.participate.dataSection.CameraBrand' /><br />
                            {
                                photographyData.fabricanteCamara ?
                                    <p className="p2">{photographyData.fabricanteCamara}</p>
                                    :
                                    <p className="p2">--</p>
                            }
                        </h6>
                    </div>




                    <div className="centeredParagraph centeredHorizontalDiv">
                        <h6>
                            <FormattedMessage id='contest.participate.dataSection.CameraModel' /><br />
                            {
                                photographyData.modeloCamara ?
                                    <p className="p2">{photographyData.modeloCamara}</p>
                                    :
                                    <p className="p2">--</p>
                            }
                        </h6>
                    </div>



                    {
                        photographyData.distanciaFocal ?
                            <div className="centeredParagraph centeredHorizontalDiv">
                                <h6>
                                    <FormattedMessage id='contest.participate.dataSection.FocalDistance' /><br />
                                    <p className="p2">{photographyData.distanciaFocal}mm</p>
                                </h6>
                            </div>

                            :
                            null
                    }
                </div>
                <div className="exifDataDiv">
                    {
                        photographyData.aperturaDiafragma ?
                            <div className="centeredParagraph centeredHorizontalDiv">
                                <h6>
                                    <FormattedMessage id='contest.participate.dataSection.Aperture' /><br />
                                    <p className="p2">ƒ/{photographyData.aperturaDiafragma}</p>
                                </h6>
                            </div>

                            :
                            null
                    }
                    {
                        photographyData.velocidadObturacion ?
                            <div className="centeredParagraph centeredHorizontalDiv">
                                <h6>
                                    <FormattedMessage id='contest.participate.dataSection.ShutterSpeed' /><br />
                                    <p className="p2">{photographyData.velocidadObturacion}s</p>
                                </h6>
                            </div>

                            :
                            null
                    }
                    {
                        photographyData.iso ?
                            <div className="centeredParagraph centeredHorizontalDiv">
                                <h6>
                                    <FormattedMessage id='contest.participate.dataSection.ISO' /><br />
                                    <p className="p2">{photographyData.iso}</p>
                                </h6>
                            </div>

                            :
                            null
                    }

                </div>
                <div className="exifDataDiv">
                    {
                        photographyData.resolucion ?
                            <div className="centeredParagraph centeredHorizontalDiv">
                                <h6>
                                    <FormattedMessage id='contest.participate.dataSection.Resolution' /><br />
                                    <p className="p2">{photographyData.resolucion}</p>
                                </h6>
                            </div>

                            :
                            null
                    }

                </div>
                <hr />
                <div className="centeredParagraph centeredHorizontalDiv">
                    <h6>
                        <FormattedMessage id='contest.supervision.table.category' /><br />
                        <p className="p2">{photographyData.nombreCategoria}</p>
                    </h6>
                </div>
            </div>
            <hr />
            <br/>
            <br/>







        </Container>
    )


}

export default Photography;