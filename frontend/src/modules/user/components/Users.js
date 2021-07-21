import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";
import commonsExportObj from "../../commons";
const Users = ({ users }) => {

    const userPhoto = (databasePhoto) => {
        if (databasePhoto === null || databasePhoto === undefined) {
            return commonsExportObj.constants.IMG_PROFILE_DEFAULT;
        }

        return databasePhoto;
    }


    return (
        <table className="table table-striped table-hover">
            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='user.Users.Photo' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='user.Users.FullName' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='user.Users.UserName' />
                    </th>
                </tr>
            </thead>

            <tbody>
                {users.map(user =>
                    user.cuentaEliminada !== true &&

                    <tr key={user.idUsuario}>
                        <td className="align-middle"><img className="profileImg" alt="user profile" src={`data:image/jpeg;base64, ${userPhoto(user.fotoPerfil)}`} /></td>
                        <td className="align-middle">
                            {`${user.nombrePilaUsuario} ${user.apellidosUsuario}`}
                        </td>
                        <td className="align-middle">
                            <Link to={`/users/${user.nombreUsuario}`}>
                                {`@${user.nombreUsuario}`}
                            </Link>
                        </td>
                    </tr>
                )}
            </tbody>
        </table>
    );
}

export default Users;