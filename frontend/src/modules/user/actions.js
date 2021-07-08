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

export const logout = () => ({
    type: actionTypes.LOGOUT
});