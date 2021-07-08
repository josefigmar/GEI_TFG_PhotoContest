import {createStore, applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';

import rootReducer from './rootReducer';
import { loadState } from './localStorage';

const configureStore = () => {

// This is the state of the aplication stored in the local storage of the browser
    const persistedState = loadState();

    const middlewares = [thunk];

    if (process.env.NODE_ENV !== 'production') {    
        middlewares.push(logger);
    }

    return createStore(
        rootReducer,
        persistedState,
        applyMiddleware(...middlewares)
    );

}

export default configureStore;