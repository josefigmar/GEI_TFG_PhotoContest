import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useHistory } from "react-router";

const MyProfileButtons = ({userName}) => {

    const history = useHistory();

    return (
        <div className="d-flex justify-content-center">
            <form onSubmit={() => history.push(`/users/${userName}/changue-data`)}>
                <Button type="submit" className="d-flex justify-content-center" variant="success">
                    <FormattedMessage id="user.Profile.EditData" />
                </Button>
            </form>
            &nbsp;
            &nbsp;
            <form onSubmit={() => history.push(`/users/${userName}/changue-password`)}>
                <Button type="submit" className="d-flex justify-content-center" variant="success">
                    <FormattedMessage id="user.Profile.ChanguePassword" />
                </Button>
            </form>
        </div>
    )
}

export default MyProfileButtons;