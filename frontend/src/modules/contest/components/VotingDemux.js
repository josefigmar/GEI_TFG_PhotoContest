
import SimpleVote from "./SimpleVote";
import StarVote from "./StarVote";
import EurovisionVote from "./EurovisionVote";
import { useEffect, useState } from "react";
import { Spinner, Container } from "react-bootstrap";
import backend from "../../../backend";

const VotingDemux = (pageData) => {

    const [votingInfo, setVotingInfo] = useState("");

    useEffect(() => {
        backend.catalogService.getVoteInfo(
            {
                contestName: pageData.contestName,
                userName: pageData.userName,
                photographyId: pageData.photographyId
            },
            result => setVotingInfo(result),
            () => null
        );
        // eslint-disable-next-line
    }, [])

    // While backend responds
    if (votingInfo === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }


    // eslint-disable-next-line
    switch (pageData.tipoVoto) {
        case 'SIMPLE': return (
            <div>
                <SimpleVote votingInfo={votingInfo} contestData={pageData} />
            </div>
        )
        case 'CINCO_ESTRELLAS': return (
            <div>
                <StarVote votingInfo={votingInfo} contestData={pageData}/>
            </div>
        )
        case 'EUROVISION': return (
            <div>
                <EurovisionVote votingInfo={votingInfo} contestData={pageData}/>
            </div>
        )
    }

}

export default VotingDemux;