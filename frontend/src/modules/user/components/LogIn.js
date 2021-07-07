import { Container, Button} from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { Link, Redirect, useHistory} from "react-router-dom";
import { useState } from "react";
import backend from "../../../backend";

const LogIn = () => {

    const history = useHistory();

    const [nombreUsuario, setNombreUsuario] = useState("");
    const [contraseñaUsuario, setContraseñaUsuario] = useState("");

    const handleSubmit = event => {
        
        event.preventDefault();

        backend.userService.logIn(
            {
                nombreUsuario,
                contraseñaUsuario
            },
            () => history.push('/'),
            () => history.push('/users/logIn'),
        )
    }

    return(

        <Container>
            <Container className="signUpLogInDivFirst bg-light border border-secondary ">
                <br/>
                <div className="d-flex justify-content-center">
                    <h3>PhotoContest</h3>
                </div>
            </Container>
            <br/>
            <Container className="LogInDivSecond bg-light border border-secondary ">
                <form onSubmit={e => handleSubmit(e)}>
                    <br/>
                    <div className="d-flex justify-content-center">
                        <h6><FormattedMessage id='user.LogIn.Welcome'/></h6>
                    </div>
                    <br/>
                    
                    <div className="input-group mb-3">
                        <div className="input-group-prepend">
                            <span class="input-group-text" id="basic-addon1">@</span>
                        </div>
                        <FormattedMessage id='user.SignUp.UserName'>
                            {placeholder => <input value={nombreUsuario} placeholder={placeholder} onChange={e => setNombreUsuario(e.target.value)} required className="form-control"/>}
                        </FormattedMessage>
                        
                    </div>

                    <div className="input-group mb-3">
                        <FormattedMessage id='user.SignUp.Password'>
                            {placeholder => <input value={contraseñaUsuario} placeholder={placeholder} onChange={e => setContraseñaUsuario(e.target.value)} required className="form-control" type="password"/>}
                        </FormattedMessage>
                    </div>
                    <br/>
                    <div className="d-flex justify-content-center">
                        <Button type="submit" className="d-flex justify-content-center" variant="success">
                            <FormattedMessage id='user.SignUp.Login'/>
                        </Button>
                    </div>
                    <br/>
                    <div className="d-flex justify-content-center">
                        <Link to={"/users/signUp"}>
                            <FormattedMessage id='user.Login.ForgotPassword'/>
                        </Link>
                    </div>
                </form>
            </Container>
            <br/>
            <Container className="signUpLogInDivFirst bg-light border border-secondary ">
                <br/>
                <div className="d-flex justify-content-center">
                    <FormattedMessage id='user.Login.RegisterMsg'/>
                    &nbsp;
                    <Link to={"/users/signUp"}>
                        <FormattedMessage id='user.SignUp.SignUp'/>
                    </Link>
                </div>
            </Container>

        </Container>


    );
}

export default LogIn;