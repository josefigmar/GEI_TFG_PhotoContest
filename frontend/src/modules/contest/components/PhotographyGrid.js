import { Container, Col, Row, Card, Button} from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";

const PhotographyGrid = ({ photographies }) => {

    const history = useHistory();

    const column = data => {
        let column = (
            <Col sm={4}>
                <Card style={{ width: '18rem' }}>
                    <Card.Img className="photographyGridImages" variant="top" src={`data:image/jpeg;base64, ${data.datosJpg}`} />
                    <Card.Body>
                        <Card.Title className="centeredParagraph">{data.tituloFotografia}</Card.Title>
                        <div className="d-flex justify-content-center">
                        <Button variant="primary" onClick={() => history.push(`/contests/${data.nombreConcurso}/${data.idConcurso}/photography/${data.idFotografia}`)}><FormattedMessage id='contest.photographyGrid.card.button.text'/></Button>
                        </div>
                        
                    </Card.Body>
                </Card>
            </Col>);

        return column;
    }


    return (
        <Container>
            <Row>
                {photographies[0] ?
                    column(photographies[0])
                    :
                    null
                }
                {photographies[1] ?
                    column(photographies[1])
                    :
                    null
                }
                {photographies[2] ?
                    column(photographies[2])
                    :
                    null
                }
            </Row>
            <br/>
            <Row>
                {photographies[3] ?
                    column(photographies[3])
                    :
                    null
                }
                {photographies[4] ?
                    column(photographies[4])
                    :
                    null
                }
                {photographies[5] ?
                    column(photographies[5])
                    :
                    null
                }
            </Row>
            <br/>
            <Row>
                {photographies[6] ?
                    column(photographies[6])
                    :
                    null
                }
                {photographies[7] ?
                    column(photographies[7])
                    :
                    null
                }
                {photographies[8] ?
                    column(photographies[8])
                    :
                    null
                }
            </Row>
        </Container>
    )
}

export default PhotographyGrid;