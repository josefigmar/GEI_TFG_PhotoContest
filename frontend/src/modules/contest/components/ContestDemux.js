import { Container, Spinner } from "react-bootstrap";
import { useEffect, useState } from "react";
import { useParams } from "react-router";
import ContestPreparationStatus from "./ContestPreparationStatus";
import backend from "../../../backend";
import ContestOpenStatus from "./ContestOpenStatus";
import ContestVotingStatus from "./ContestVotingStatus";

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

    // eslint-disable-next-line
    switch (contestData.estadoConcurso) {
        case "EN_PREPARACION":
            return (
                <div>
                    <ContestPreparationStatus contestData={contestData} />
                </div>
            )
        case "ABIERTO":
            return (
                <div>
                    <ContestOpenStatus contestData={contestData} />
                </div>
            )
        case "VOTACION":
            return (
                <div>
                    <ContestVotingStatus contestData={contestData} />
                </div>
            )
    }
}

export default ContestDemux;