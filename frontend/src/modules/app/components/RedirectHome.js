import { Container, Jumbotron} from "react-bootstrap";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";


const RedirectHome = () =>{

    const history = useHistory();
    
    return(

        <Jumbotron fluid>
            <Container>
                <div className="d-flex justify-content-center">
                    <h4>
                        <FormattedMessage  id="app.RedirectHome.paragraphContests"/>
                    </h4>
                </div>
                <div className="d-flex justify-content-center">
                    <form onSubmit={() => history.push('/catalog/find-contests')}>
                        <Button  type="submit" className="d-flex justify-content-center" variant="success">
                            <FormattedMessage id="app.RedirectHome.ButtonContests"/>
                        </Button>
                    </form>

                    &emsp;
                    <Button variant="success">
                        <FormattedMessage id="app.RedirectHome.ButtonCreateContest"/>
                    </Button>
                </div>
               
                <br/>
                <div className="d-flex justify-content-center">
                    <h4>
                        <FormattedMessage id="app.RedirectHome.paragraphAccount"/>
                    </h4>
                </div>
                <div className="d-flex justify-content-center">
                    <Button variant="info">
                        <FormattedMessage id="app.RedirectHome.LogIn"/>
                    </Button>
                    &emsp;
                    <Button variant="info">
                        <FormattedMessage id="app.RedirectHome.SignIn"/>    
                    </Button>
                </div>
                
            </Container>
        </Jumbotron>

    );
}

export default RedirectHome;