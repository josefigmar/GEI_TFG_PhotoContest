import ContestHeader from "./ContestHeader";
import Countdown from "react-countdown";
import { Container, Spinner } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import commonFunctions from "../../commons/functions"
import PhotographyGridPaginated from "./PhotographyGridPaginated";

const ContestVotingStatus = ({ contestData }) => {

    return (
        <div>
            <ContestHeader contestData={contestData} />
            <hr />
            <PhotographyGridPaginated contestName={contestData.nombreConcurso} contestId={contestData.idConcurso}/>
            <Container className="centering">
                <h4><FormattedMessage id='contest.contestDetail.Body.VotingState.CountDown.Title' /></h4>
                <Spinner animation="grow" variant="danger" size="sm" />&ensp;
                <h4 className="hWithoutLineBreak"><Countdown daysInHours={true} date={Date.now() + commonFunctions.calculateDiffBetweenDates(new Date(contestData.fechaLimiteVotacion), Date.now())} /></h4>
            </Container>
        </div>
    )

}

export default ContestVotingStatus;