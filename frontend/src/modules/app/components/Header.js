import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { FormattedMessage } from 'react-intl';
import { useSelector } from 'react-redux';
import { Link, withRouter } from 'react-router-dom';
import * as selectors from "../../user/selectors";
import { useDispatch } from 'react-redux';
import * as userActions from '../../user/actions';
import { useEffect, useState } from 'react';
import backend from '../../../backend';
import { useHistory } from 'react-router';

const Header = () => {

  const history = useHistory();
  const userName = useSelector(selectors.getUserName);
  const dispatch = useDispatch();
  const [newNotifications, setNewNotifications] = useState(false);

  useEffect(() => {
    backend.notificationService.hasUserNewNotifications(
      userName,
      result => setNewNotifications(result));
  })

  return (
    <div>
      <Navbar bg="dark" variant="dark">
        <Container>
          <Link className="navbar-brand" to="/">PhotoContest</Link>
          <Nav className="me-auto">
            <Link className="nav-link" to="/">
              <FormattedMessage id="app.Header.Home" />
            </Link>
            <Link className="nav-link" to="/catalog/find-contests">
              <FormattedMessage id="app.Header.Contests" />
            </Link>
            <Link className="nav-link" to="/users/find-users">
              <FormattedMessage id="app.Header.People" />
            </Link>
            {
              userName &&

              <Link className="nav-link" to={`/notifications/${userName}`}>
                <FormattedMessage id="app.Header.Notifications" />
                &nbsp;

                {
                  newNotifications ?

                    <svg xmlns="http://www.w3.org/2000/svg" width="12" height="12" fill="#e54720" class="bi bi-circle-fill" viewBox="0 0 16 16">
                      <circle cx="8" cy="8" r="8" />
                    </svg>

                    :

                    null
                }

              </Link>

            }

          </Nav>

          {
            userName ?

              <NavDropdown title={userName} id="basic-nav-dropdown">

                <Link className="dropdown-item" to={`/users/${userName}`}>
                  <FormattedMessage id="app.Header.MyProfile" />
                </Link>
                <Link className="dropdown-item" to="/users/signUp">
                  <FormattedMessage id="app.Header.Config" />
                </Link>
                <NavDropdown.Divider />
                <Link className="dropdown-item" onClick={() => dispatch(userActions.logout())} to="/">
                  <FormattedMessage id="app.Header.LogOut" />
                </Link>
              </NavDropdown>

              :

              <NavDropdown title={<FormattedMessage id="app.Header.Profile" />} id="basic-nav-dropdown">

                <Link className="dropdown-item" to="/users/logIn">
                  <FormattedMessage id="app.Header.LogIn" />
                </Link>
                <Link className="dropdown-item" to="/users/signUp">
                  <FormattedMessage id="app.Header.SignIn" />
                </Link>
              </NavDropdown>

          }

        </Container>
      </Navbar>
    </div>
  );
}

export default withRouter(Header);