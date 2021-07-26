import { FormattedMessage, FormattedDate, FormattedTime } from "react-intl";
import { Badge } from "react-bootstrap";
import { Link } from "react-router-dom";
import constants from "../../commons";
import commonFunctions from "../../commons/functions";

const Contests = ({contests}) =>{

    const contestPhoto = (databasePhoto) => {
        if(databasePhoto === null || databasePhoto === undefined){
            return constants.IMG_CONTEST_DEFAULT;
        }
        
        return databasePhoto;
    }

    return(
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.Photo"/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.Name"/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.Status"/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.InitialDate"/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.EndDate"/>
                        </th>
                    </tr>
                </thead>

                <tbody>
                    {contests.map(concurso => 
                        <tr key={concurso.idConcurso}>
                            <td><img alt="contest" src={`data:image/jpeg;base64, ${contestPhoto(concurso.fotografia)}`}/></td>
                            <td className="align-middle">
                                <Link to={`/catalog/concursos/${concurso.idConcurso}`}>{concurso.nombre}</Link>
                            </td>
                            <td className="align-middle"><Badge variant={commonFunctions.tipoLabelConcurso(concurso.estadoConcurso)}>{concurso.estadoConcurso}</Badge></td>
                            <td className="align-middle"><FormattedDate value={new Date(concurso.fechaInicio)}/> <FormattedTime value={new Date(concurso.fechaInicio)}/></td>
                            <td className="align-middle"><FormattedDate value={new Date(concurso.fechaFin)}/> <FormattedTime value={new Date(concurso.fechaFin)}/></td>
                        </tr>
                    )}
                </tbody>

            </table>
    );
}

export default Contests;