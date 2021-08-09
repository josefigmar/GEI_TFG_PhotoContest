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
    const [isContender, setIsContender] = useState(false);
    const userNameLogged = useSelector(userSelectors.getUserName);

    const isPreparationState = () => {

        return contestData.estadoConcurso === "EN_PREPARACION";
    }

    const isOpen = () => {

        return contestData.estadoConcurso === "ABIERTO";
    }

    const isPublicAccess = () => {

        return contestData.tipoAcceso === "PUBLICO";
    }

    useEffect(() => {
        // It is checked if the user is staff member
        backend.catalogService.isOrganizador(
            {
                userName: userNameLogged,
                contestId: contestData.idConcurso
            },
            result => setIsStaff(result)
        )
        // It is checked if the user is a contender
        backend.catalogService.isContender(
            {
                userName: userNameLogged,
                contestId: contestData.idConcurso
            },
            result => setIsContender(result)
        )
        // eslint-disable-next-line
    }, [])

    return (
        <div className="contestHeaderButtons">

            {
                // If the user is a Staff member, he/her has the right to manage and delete the contest in this stage
                isStaff ?

                    <div className="d-flex">
                        {
                            // Si el estado ha pasado a abierto, el concurso ya no se puede editar.
                            isPreparationState() ?

                                <div>
                                    <form onSubmit={() => history.push(`/contests/create-contest/${contestData.idConcurso}/${contestData.nombreConcurso}`)}>
                                        <Button
                                            variant="info"
                                            type="submit">
                                            <FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.EditContest' />
                                        </Button>
                                    </form>
                                </div>

                                :

                                null
                        }
                        &ensp;
                        {
                            // You can only supervise photos if the contest allows photo upload (Open state)
                            isOpen()?

                                <form onSubmit={() => history.push(`/contests/${contestData.nombreConcurso}/${contestData.idConcurso}/supervise`)}>
                                    <Button
                                        variant="info"
                                        type="submit">
                                        <FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.Supervise' />
                                    </Button>
                                </form>

                                :

                                null

                        }

                        &ensp;
                        <DeleteContest contestData={contestData} />
                        &ensp;
                    </div>

                    :

                    null
            }
            <br />
            {
                isOpen() && !isStaff && (isPublicAccess() || isContender) ?

                    <div>
                        <form onSubmit={() => history.push(`/contests/${contestData.nombreConcurso}/${contestData.idConcurso}/participate`)}>
                            <Button
                                variant="success"
                                type="submit">
                                <FormattedMessage id='contest.contestDetail.Header.contestHeaderButtons.Participate' />
                            </Button>
                        </form>
                        &nbsp;
                    </div>

                    :

                    null
            }
        </div>
    )
}

export default ContestHeaderButtons;