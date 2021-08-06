import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import * as userSelectors from "../../user/selectors";
import { useHistory } from "react-router";
import DeleteContest from "./DeleteContest";
import backend from "../../../backend";

const ContestHeaderButtons = ({ contestData }) => {

    const history = useHistory();
    const [isStaff, setIsStaff] = useState(false);
    const userNameLogged = useSelector(userSelectors.getUserName);

    useEffect(() => {
        backend.catalogService.isOrganizador(
            {
                userName: userNameLogged,
                contestId: contestData.idConcurso
            },
            result => setIsStaff(result)
        )
        // eslint-disable-next-line
    }, [])

    return (
        <div className="contestHeaderButtons">

            {/* If the user is a Staff member, he/her has the right to manage and delete the contest in this stage */}
            {isStaff ?

                <div className="d-flex">
                    <form onSubmit={() => history.push(`/contests/create-contest/${contestData.idConcurso}/${contestData.nombreConcurso}`)}>
                        <Button
                            variant="primary"
                            type="submit">
                            <FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.EditContest' />
                        </Button>
                    </form>
                    &ensp;
                    <DeleteContest contestData={contestData} />
                    &ensp;
                </div>

                :

                null
            }
        </div>
    )
}

export default ContestHeaderButtons;