import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";

const Users = ({users}) =>{


    return(
            <table className="table table-striped table-hover">
                <thead>
                    <tr>
                        <th scope="col">
                            <FormattedMessage id='user.Users.Photo'/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id='user.Users.FullName'/>
                        </th>
                        <th scope="col">
                            <FormattedMessage id='user.Users.UserName'/>
                        </th>
                    </tr>
                </thead>

                <tbody>
                    {users.map(user => 
                        <tr key={user.idUsuario}>
                            <td><img className="profileImg" alt="user profile" src={`data:image/jpeg;base64, ${user.fotoPerfil}`}/></td>
                            <td>
                                {`${user.nombrePilaUsuario} ${user.apellidosUsuario}`}
                            </td>
                            <td>
                                <Link to={`/users/${user.idUsuario}`}>
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