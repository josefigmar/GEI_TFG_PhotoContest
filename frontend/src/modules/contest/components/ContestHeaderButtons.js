import { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import * as userSelectors from "../../user/selectors";
import { useHistory } from "react-router";
import DeleteContest from "./DeleteContest";
import { jsPDF } from "jspdf";
import backend from "../../../backend";

const ContestHeaderButtons = ({ contestData }) => {

    const history = useHistory();
    const doc = new jsPDF();
    const [isStaff, setIsStaff] = useState(false);
    const [isContender, setIsContender] = useState(false);
    const userNameLogged = useSelector(userSelectors.getUserName);

    const isPreparationState = () => {

        return contestData.estadoConcurso === "EN_PREPARACION";
    }

    const isOpen = () => {

        return contestData.estadoConcurso === "ABIERTO";
    }

    const isFinished = () => {

        return contestData.estadoConcurso === "FINALIZADO";
    }

    const isPublicAccess = () => {

        return contestData.tipoAcceso === "PUBLICO";
    }

    const createPDF = result => {
        if (result) {
            doc.setFontSize(11);
            doc.text(result, 5, 10);
        }
    }

    const downloadResultReport = () => {
        doc.save(`${contestData.nombreConcurso}-VOTING-REPORT.pdf`);
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

    useEffect(() => {
        if (isFinished()) {
            createPDF(contestData.resumenVotacion);
        }
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
                                <div className="contestHeaderButtons">

                                    <form onSubmit={() => history.push(`/contests/create-contest/${contestData.idConcurso}/${contestData.nombreConcurso}`)}>
                                        <Button
                                            variant="info"
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
                        &ensp;
                        {
                            // You can only supervise photos if the contest allows photo upload (Open state)
                            isOpen() ?

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

            {
                isFinished() ?

                    <Button className="centeredLink" onClick={() => downloadResultReport()} variant="info">
                        <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                            <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z" />
                        </svg>
                        &nbsp;
                        <FormattedMessage id='contest.contestDetail.Body.FinishedStatus.Winners.ResultReport' />
                    </Button>

                    :

                    null
            }
        </div>
    )
}

export default ContestHeaderButtons;