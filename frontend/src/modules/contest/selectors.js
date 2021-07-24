const getModuleState = state => state.catalog;

export const contestsSearch = state => getModuleState(state).contestsSearch;
export const getCategories = state => getModuleState(state).categories;

