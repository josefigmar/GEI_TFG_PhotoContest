import { Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";

const MyProfileButtons = () => {

    return (
        <div className="d-flex justify-content-center">
            <form>
                <Button type="submit" className="d-flex justify-content-center" variant="success">
                    <FormattedMessage id="user.Profile.EditData" />
                </Button>
            </form>
            &nbsp;
            &nbsp;
            <form>
                <Button type="submit" className="d-flex justify-content-center" variant="success">
                    <FormattedMessage id="user.Profile.ChanguePassword" />
                </Button>
            </form>
        </div>
    )
}

export default MyProfileButtons;