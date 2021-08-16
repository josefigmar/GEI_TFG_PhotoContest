import { Button, Badge } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";
import backend from "../../../backend";

const SimpleVote = ({ votingInfo, contestData }) => {

    const history = useHistory();

    const handleVote = () => {

        backend.catalogService.vote(
            {
                nombreUsuario: contestData.userName,
                idFotografia: contestData.photographyId,
                nombreConcurso: contestData.contestName,
                puntuacion: 1
            },
            () => history.go(0)


        )

    }

    const hasReachedVotingLimit = () => {
        return votingInfo.numeroDeVotosUsuario >= votingInfo.numeroMaximoVotosPorUsuarioConcurso;
    }

    const hasAlreadyVoted = () => {
        return votingInfo.haVotado;
    }

    const isButtonDisabled = () => {

        let result = false;

        // Ya ha votado dicha fotografía
        if (hasAlreadyVoted()) {
            result = true;
        }

        if (hasReachedVotingLimit()) {
            result = true;
        }

        return result;
    }

    return (
        <div>
            {
                hasAlreadyVoted() ?

                    <div className="d-flex justify-content-center">
                        <h4><FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.AlreadyVotted' /></h4>
                    </div>

                    :

                    null
            }
            <br/>
            {
                hasReachedVotingLimit() ?

                    <div className="d-flex justify-content-center">
                        <h4><FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.LimitOfVotes' /></h4>
                    </div>

                    :

                    null
            }
            <br/>
            {
                votingInfo.ocultarVotos ?

                    null

                    :

                    <div className="d-flex justify-content-center">
                        <Badge variant="info">
                            <h4> {votingInfo.puntuacionTotal} <FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.NumberOfPoints' /></h4>
                        </Badge>
                    </div>
            }

            <br />
            <div className="d-flex justify-content-center">
                <Button variant="success" disabled={isButtonDisabled()} onClick={() => handleVote()}>
                    <FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.Button.Name' />
                </Button>
            </div>

            <br />
            <br />
            <br />
            <br />
        </div>

    )

}

export default SimpleVote;