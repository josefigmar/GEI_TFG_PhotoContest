import { FormattedMessage } from "react-intl";
import { Button } from "react-bootstrap";
import { useHistory } from "react-router";

const ContestButtons = () => {

    const history = useHistory();
    
    return (
        <div>
            <div className="d-flex justify-content-center">
                <h5>
                    <FormattedMessage id="app.RedirectHome.paragraphContests" />
                </h5>
            </div>
            <br />

            <div className="d-flex justify-content-center">
                <form onSubmit={() => history.push('/catalog/find-contests')}>
                    <Button type="submit" className="d-flex justify-content-center" variant="success">
                        <FormattedMessage id="app.RedirectHome.ButtonContests" />
                    </Button>
                </form>

                &emsp;
                <Button variant="success">
                    <FormattedMessage id="app.RedirectHome.ButtonCreateContest" />
                </Button>
            </div>
        </div>
    )
}

export default ContestButtons;
