import { Container, Button } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";
import constants from "../../commons";
import { useSelector, useDispatch } from "react-redux";
import * as userSelectors from '../../user/selectors';
import MyProfileButtons from "./MyProfileButtons";
import { useEffect, useState } from "react";
import backend from "../../../backend";
import * as userActions from "../actions";
import { useHistory } from "react-router";

const UserProfileDetails = ({ userData }) => {

    const dispatch = useDispatch();
    const history = useHistory();

    const userName = useSelector(userSelectors.getUserName);
    const isLoggedIn = useSelector(userSelectors.isUserLoggedIn);

    const [renderFollow, setRenderFollow] = useState('');

    const userPhoto = (databasePhoto) => {
        if (databasePhoto === null || databasePhoto === undefined) {
            return constants.IMG_PROFILE_DEFAULT;
        }

        return databasePhoto;
    }

    const handleFollow = (event) => {
        event.preventDefault();

        dispatch(userActions.followUser(
            userName,
            userData.nombreUsuario,
            () => { history.push("/temp"); history.push(`users/${userData.nombreUsuario}`) },
            () => { history.push("/temp"); history.push(`users/${userData.nombreUsuario}`) },
        ));
    }

    const handleUnfollow = (event) => {
        event.preventDefault();

        dispatch(userActions.unfollowUser(
            userName,
            userData.nombreUsuario,
            () => { history.push("/temp"); history.push(`users/${userData.nombreUsuario}`) },
            () => { history.push("/temp"); history.push(`users/${userData.nombreUsuario}`) },
        ));
    }

    useEffect(() => {
        if (isLoggedIn) {
            backend.userService.doesUserFollowUser(userName,
                userData.nombreUsuario,
                result => setRenderFollow(result),
                null
            );
        }

    }, [])

    return (
        <Container>
            <div className="d-flex justify-content-center">
                <img className="profileImg" alt="profile" src={`data:image/jpeg;base64, ${userPhoto(userData.fotoPerfil)}`} />
            </div>
            <br />
            <div className="d-flex justify-content-center">
                <h3>{`${userData.nombrePilaUsuario} ${userData.apellidosUsuario}`}</h3>
                &nbsp;
                &nbsp;
                &nbsp;
                {
                    // If the user is seeing his/her profile, the follow button doesn't have
                    // to exist.
                    isLoggedIn && userName === userData.nombreUsuario || renderFollow === '' ?

                        null

                        :

                        renderFollow ?

                            <form onSubmit={e => handleUnfollow(e)}>
                                <Button type="submit" className="d-flex justify-content-center" variant="danger">
                                    <FormattedMessage id="user.Profile.Unfollow" />
                                </Button>

                            </form>

                            :

                            <form onSubmit={e => handleFollow(e)}>
                                <Button type="submit" className="d-flex justify-content-center" variant="success">
                                    <FormattedMessage id="user.Profile.Follow" />
                                </Button>
                            </form>
                }
            </div>
            <br />
            <div className="d-flex justify-content-center">
                <h5>{`@${userData.nombreUsuario}`}</h5>
            </div>
            <br />
            {userData.biografiaUsuario !== null && <div className="d-flex justify-content-center">
                <h6>{`${userData.biografiaUsuario}`}</h6>
            </div>}
            <div className="d-flex justify-content-center">
                {
                    userData.enlaceFacebookUsuario !== null && userData.enlaceFacebookUsuario !== "" ?

                        <Link className="nav-link" to={{ pathname: userData.enlaceFacebookUsuario }} target="_blank">
                            Facebook &nbsp;
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-facebook" viewBox="0 0 16 16">
                                <path d="M16 8.049c0-4.446-3.582-8.05-8-8.05C3.58 0-.002 3.603-.002 8.05c0 4.017 2.926 7.347 6.75 7.951v-5.625h-2.03V8.05H6.75V6.275c0-2.017 1.195-3.131 3.022-3.131.876 0 1.791.157 1.791.157v1.98h-1.009c-.993 0-1.303.621-1.303 1.258v1.51h2.218l-.354 2.326H9.25V16c3.824-.604 6.75-3.934 6.75-7.951z" />
                            </svg>
                        </Link>

                        :

                        null
                }
                {
                    userData.enlaceTwitterUsuario !== null && userData.enlaceTwitterUsuario !== "" ?

                        <Link className="nav-link" to={{ pathname: userData.enlaceTwitterUsuario }} target="_blank">
                            Twitter &nbsp;
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-twitter" viewBox="0 0 16 16">
                                <path d="M5.026 15c6.038 0 9.341-5.003 9.341-9.334 0-.14 0-.282-.006-.422A6.685 6.685 0 0 0 16 3.542a6.658 6.658 0 0 1-1.889.518 3.301 3.301 0 0 0 1.447-1.817 6.533 6.533 0 0 1-2.087.793A3.286 3.286 0 0 0 7.875 6.03a9.325 9.325 0 0 1-6.767-3.429 3.289 3.289 0 0 0 1.018 4.382A3.323 3.323 0 0 1 .64 6.575v.045a3.288 3.288 0 0 0 2.632 3.218 3.203 3.203 0 0 1-.865.115 3.23 3.23 0 0 1-.614-.057 3.283 3.283 0 0 0 3.067 2.277A6.588 6.588 0 0 1 .78 13.58a6.32 6.32 0 0 1-.78-.045A9.344 9.344 0 0 0 5.026 15z" />
                            </svg>
                        </Link>

                        :

                        null

                }


            </div>
            {
                userName === userData.nombreUsuario ?

                    <div>
                        <br />
                        <MyProfileButtons userName={userData.nombreUsuario} />
                    </div>

                    :

                    null
            }

            <br />
            <div className="d-flex justify-content-center">
                <br />

                <Link className="nav-link" to={`/users/${userData.nombreUsuario}/following`} >
                    {`${userData.numeroSeguidos} Siguiendo`}
                </Link>

                <Link className="nav-link" to={`/users/${userData.nombreUsuario}/followers`} >
                    {`${userData.numeroSeguidores} Seguidores`}
                </Link>

            </div>
        </Container>
    )
}

export default UserProfileDetails;