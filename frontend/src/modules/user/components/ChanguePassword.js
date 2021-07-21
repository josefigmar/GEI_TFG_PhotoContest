import { useSelector } from "react-redux";
import { useState } from "react";
import * as userSelectors from "../selectors";
import { Button, Container, Form } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";
import backend from "../../../backend";
import { useHistory } from "react-router";
import Errors from "../../commons/components/Errors";


const ChanguePassword = () => {

    const intl = useIntl();
    const userName = useSelector(userSelectors.getUserName);
    const history = useHistory();
    const [backendErrors, setBackendErrors] = useState("");
    const [oldPassword, setOldPassword] = useState("");
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
                    contraseñaAntigua: oldPassword,
                    contraseñaNueva: newPassword,
                },
                () => history.push(`/users/${userName}`),
                errors => setBackendErrors(errors)
            )
        }
    }

    return (
        <Container>
            <Errors errors={backendErrors} onClose={() => setBackendErrors(null)} />
            <Form onSubmit={e => handleSubmit(e)}>
                <Form.Group className="mb-3" controlId="formOldPassword">
                    <Form.Label><FormattedMessage id='user.ChanguePassword.OldPassword' /></Form.Label>
                    <Form.Control type="password" onChange={e => setOldPassword(e.target.value)} />
                    <Form.Text className="text-muted">
                    </Form.Text>
                </Form.Group>

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

export default ChanguePassword;