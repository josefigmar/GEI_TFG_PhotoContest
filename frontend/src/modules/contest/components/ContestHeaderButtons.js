import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import * as userSelectors from "../../user/selectors";
import backend from "../../../backend";

const ContestHeaderButtons = ({ contestData }) => {

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

                <div>
                    <Button variant="primary" ><FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.EditContest' /></Button>
                    &ensp;
                    <Button variant="danger" ><FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.DeleteContest' /></Button>
                    &ensp;
                </div>

                :

                    null
            }
        </div>
    )
}

export default ContestHeaderButtons;