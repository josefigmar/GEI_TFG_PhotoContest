import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './modules/app/components/App';
import './App.css';

import {IntlProvider} from 'react-intl';
import {initReactIntl} from './i18n';
import configureStore from './store';
import { Provider } from 'react-redux';

/* Configure i18n. */
const {locale, messages} = initReactIntl();


const store = configureStore();

/* Render application. */
ReactDOM.render(
  <Provider store={store}>
    <IntlProvider locale={locale} messages={messages}>
      <App/>
    </IntlProvider>
  </Provider>,
  document.getElementById('root')
);

