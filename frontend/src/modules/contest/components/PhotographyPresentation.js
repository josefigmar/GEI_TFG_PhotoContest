import { Container, Spinner } from "react-bootstrap";
import { useParams } from "react-router";
import { useState } from "react";
import { useEffect } from "react";
import Photography from "./Photography";
import VotingDemux from "./VotingDemux";
import backend from "../../../backend";
import * as selectors from "../../user/selectors";
import { useSelector } from "react-redux";

const PhotographyPresentation = () => {

    const userName = useSelector(selectors.getUserName);
    const { contestName, photographyId } = useParams();
    const [photographyData, setPhotographyData] = useState("");
    const [userData, setUserData] = useState("");
    const [contestRolInfo, setContestRolInfo] = useState("");

    const isVotingSituation = () => {
        let result = false;

        if(contestRolInfo !== ""){

            // Usuario no participa activamente
            if(contestRolInfo.participa === false){
                if(contestRolInfo.tipoVotante === "CUALQUIERA"){
                    result = true;
                }
                // Tiene rol en el concurso
            } else {
                if(contestRolInfo.RolConcurso === "JURADO" && contestRolInfo.tipoVotante === "JURADO"){
                    result = true;
                }
                if(contestRolInfo.RolConcurso === "INSCRITO" && contestRolInfo.tipoVotante === "PARTICIPANTE"){
                    result = true;
                }
            }
        }

        return result;
    }

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

    useEffect(() => {

            backend.catalogService.getContestRolInfo(
                {
                    contestName,
                    userName
                },
                result => setContestRolInfo(result)
            )
        // eslint-disable-next-line
    }, [])

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
            {
                isVotingSituation()?

                    <VotingDemux 
                        tipoVoto={contestRolInfo.tipoVoto} 
                        contestName={contestName} 
                        userName={userName} 
                        photographyId={photographyData.idFotografia}
                    />

                :

                    null
            }
        </div>
    )

}

export default PhotographyPresentation;