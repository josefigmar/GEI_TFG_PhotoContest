import { useEffect, useState } from "react";
import { useParams, Link} from "react-router-dom";
import backend from "../../../backend";
import UserContests from "./UserContests";
import UserProfileDetails from "./UserProfileDetails";


const User = () => {

    const[userData, setUserData] = useState("");

    const {userName} = useParams();

    useEffect(() =>{

        backend.userService.findUser({userName}, result => 
            setUserData(result));

    }, [userName])

    if(userData === ""){
        return null;
    }

    return(
        <div>
            <UserProfileDetails userData={userData}/>
            <br/>
            { userData.concursosEnLosQueParticipa.length !== 0 &&
                <UserContests userContestsData={userData.concursosEnLosQueParticipa}/>
            }
        </div>

    );
}

export default User;
