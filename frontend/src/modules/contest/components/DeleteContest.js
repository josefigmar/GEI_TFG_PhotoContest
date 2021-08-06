import backend from "../../../backend";
import { useHistory } from "react-router";
import { Button } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";

const DeleteContest = ({ contestData }) => {

    const intl = useIntl();
    const history = useHistory();

    const handleDeleteContest = () => {
        backend.catalogService.deleteContest(
            {
                contestName: contestData.nombreConcurso,
                contestId: contestData.idConcurso
            },
            () => { history.push("/"); },
        );
    }

    return (
        <div className='delete-button' onClick={() => { if (window.confirm(intl.formatMessage({ id: 'contest.contestDetail.Header.contestHeaderButtons.DeleteContest.Msg' }))) handleDeleteContest() }}>
            <Button
                variant="danger" >
                <FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.DeleteContest' />
            </Button>
        </div>
    );
}

export default DeleteContest;