import {combineReducers} from "redux";

import * as actionTypes from "./actionTypes";

const initialState = {
    contestsSearch: null,
    categories: null
};

const contestsSearch = (state = initialState.contestsSearch, action) => {

    switch (action.type) {

        case actionTypes.FIND_CONTESTS_COMPLETED:
            return action.contestsSearch;

        case actionTypes.CLEAR_CATEGORIES_SEARCH:
            return null;

        default:
            return state;
    }
}

const categories = (state = initialState.categories, action) => {

    switch (action.type) {

        case actionTypes.FIND_CATEGORIES_COMPLETED:
            return action.categoriesSearch;

        default:
            return state;
    }
}

const reducer = combineReducers({
    contestsSearch,
    categories
});

export default reducer;