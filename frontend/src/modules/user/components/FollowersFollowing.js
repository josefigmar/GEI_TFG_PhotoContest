import { useEffect, useState } from "react";
import backend from "../../../backend";
import { useParams} from "react-router";
import Pager from "../../commons/components/Pager";
import Users from "./Users";
import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";

const FollowersFollowing = () => {

    // This component is used in both followers and following use cases.
    const lastItem = window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);
    const { userName } = useParams();
    const [usersData, setUsersData] = useState("");
    const [page, setPage] = useState(0);
    const size = 5;

    const getTitle = () => {
        if (lastItem === "followers") {
            return 'user.Profile.Followers';
        }

        return 'user.Profile.Following';
    }

    // It returns the users to show on the list. It's needed
    // because this component is reused to show the followers and the people
    // that a user is following.
    useEffect(() => {
        if (lastItem === "followers") {
            backend.userService.followersOfUser(
                {
                    userName,
                    page,
                    size,
                },
                (result) => setUsersData(result)
            );
        } else {
            backend.userService.followingOfUser(
                {
                    userName,
                    page,
                    size,
                },
                (result) => setUsersData(result)
            );
        }
    // eslint-disable-next-line
    }, []);

    if (usersData === '') {
        return null;
    }

    return (
        <div>
            <div className="d-flex justify-content-center">
                <h5>
                    <Link to={`/users/${userName}`}>
                        {`@${userName}`}
                    </Link>
                    : <FormattedMessage id={getTitle()} />
                </h5>
            </div>

            <br />
            <Users users={usersData.items} />
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => setPage(page - 1)
                }}
                next={{
                    enabled: usersData.existMoreItems,
                    onClick: () => setPage(page + 1)
                }} />
        </div>

    );
}

export default FollowersFollowing;