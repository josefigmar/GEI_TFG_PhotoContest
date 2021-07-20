const getModuleState = state => state.user;

export const getUserData = state => getModuleState(state).userData
export const isUserLoggedIn = state => getUserData(state) !== null
export const getUserName = state => isUserLoggedIn(state)? getUserData(state).nombreUsuario : null;
export const getName = state => isUserLoggedIn(state)? getUserData(state).nombrePilaUsuario : null;
export const getLocale = state => isUserLoggedIn(state)? getUserData(state).lenguaje : null;