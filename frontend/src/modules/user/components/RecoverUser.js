import { Container, Form, Button} from "react-bootstrap";
import { useState } from "react";
import { FormattedMessage } from "react-intl";
import backend from "../../../backend";
import { useHistory } from "react-router";


const RecoverUser = () => {

    const history = useHistory();

    const [userName, setUserName] = useState("");

    const handleSubmit = (event) => {

        event.preventDefault();
        backend.userService.sendRecoverMail(
            userName,
            () => history.push("/"),
            () => history.push("/")
        );
    }

    return (
        <Container>
            <Form>
                <Form.Group className="mb-3" controlId="nombreUsuario">
                    <Form.Label><FormattedMessage id='user.RecoverUser.UserName' /></Form.Label>
                    <Form.Control maxlength="50" onChange={e => setUserName(e.target.value)} />
                </Form.Group>
                <Button type="submit" variant="primary" onClick={e => handleSubmit(e)}>
                    <FormattedMessage id='user.RecoverUser.SendRecoveryLink' />
                </Button>
            </Form>
        </Container>
    );

}

export default RecoverUser;