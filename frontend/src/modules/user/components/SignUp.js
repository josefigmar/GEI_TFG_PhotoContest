import { Container, Button} from "react-bootstrap";
import { FormattedMessage } from "react-intl";





const SignUp = () => {

    return(

        <Container>
            <Container className="signUpLogInDivFirst bg-light border border-secondary ">
                <br/>
                <div className="d-flex justify-content-center">
                    <h3>PhotoContest</h3>
                </div>
            </Container>
            <br/>
            <Container className="signUpDivSecond bg-light border border-secondary ">
                <form>
                    <br/>
                    <div className="d-flex justify-content-center">
                        <h6><FormattedMessage id='user.SignUp.Welcome'/></h6>
                    </div>
                    <br/>
                    
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">@</span>
                        </div>
                        <FormattedMessage id='user.SignUp.UserName'>
                            {placeholder => <input placeholder={placeholder} className="form-control"/>}
                        </FormattedMessage>
                        
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Password'>
                            {placeholder => <input placeholder={placeholder} className="form-control" type="password"/>}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Name'>
                            {placeholder => <input placeholder={placeholder} className="form-control"/>}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Surnames'>
                            {placeholder => <input placeholder={placeholder} className="form-control"/>}
                        </FormattedMessage>
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Email'>
                            {placeholder => <input placeholder={placeholder} className="form-control"/>}
                        </FormattedMessage>
                    </div>
                    <br/>
                    <div className="d-flex justify-content-center">
                        <Button type="submit" className="d-flex justify-content-center" variant="success">
                            <FormattedMessage id='user.SignUp.SignUp'/>
                        </Button>
                    </div>
                    
                </form>
            </Container>

        </Container>


    );
}

export default SignUp;