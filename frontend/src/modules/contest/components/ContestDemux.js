import { Container, Spinner } from "react-bootstrap";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import ContestPreparationStatus from "./ContestPreparationStatus";
import backend from "../../../backend";

const ContestDemux = () => {

    const { contestName, contestId } = useParams();
    const [contestData, setContestData] = useState("");

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

    // While backend responds
    if (contestData === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    // STATUS = IN PREPARATION
    if(contestData.estadoConcurso === "EN_PREPARACION")
    return (
        <div>
            <ContestPreparationStatus contestData={contestData}/>
        </div>

    )

}

export default ContestDemux;