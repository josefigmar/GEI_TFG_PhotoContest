import * as actionTypes from "./actionTypes";
import backend from "../../backend";

const findContestsCompleted = contestsSearch => ({
    type: actionTypes.FIND_CONTESTS_COMPLETED,
    contestsSearch
});

const findCategoriesCompleted = categoriesSearch => ({
    type: actionTypes.FIND_CATEGORIES_COMPLETED,
    categoriesSearch
});

export const clearContestsSearch = () => ({
    type: actionTypes.CLEAR_CONTESTS_SEARCH
});

const clearCategoriesSearch = () => ({
    type: actionTypes.CLEAR_CATEGORIES_SEARCH
});

export const findCategories = criteria => dispatch =>{

    dispatch(clearCategoriesSearch());
    backend.catalogService.findCategories(result =>
        dispatch(findCategoriesCompleted(result)));
}

export const findContests = criteria => dispatch =>{

    dispatch(clearContestsSearch());
    backend.catalogService.findContests(criteria, 
        result => dispatch(findContestsCompleted({criteria, result})));
}

export const previousFindProductsResultPage = criteria =>
    findContests({...criteria, page: criteria.page-1});

export const nextFindProductsResultPage = criteria =>
    findContests({...criteria, page: criteria.page+1});
