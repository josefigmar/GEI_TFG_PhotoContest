import Header from './Header';
import Footer from './Footer';
import { InitReactIntl } from '../../../i18n';
import { IntlProvider } from 'react-intl';
import { BrowserRouter as Router } from 'react-router-dom';
import Body from './Body';
import { useState, useEffect} from 'react';
import { useSelector } from 'react-redux';
import * as userSelectors from "../../user/selectors";

const App = () => {

  /* The browser locale and all the messages are retrieved */
  let { locale, messages } = InitReactIntl();

  /* handy function that eliminates the country code from the browser locale */
  const localeWithoutRegionCode = locale.toLowerCase().split(/[_-]+/)[0];

  /* The user locale is obtained if there is an user logged in */
  const userLocale = useSelector(userSelectors.getLocale);
  const [appLocale, setAppLocale] = useState(locale);
  const [appMessages, setAppMessages] = useState(messages[locale] || messages[localeWithoutRegionCode] || messages['en']);

  /* The user locale comes from the backend with a code from 0 to 2, this function returns the locale value of the code*/
  const i18nUSerLocales = code => {
    switch (code) {
      case 0: return "es";
      case 1: return "gl";
      case 2: return "en";
      default: return "es";
    }
  }

  /* Every time App is rendered, the sistem checks if there is a user locale. If so, it changues the state of the component so it causes it to re render with
  The user locale. If not, the browser and ultimately the default locale is used */
  useEffect (() => {

    if (userLocale !== null) {
      let appLocale = i18nUSerLocales(userLocale);
      setAppLocale(appLocale);
      setAppMessages(messages[appLocale] || messages[localeWithoutRegionCode] || messages['en']);
    } else {
      setAppLocale(locale);
      setAppMessages( messages[locale] || messages[localeWithoutRegionCode] || messages['en']);
    }
    // eslint-disable-next-line
  }, [userLocale])

  return (
    <div>
      <IntlProvider locale={appLocale} messages={appMessages}>
        <Router>
          <div>
            <Header />
            <Body />
          </div>
          <Footer />
        </Router>
      </IntlProvider>
    </div>
  );
}

export default App;