import { combineReducers } from "redux";

import catalog from "../modules/contest";
import user from "../modules/user";

const rootReducer = combineReducers({
    catalog: catalog.reducer,
    user:  user.reducer
});

export default rootReducer;