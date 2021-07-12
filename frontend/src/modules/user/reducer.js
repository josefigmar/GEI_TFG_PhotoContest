import * as actionTypes from "./actionTypes";
import {combineReducers} from "redux";

const initialState = {
    userData: null
}

const userData = (state = initialState.userData, action) => {

    switch (action.type) {

        case actionTypes.LOGOUT:
            return null;

        case actionTypes.USER_LOGIN_COMPLETED:
            return action.userData.usuarioDto;

        case actionTypes.USER_UPDATE_COMPLETED:
            return action.userData;

        default:
            return state;
    }
}

const reducer = combineReducers({
    userData,
});

export default reducer;