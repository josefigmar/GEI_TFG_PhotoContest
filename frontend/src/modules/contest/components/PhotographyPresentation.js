import { Container, Spinner } from "react-bootstrap";
import { useParams } from "react-router";
import { useState } from "react";
import { useEffect } from "react";
import Photography from "./Photography";
import backend from "../../../backend";

const PhotographyPresentation = () => {

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

    if (photographyData === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <div>
            <Photography photographyData={photographyData} userData={userData} />
        </div>
    )

}

export default PhotographyPresentation;