import { useEffect, useState } from "react";
import { Button, Form } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useSelector, useDispatch } from "react-redux";
import { useHistory } from "react-router";
import * as userSelectors from "../selectors";
import * as userActions from "../actions";


const ChangueProfileData = () => {

    const history = useHistory();
    const dispatch = useDispatch();

    const userData = useSelector(userSelectors.getUserData);

    const [nombrePilaUsuario, setNombrePilaUsuario] = useState("");
    const [apellidosUsuario, setApellidosUsuario] = useState("");
    const [biografiaUsuario, setBiografiaUsuario] = useState("");
    const [enlaceTwitterUsuario, setEnlaceTwitterUsuario] = useState("");
    const [enlaceFacebookUsuario, setEnlaceFacebookUsuario] = useState("");
    const [fotoPerfil, setFotoPerfilUsuario] = useState("");

    useEffect(() => {
        setNombrePilaUsuario(userData.nombrePilaUsuario);
        setApellidosUsuario(userData.apellidosUsuario);
        setBiografiaUsuario(userData.biografiaUsuario);
        setEnlaceTwitterUsuario(userData.enlaceTwitterUsuario);
        setEnlaceFacebookUsuario(userData.enlaceFacebookUsuario);
        setFotoPerfilUsuario(userData.fotoPerfil);
    }, []);

    const updateProfilePhoto = (file) => {

        const reader = new FileReader();

        if (file) {
            reader.readAsDataURL(file);
            reader.onloadend = function () {
                let imgB64 = reader.result;
                let indexOfComma = imgB64.indexOf(",");
                imgB64 = imgB64.substr(indexOfComma + 1);
                setFotoPerfilUsuario(imgB64);
              }
        }
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        dispatch(userActions.updateUser(
            {
                nombreUsuario: userData.nombreUsuario,
                nombrePilaUsuario,
                apellidosUsuario,
                biografiaUsuario,
                enlaceTwitterUsuario,
                enlaceFacebookUsuario,
                fotoPerfil
            },
            () => { history.push(`users/${userData.nombreUsuario}`); window.localStorage.clear() },
            () => history.push(`users/${userData.nombreUsuario}/changue-data`),
        ));
    }

    return (
        <Form>
            <Form.Group className="mb-3" controlId="nombreUsuario">
                <Form.Label><FormattedMessage id='user.ChangueData.Name' /></Form.Label>
                <Form.Control value={nombrePilaUsuario} maxlength="50" onChange={e => setNombrePilaUsuario(e.target.value)} />
            </Form.Group>

            <Form.Group className="mb-3" controlId="apellidos">
                <Form.Label><FormattedMessage id='user.ChangueData.Surnames' /></Form.Label>
                <Form.Control value={apellidosUsuario} maxlength="100" onChange={e => setApellidosUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="Biografia">
                <Form.Label><FormattedMessage id='user.ChangueData.Biography' /></Form.Label>
                <Form.Control value={biografiaUsuario} maxlength="500" as="textarea" rows={3} onChange={e => setBiografiaUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="EnlaceTwitter">
                <Form.Label><FormattedMessage id='user.ChangueData.TwitterLink' /></Form.Label>
                <Form.Control value={enlaceTwitterUsuario} maxlength="200" onChange={e => setEnlaceTwitterUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3" controlId="EnlaceFacebook">
                <Form.Label><FormattedMessage id='user.ChangueData.FacebookLink' /></Form.Label>
                <Form.Control value={enlaceFacebookUsuario} maxlength="200" onChange={e => setEnlaceFacebookUsuario(e.target.value)} />
            </Form.Group>
            <Form.Group className="mb-3">
                <Form.Label><FormattedMessage id='user.ChangueData.ProfilePhoto'/></Form.Label>
                <Form.Control id="fotoPerfil" type="file"  onChange={e => updateProfilePhoto(e.target.files[0])}/>
            </Form.Group>
            <Button variant="primary" type="submit" onClick={e => handleSubmit(e)}>
                <FormattedMessage id="app.Commons.Save" />
            </Button>
        </Form>
    );
}

export default ChangueProfileData;