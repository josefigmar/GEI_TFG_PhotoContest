import { FormattedMessage } from "react-intl";
import Pager from "../../commons/components/Pager";
import backend from "../../../backend";
import { useState } from "react";

const UserContests = ({userContestsData}) => {

    const size = 5;
    const [page, setPage] = useState(0);

    const modifyContests = (newPage) =>{
        setPage(newPage);
    }

    return (
        <div>
            <table className="table table-striped table-hover">
            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='user.Profile.ContestTable.id'/>
                    </th>
                    <th scope="col">
                        <FormattedMessage id='user.Profile.ContestTable.role'/>
                    </th>
                    <th scope="col">
                        <FormattedMessage id='user.Profile.ContestTable.fecha'/>
                    </th>
                </tr>
            </thead>

            <tbody>
                {userContestsData.slice(page*size, page*size + size).map(c => 
                    <tr key={c.idConcurso}>
                        <td>
                            {`${c.idConcurso}`}
                        </td>
                        <td>
                            {`${c.rolUsuario}`}
                        </td>
                        <td>
                            {`${c.fechaInicioParticipacion}`}
                        </td>
                    </tr>
                )}
            </tbody>
        </table >
        <Pager 
            back={{
                enabled: page >=1,
                onClick: () => modifyContests(page-1)}}
            next={{
                enabled: userContestsData[(page * size) + size] !== null,
                onClick: () => modifyContests(page+1)}}
        />
        </div>
    );
}

export default UserContests;