import { useState } from "react";
import { Link } from "react-router-dom";
import { FormattedMessage, FormattedDate, FormattedTime, useIntl} from "react-intl";
import Pager from "../../commons/components/Pager";
import { Badge } from "react-bootstrap";
import commonFunctions from "../../commons/functions";

const UserContests = ({ userContestsData }) => {

    const size = 4;
    const intl = useIntl();
    const [page, setPage] = useState(0);

    const modifyContests = (newPage) => {
        setPage(newPage);
    }

    return (
        <div>
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">
                            <FormattedMessage id='user.Profile.ContestTable.Name' />
                        </th>
                        <th scope="col">
                            <FormattedMessage id='user.Profile.ContestTable.role' />
                        </th>
                        <th scope="col">
                            <FormattedMessage id="app.ContestTable.Status" />
                        </th>
                        <th scope="col">
                            <FormattedMessage id='user.Profile.ContestTable.fecha' />
                        </th>
                    </tr>
                </thead>

                <tbody>
                    {userContestsData.slice(page * size, page * size + size).map(c =>
                        <tr key={c.idConcurso}>
                            <Link className="nav-link" to={`/contests/${c.nombreConcurso}/${c.idConcurso}`}>
                                {`${c.nombreConcurso}`}
                            </Link>
                            <td>
                                <Badge variant="primary">{`${c.rolUsuario}`}</Badge>
                            </td>
                            <td>
                                <Badge variant={commonFunctions.tipoLabelConcurso(c.estadoConcurso)}>{intl.formatMessage({id: 'app.ContestTable.Status.' + c.estadoConcurso})}</Badge>
                            </td>
                            <td>
                                <FormattedDate value={new Date(c.fechaInicioParticipacion)} /> <FormattedTime value={new Date(c.fechaInicioParticipacion)} />
                            </td>
                        </tr>
                    )}
                </tbody>
            </table >
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => modifyContests(page - 1)
                }}
                next={{
                    enabled: userContestsData[(page * size) + size] !== undefined,
                    onClick: () => modifyContests(page + 1)
                }}
            />
        </div>
    );
}

export default UserContests;