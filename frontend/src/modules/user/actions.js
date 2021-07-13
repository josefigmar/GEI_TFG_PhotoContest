import backend from "../../backend";
import * as actionTypes from "./actionTypes";

const loginCompleted = userData => ({
    type: actionTypes.USER_LOGIN_COMPLETED,
    userData
});

export const login = (userLoginData, onSuccess, onErrors, reauthenticationCallback) => dispatch => {
    backend.userService.logIn(userLoginData,
        userData => {
            dispatch(loginCompleted(userData));
            onSuccess();
        },
        onErrors,
        reauthenticationCallback);
};

const userUpdateCompleted = userData => ({
    type: actionTypes.USER_UPDATE_COMPLETED,
    userData
});

export const updateUser = (userUpdateData, onSuccess, onErrors) => dispatch => {
    backend.userService.updateUserData(userUpdateData,
        userData => {
            dispatch(userUpdateCompleted(userData));
            onSuccess();
        },
        onErrors,

    );
}

const relationshipUpdateCompleted = userData => ({
    type: actionTypes.RELATIONSHIP_UPDATE_COMPLETED,
    userData
});

export const followUser = (followerUserName, followedUserName, onSuccess, onErrors) => dispatch => {
    backend.userService.userFollowsUser(followerUserName,
        followedUserName,
        userData => {
            dispatch(relationshipUpdateCompleted(userData));
            onSuccess();
        },
        onErrors,
    );
}

export const unfollowUser = (followerUserName, followedUserName, onSuccess, onErrors) => dispatch => {
    backend.userService.userUnfollowsUser(followerUserName,
        followedUserName,
        userData => {
            dispatch(relationshipUpdateCompleted(userData));
            onSuccess();
        },
        onErrors,
    );
}

export const logout = () => ({
    type: actionTypes.LOGOUT
});