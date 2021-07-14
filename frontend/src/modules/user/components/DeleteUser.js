import { useDispatch } from "react-redux";
import backend from "../../../backend";
import * as userActions from "../actions";
import { useHistory } from "react-router";
import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";

const DeleteUser = ({userName}) => {

    const dispatch = useDispatch();
    const history = useHistory();

    const handleDeleteAccount = () => {
        backend.userService.deleteAccount(
            userName,
            () => {dispatch(userActions.logout()); history.push("/"); },
            () => null
        );
    }

    return (
        <div className='delete-button d-flex justify-content-center' onClick={() => { if (window.confirm("¿Está seguro de que desea eliminar esta cuenta?")) handleDeleteAccount() }}>
            <Button variant="danger">
                <FormattedMessage id="user.Profile.Delete" />
            </Button>
        </div>
    );
}

export default DeleteUser;