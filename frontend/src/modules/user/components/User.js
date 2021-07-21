import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import backend from "../../../backend";
import UserContests from "./UserContests";
import UserProfileDetails from "./UserProfileDetails";
import { useSelector } from "react-redux";
import * as userSelectors from "../selectors";
import DeleteUser from "./DeleteUser";

const User = () => {

    const [userData, setUserData] = useState("");

    const { userName } = useParams();
    // The userName of the user that is logged in
    const userNameLogged = useSelector(userSelectors.getUserName);
    const isLoggedIn = useSelector(userSelectors.isUserLoggedIn);

    useEffect(() => {

        backend.userService.findUser({ userName }, result =>
            setUserData(result));

    }, [userName])

    if (userData === "") {
        return null;
    }

    return (
        <div>
            <UserProfileDetails userData={userData} />
            <br />
            {userData.cuentaEliminada === false && userData.concursosEnLosQueParticipa.length !== 0 ?

                <UserContests userContestsData={userData.concursosEnLosQueParticipa} />

                :

                null
            }
            <br />
            <br />
            <br />
            <div>
                {
                    // If the user that is logged in is seeing his/her profile, then the
                    // Delete account button should be active
                    isLoggedIn && userNameLogged === userData.nombreUsuario ?

                        <DeleteUser userName={userName} />

                        :

                        null
                }

            </div>
        </div>


    );
}

export default User;
