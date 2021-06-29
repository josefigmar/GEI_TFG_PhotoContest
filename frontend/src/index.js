import React from 'react';
import ReactDOM from 'react-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import App from './modules/app/components/App';

import {IntlProvider} from 'react-intl';
import {initReactIntl} from './i18n';

/* Configure i18n. */
const {locale, messages} = initReactIntl();

/* Render application. */
ReactDOM.render(
    <IntlProvider locale={locale} messages={messages}>
      <App/>
    </IntlProvider>,
  document.getElementById('root')
);

