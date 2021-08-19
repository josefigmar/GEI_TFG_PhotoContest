import { FormattedMessage, FormattedDate, FormattedTime } from "react-intl";
import commonFunctions from "../../commons/functions";
import { Badge } from "react-bootstrap";
import { useIntl } from "react-intl";
import { useEffect, useState } from "react";
import backend from "../../../backend";
import { Link } from "react-router-dom";

const ContestInfoTable = ({ contestData }) => {

    const intl = useIntl();
    const [numeroFotos, setNumeroFotos] = useState(0);

    useEffect(() => {
        backend.catalogService.getNumberPhotos(
            contestData.nombreConcurso,
            result => setNumeroFotos(result)
        )
        // eslint-disable-next-line
    }, [])

    return (
        <div className="contestInfoTable bg-light border border-secondary">
            <h4>&ensp;&ensp;&ensp;&ensp;&ensp;<FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Title' /></h4>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Status' />:&nbsp;
                <Badge variant={commonFunctions.tipoLabelConcurso(contestData.estadoConcurso)}>{intl.formatMessage({ id: 'app.ContestTable.Status.' + contestData.estadoConcurso })}</Badge>
            </h6>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Access' />:&nbsp;
                <Badge variant={commonFunctions.tipoLabelAcceso(contestData.tipoAcceso)}>{intl.formatMessage({ id: 'contest.contestDetail.Header.contestInfoTable.Access.' + contestData.tipoAcceso })}</Badge>
            </h6>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.WhoCanVote' />:&nbsp;
                <Badge variant={commonFunctions.tipoLabelVotante(contestData.tipoVotante)}>{intl.formatMessage({ id: 'contest.contestDetail.Header.contestInfoTable.WhoCanVote.' + contestData.tipoVotante })}</Badge>
            </h6>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.NumberOfContenders' />:&nbsp;
                <p className="p2">{numeroFotos}/{contestData.numeroMaximoFotografias}</p>
            </h6>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.InitialUploadDate' />:&nbsp;
                <p className="p2"><FormattedDate value={new Date(contestData.fechaInicio)} /> <FormattedTime value={new Date(contestData.fechaInicio)} /></p>
            </h6>

            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.StartOfVoting' />:&nbsp;
                <p className="p2"><FormattedDate value={new Date(contestData.fechaInicioVotacion)} /> <FormattedTime value={new Date(contestData.fechaInicioVotacion)} /></p>
            </h6>
            <hr />
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.EndContestDate' />:&nbsp;
                <p className="p2"><FormattedDate value={new Date(contestData.fechaLimiteVotacion)} /> <FormattedTime value={new Date(contestData.fechaLimiteVotacion)} /></p>
            </h6>
            <hr />
            <div className="centeredLink">
                <Link className="centeredLink" to={`/contests/${contestData.nombreConcurso}/${contestData.idConcurso}/staffMembers`} >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                    </svg>
                    &nbsp;
                    <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Staff' />
                </Link>
                &ensp;
                <Link className="centeredLink" to={`/contests/${contestData.nombreConcurso}/${contestData.idConcurso}/contenders`} >
                    <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                        <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                    </svg>
                    &nbsp;
                    <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Contenders' />
                </Link>
                &ensp;
                {
                    /* Existe la figura de jurado? */
                    contestData.tipoVotante === "JURADO" ?
                        <Link className="centeredLink" to={`/contests/${contestData.nombreConcurso}/${contestData.idConcurso}/juryMembers`} >
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-person" viewBox="0 0 16 16">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z" />
                            </svg>
                            &nbsp;
                            <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Jury' />
                        </Link>

                        :

                        null
                }
            </div>
            <hr />
            <a
                className="centeredLink"
                href={`data:application/pdf;base64,${contestData.basesConcurso}`}
                download={`${contestData.nombreConcurso}-RULES.pdf`}>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-arrow-down-circle" viewBox="0 0 16 16">
                    <path fill-rule="evenodd" d="M1 8a7 7 0 1 0 14 0A7 7 0 0 0 1 8zm15 0A8 8 0 1 1 0 8a8 8 0 0 1 16 0zM8.5 4.5a.5.5 0 0 0-1 0v5.793L5.354 8.146a.5.5 0 1 0-.708.708l3 3a.5.5 0 0 0 .708 0l3-3a.5.5 0 0 0-.708-.708L8.5 10.293V4.5z" />
                </svg>
                &nbsp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.DownloadRules' />
            </a>
            <hr />
        </div>
    )


}

export default ContestInfoTable;