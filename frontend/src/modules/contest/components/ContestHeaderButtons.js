import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";

const ContestHeaderButtons = ({contestData}) => {
    
    return(
        <div>
            <a href={`data:application/pdf;base64,${contestData.basesConcurso}`} download={`${contestData.nombreConcurso}-RULES.pdf`}><FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.DownloadRules'/></a>
        </div>
    )
}

export default ContestHeaderButtons;