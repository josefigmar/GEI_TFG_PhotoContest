import { useParams } from "react-router";
import { useState } from "react";
import { useEffect } from "react";
import Photography from "./Photography";
import SuperviseControl from "./SuperviseControl";
import backend from "../../../backend";

const PhotographySupervision = () => {

    const { contestName, photographyId } = useParams();
    const [photographyData, setPhotographyData] = useState("");
    const [userData, setUserData] = useState("");

    useEffect(() => {
        backend.catalogService.getPhotography(
            {
                contestName,
                photographyId

            },

            result => setPhotographyData(result),
            () => null
        )
        // eslint-disable-next-line
    }, []);

    useEffect(() => {

        if (photographyData !== "") {
            backend.userService.findUser(
                {
                    userName: photographyData.nombreUsuario
                },
                result => setUserData(result)
            )
        }

    }, [photographyData])

    return (
        <div>
            <Photography photographyData={photographyData} userData={userData} />
            <SuperviseControl photographyData={photographyData} userData={userData} contestName={contestName}/>
        </div>

    )

}

export default PhotographySupervision;