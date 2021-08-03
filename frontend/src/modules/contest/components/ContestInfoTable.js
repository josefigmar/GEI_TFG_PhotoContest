import { FormattedMessage, FormattedDate, FormattedTime} from "react-intl";
import commonFunctions from "../../commons/functions";
import { Badge } from "react-bootstrap";
import { useIntl } from "react-intl";
import { useEffect, useState } from "react";
import backend from "../../../backend";

const ContestInfoTable = ({contestData}) => {

    const intl = useIntl();
    const [numeroParticipantes, setNumeroParticipantes] = useState(0);

    useEffect(() => {
        backend.catalogService.getNumberContenders(
            contestData.idConcurso,
            result => setNumeroParticipantes(result)
        )
    }, [])

    return(
        <div className="contestInfoTable bg-light border border-secondary">
            <h4>&ensp;&ensp;&ensp;&ensp;&ensp;<FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Title'/></h4>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Status'/>:&nbsp;
                <Badge variant={commonFunctions.tipoLabelConcurso(contestData.estadoConcurso)}>{intl.formatMessage({ id: 'app.ContestTable.Status.' + contestData.estadoConcurso })}</Badge>
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.Access'/>:&nbsp;
                <Badge variant={commonFunctions.tipoLabelAcceso(contestData.tipoAcceso)}>{intl.formatMessage({ id: 'contest.contestDetail.Header.contestInfoTable.Access.' + contestData.tipoAcceso})}</Badge>
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.WhoCanVote'/>:&nbsp;
                <Badge variant={commonFunctions.tipoLabelVotante(contestData.tipoVotante)}>{intl.formatMessage({ id: 'contest.contestDetail.Header.contestInfoTable.WhoCanVote.' + contestData.tipoVotante})}</Badge>
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.NumberOfContenders'/>:&nbsp;
                {numeroParticipantes}/200
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.InitialUploadDate'/>:&nbsp;
                <FormattedDate value={new Date(contestData.fechaInicio)}/> <FormattedTime value={new Date(contestData.fechaInicio)}/>
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.StartOfVoting'/>:&nbsp;
                <FormattedDate value={new Date(contestData.fechaInicioVotacion)}/> <FormattedTime value={new Date(contestData.fechaInicioVotacion)}/>
            </h6>
            <hr/>
            <h6>
                &ensp;
                <FormattedMessage id='contest.contestDetail.Header.contestInfoTable.EndContestDate'/>:&nbsp;
                <FormattedDate value={new Date(contestData.fechaLimiteVotacion)}/> <FormattedTime value={new Date(contestData.fechaLimiteVotacion)}/>
            </h6>
            <hr/>
            

        </div>
    )


}

export default ContestInfoTable;