import { useState, useEffect } from "react";
import { Button, Container, Form, Spinner } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";
import backend from "../../../backend";
import { useHistory, useParams } from "react-router";
import Errors from "../../commons/components/Errors";


const ResetPassword = () => {

    const intl = useIntl();
    const history = useHistory();
    const [backendErrors, setBackendErrors] = useState("");
    const { userName, token } = useParams();
    const [renderForm, setRenderForm] = useState(false);
    const [newPassword, setNewPassword] = useState("");
    const [repeatNewPassword, setRepeatNewPassword] = useState("");

    const checkPassword = () => {

        if (newPassword !== repeatNewPassword) {
            setBackendErrors({ "errorGlobal": intl.formatMessage({ id: 'user.SignUp.Error.PasswordsDoNotMatch' }) });
            return false;
        }

        return true;
    }

    const handleSubmit = (event) => {
        event.preventDefault();

        if (checkPassword()) {
            backend.userService.changuePassword(
                {
                    nombreUsuario: userName,
                    contraseñaNueva: newPassword,
                },
                // Is from reset is true
                true,
                () => history.push(`/`),
                errors => setBackendErrors(errors)
            )
        }
    }

    const isTokenOk = result => {
        result === true ? setRenderForm(true) : history.push(`/`);
    }

    useEffect(() => {
        backend.userService.isRecoveryTokenOk(
            {
                userName,
                token
            },
            result => isTokenOk(result),
            () => history.push(`/`)
        )
        // eslint-disable-next-line
    }, [])

    if (!renderForm) {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <Container>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <Form onSubmit={e => handleSubmit(e)}>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label><FormattedMessage id='user.ChanguePassword.NewPassword' /></Form.Label>
                    <Form.Control type="password" value={newPassword} onChange={e => setNewPassword(e.target.value)} />
                </Form.Group>

                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label><FormattedMessage id='user.ChanguePassword.RepeatPassword' /></Form.Label>
                    <Form.Control type="password" value={repeatNewPassword} onChange={e => setRepeatNewPassword(e.target.value)} />
                </Form.Group>

                <Button variant="primary" type="submit">
                    <FormattedMessage id='app.Commons.Save' />
                </Button>
            </Form>
        </Container>

    );
}

export default ResetPassword;