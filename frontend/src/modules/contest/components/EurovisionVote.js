import { useEffect, useState } from "react";
import { Button, Badge } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useIntl } from "react-intl";
import { useHistory } from "react-router";
import backend from "../../../backend";

const EurovisionVote = ({ votingInfo, contestData }) => {

    const history = useHistory();
    const intl = useIntl();
    const [puntuacion, setPuntuacion] = useState(votingInfo.puntuacion);
    const [validVotes, setValidVotes] = useState([]);
    const possibleVotes = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12];

    const handleVote = () => {

        backend.catalogService.vote(
            {
                nombreUsuario: contestData.userName,
                idFotografia: contestData.photographyId,
                nombreConcurso: contestData.contestName,
                puntuacion
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

    useEffect(() => {
        let result = possibleVotes.filter(e => !votingInfo.puntuacionesEurovision.includes(e))
        setValidVotes(result);
        // eslint-disable-next-line
    }, [])

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
            <br />
            {
                hasReachedVotingLimit() ?

                    <div className="d-flex justify-content-center">
                        <h4><FormattedMessage id='contest.contestDetail.Body.VotingState.Voting.LimitOfVotes' /></h4>
                    </div>

                    :

                    null
            }
            <br />
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
            <form className="d-flex justify-content-center">
                <select value={puntuacion} onChange={e => setPuntuacion(e.target.value)} disabled={isButtonDisabled()} required>
                    <option value="">{intl.formatMessage({ id: 'contest.contestDetail.Body.VotingState.Voting.Eurovision.DefaultOption' })}</option>
                    {
                        validVotes.map(e =>
                            <option value={`${e}`}>{e}</option>
                        )
                    }
                </select>
            </form>
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

export default EurovisionVote;