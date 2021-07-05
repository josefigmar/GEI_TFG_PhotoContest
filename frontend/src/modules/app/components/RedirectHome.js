import { Container, Jumbotron} from "react-bootstrap";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";


const RedirectHome = () =>{

    const history = useHistory();
    
    return(

        <Jumbotron fluid className="bg-light border border-ssecondary">
            <Container>
                <div className="d-flex justify-content-center">
                    <h5>
                        <FormattedMessage  id="app.RedirectHome.paragraphContests"/>
                    </h5>
                </div>
                <br/>

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
                <br/>

                <div className="d-flex justify-content-center">
                    <h5>
                        <FormattedMessage id="app.RedirectHome.paragraphAccount"/>
                    </h5>
                </div>
                <br/>
                <div className="d-flex justify-content-center">
                    <form onSubmit={() => history.push('/users/login')}>
                        <Button type="submit" variant="info">
                            <FormattedMessage id="app.RedirectHome.LogIn"/>
                        </Button>
                    </form>
                    &emsp;
                    <div className="d-flex justify-content-center">
                        <form onSubmit={() => history.push('/users/signUp')}>
                            <Button type="submit" className="d-flex justify-content-center" variant="info">
                                <FormattedMessage id="app.RedirectHome.SignIn"/>    
                            </Button>
                        </form>
                    </div>
                </div>
                
            </Container>
        </Jumbotron>

    );
}

export default RedirectHome;