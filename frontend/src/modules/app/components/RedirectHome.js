import { Container, Jumbotron } from "react-bootstrap";
import { useSelector } from "react-redux";
import * as userSelectors from "../../user/selectors";
import ContestButtons from "./ContestButtons";
import SessionButtons from "./SessionButtons";


const RedirectHome = () =>{

    const userName = useSelector(userSelectors.getUserName);
    
    return(

        <Jumbotron fluid className="bg-light border border-ssecondary">
            <Container>
                <ContestButtons/>
                <br/>
                <br/>
                
                {
                    // If the user is logged in there is no need to show him/her the session buttons
                    userName?
                        null
                    :
                    <SessionButtons/>
                }
            </Container>
        </Jumbotron>
    );
}

export default RedirectHome;