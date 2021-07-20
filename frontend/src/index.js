import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './modules/app/components/App';
import './App.css';
import throttle from 'lodash/throttle';
import configureStore from './store';
import { Provider } from 'react-redux';
import { saveState } from './store/localStorage';

const store = configureStore();

store.subscribe(throttle(() => {
  saveState({
    user: store.getState().user
  });
}, 1000));

/* Render application. */
ReactDOM.render(
  <Provider store={store}>
      <App/>
  </Provider>,
  document.getElementById('root')
);