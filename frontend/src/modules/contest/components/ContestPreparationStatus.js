import ContestHeader from "./ContestHeader";
import Countdown from "react-countdown";
import { Container, Spinner } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import commonFunctions from "../../commons/functions"

const ContestPreparationStatus = ({ contestData }) => {

    return (
        <div>
            <ContestHeader contestData={contestData} />
            <hr />
            <Container className="centering">
                <h4><FormattedMessage id='contest.contestDetail.Body.PreparationState.CountDown.Title' /></h4>
                <Spinner animation="grow" variant="danger" size="sm" />&ensp;
                <h4 className="hWithoutLineBreak"><Countdown daysInHours={true} date={Date.now() + commonFunctions.calculateDiffBetweenDates(new Date(contestData.fechaInicio), Date.now())} /></h4>
            </Container>
        </div>
    )

}

export default ContestPreparationStatus;