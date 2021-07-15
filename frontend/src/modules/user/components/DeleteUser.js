import { useDispatch } from "react-redux";
import backend from "../../../backend";
import * as userActions from "../actions";
import { useHistory } from "react-router";
import { Button } from "react-bootstrap";
import { FormattedMessage, useIntl } from "react-intl";

const DeleteUser = ({userName}) => {

    const dispatch = useDispatch();
    const intl = useIntl();
    const history = useHistory();

    const handleDeleteAccount = () => {
        backend.userService.deleteAccount(
            userName,
            () => {dispatch(userActions.logout()); history.push("/"); },
            () => null
        );
    }

    return (
        <div className='delete-button d-flex justify-content-center' onClick={() => { if (window.confirm(intl.formatMessage({id:'user.Profile.DeleteMsg'}))) handleDeleteAccount() }}>
            <Button variant="danger">
                <FormattedMessage id="user.Profile.Delete" />
            </Button>
        </div>
    );
}

export default DeleteUser;