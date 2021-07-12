import { useSelector } from "react-redux";
import { useState } from "react";
import * as userSelectors from "../selectors";
import { Button, Form } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import backend from "../../../backend";
import { useHistory } from "react-router";


const ChanguePassword = () => {

    const userName = useSelector(userSelectors.getUserName);
    const history = useHistory();
    const [oldPassword, setOldPassword] = useState("");
    const [newPassword, setNewPassword] = useState("");

    const handleSubmit = (event) =>{
        event.preventDefault();

        backend.userService.changuePassword(
            {
                nombreUsuario: userName,
                contraseñaAntigua: oldPassword,
                contraseñaNueva : newPassword,
            },
            () => history.push(`/users/${userName}`),
            () => history.push(`/users/${userName}/changue-password`)
        )

    }

    return (
        <Form onSubmit={e => handleSubmit(e)}>
            <Form.Group className="mb-3" controlId="formOldPassword">
                <Form.Label><FormattedMessage id='user.ChanguePassword.OldPassword' /></Form.Label>
                <Form.Control type="password" onChange={e => setOldPassword(e.target.value)}/>
                <Form.Text className="text-muted">
                </Form.Text>
            </Form.Group>

            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label><FormattedMessage id='user.ChanguePassword.NewPassword' /></Form.Label>
                <Form.Control type="password" onChange={e => setNewPassword(e.target.value)} />
            </Form.Group>

            <Button variant="primary" type="submit">
                <FormattedMessage id='app.Commons.Save' />
            </Button>
        </Form>
    );
}

export default ChanguePassword;