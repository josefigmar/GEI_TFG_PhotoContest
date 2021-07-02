import { combineReducers } from "redux";

import catalog from "../modules/catalog"

const rootReducer = combineReducers({
    catalog: catalog.reducer
});

export default rootReducer;