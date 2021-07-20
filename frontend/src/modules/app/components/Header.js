import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { FormattedMessage } from 'react-intl';
import { useSelector } from 'react-redux';
import { Link, withRouter} from 'react-router-dom';
import * as selectors from "../../user/selectors";
import { useDispatch } from 'react-redux';
import * as userActions from '../../user/actions';
import { useEffect, useState } from 'react';
import backend from '../../../backend';

const Header = () => {

  const userName = useSelector(selectors.getUserName);
  const dispatch = useDispatch();
  const [newNotifications, setNewNotifications] = useState(false);

  useEffect(() => {
    backend.notificationService.hasUserNewNotifications(
      userName,
      result => setNewNotifications(result));
  })

  const notificationColor = () => {
    if(newNotifications){
      return "#084FF1";
    }

    return "grey";
  }

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
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill={notificationColor()} class="bi bi-bell" viewBox="0 0 16 16">
                  <path d="M8 16a2 2 0 0 0 2-2H6a2 2 0 0 0 2 2zM8 1.918l-.797.161A4.002 4.002 0 0 0 4 6c0 .628-.134 2.197-.459 3.742-.16.767-.376 1.566-.663 2.258h10.244c-.287-.692-.502-1.49-.663-2.258C12.134 8.197 12 6.628 12 6a4.002 4.002 0 0 0-3.203-3.92L8 1.917zM14.22 12c.223.447.481.801.78 1H1c.299-.199.557-.553.78-1C2.68 10.2 3 6.88 3 6c0-2.42 1.72-4.44 4.005-4.901a1 1 0 1 1 1.99 0A5.002 5.002 0 0 1 13 6c0 .88.32 4.2 1.22 6z" />
                </svg>
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