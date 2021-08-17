import { useEffect, useState } from "react";
import { Container, Spinner, Card, Button, Badge } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";
import backend from "../../../backend";

const ResultPresentation = ({ contestData }) => {

    const history = useHistory()
    const [data, setData] = useState("")

    useEffect(() => {
        backend.catalogService.getWinners(
            contestData.nombreConcurso,
            contestData.numeroMaximoDeFotografiasGanadoras,
            result => setData(result)
        );
        // eslint-disable-next-line
    }, [])

    if (data === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <div>
            <div className="d-flex justify-content-center">
                <h4>
                    <FormattedMessage id='contest.contestDetail.Body.FinishedStatus.Winners.Title' />
                </h4>
            </div>
            <br />

            {
                data.map(photoData =>

                    <div>
                        <div className="d-flex justify-content-center">
                            <h4>
                                {photoData.posicion} <FormattedMessage id='contest.contestDetail.Body.FinishedStatus.Winners.Position' /> :
                                &nbsp;
                                <Badge variant="warning">
                                    {photoData.puntuacionFinal} <FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.NumberOfPoints' />
                                </Badge>
                            </h4>
                        </div>
                        <br />
                        <div className="d-flex justify-content-center">
                            <Card style={{ width: '18rem' }}>
                                <Card.Img className="photographyGridImages" variant="top" src={`data:image/jpeg;base64, ${photoData.fotografiaDto.datosJpg}`} />
                                <Card.Body>
                                    <Card.Title className="centeredParagraph">{photoData.fotografiaDto.tituloFotografia}</Card.Title>
                                    <div className="d-flex justify-content-center">
                                        <Button variant="primary" onClick={() => history.push(`/contests/${photoData.fotografiaDto.nombreConcurso}/${photoData.fotografiaDto.idConcurso}/photography/${photoData.fotografiaDto.idFotografia}`)}><FormattedMessage id='contest.photographyGrid.card.button.text' /></Button>
                                    </div>
                                </Card.Body>
                            </Card>
                        </div>
                        <br />
                        <hr />
                    </div>

                )
            }
            <br />
            <br />
            <br />
            <br />
            <br />
        </div>
    )
}

export default ResultPresentation;