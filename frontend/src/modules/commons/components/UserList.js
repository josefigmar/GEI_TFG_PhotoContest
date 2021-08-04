import { useEffect, useState } from "react";
import backend from "../../../backend";
import { useParams } from "react-router";
import Pager from "./Pager";
import Users from "../../user/components/Users";
import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";

const UserList = () => {

    // This component is used in both followers and following use cases.
    const lastItem = window.location.pathname.substring(window.location.pathname.lastIndexOf('/') + 1);
    const { userName, contestId, contestName } = useParams();
    const [usersData, setUsersData] = useState("");
    const [page, setPage] = useState(0);
    const size = 5;

    const getTitle = () => {
        let result;

        switch (lastItem) {
            case "followers": result = 'user.Profile.Followers'; break;
            case "following": result = 'user.Profile.Following'; break;
            case "staffMembers": result = 'contest.contestDetail.Header.contestInfoTable.Staff'; break;
            case "contenders": result = 'contest.contestDetail.Header.contestInfoTable.Contenders'; break;
            case "juryMembers": result = 'contest.contestDetail.Header.contestInfoTable.Jury'; break;
            default : return;
        }
        return result;
    }


    const retrieveFollowers = () => {
        backend.userService.followersOfUser(
            {
                userName,
                page,
                size,
            },
            (result) => setUsersData(result)
        );
    }

    const retrieveFollowing = () => {
        backend.userService.followingOfUser(
            {
                userName,
                page,
                size,
            },
            (result) => setUsersData(result)
        );
    }

    const retrieveStaffMembers = () => {
        backend.catalogService.findStaff(
            {
                contestId,
                page,
                size,
            },
            (result) => setUsersData(result)
        );
    }

    const retrieveContenders = () => {
        backend.catalogService.findContenders(
            {
                contestId,
                page,
                size,
            },
            (result) => setUsersData(result)
        );
    }

    const retrieveJuryMembers = () => {
        backend.catalogService.findJury(
            {
                contestId,
                page,
                size,
            },
            (result) => setUsersData(result)
        );
    }

    // It returns the users to show on the list. It's needed
    // because this component is reused to show the followers, the people
    // that a user is following, the staff members of a contest, the jury and the contenders.
    useEffect(() => {

        switch (lastItem) {

            case "followers": retrieveFollowers(); break;
            case "following": retrieveFollowing(); break;
            case "staffMembers": retrieveStaffMembers(); break;
            case "contenders": retrieveContenders(); break;
            case "juryMembers": retrieveJuryMembers(); break;
            default : return;
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
                    {
                        (lastItem === "followers" || lastItem === "following") ?
                            <div>
                                <Link to={`/users/${userName}`}>
                                    {`@${userName}`}
                                </Link>
                                : <FormattedMessage id={getTitle()} />
                            </div>
                            :
                            <div>
                                <Link to={`/contests/${contestName}/${contestId}`}>
                                    {`${contestName}`}
                                </Link>
                                : <FormattedMessage id={getTitle()} />
                            </div>
                    }

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

export default UserList;