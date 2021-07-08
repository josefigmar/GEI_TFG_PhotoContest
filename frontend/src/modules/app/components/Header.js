import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import NavDropdown from 'react-bootstrap/NavDropdown';
import { FormattedMessage } from 'react-intl';
import { useSelector } from 'react-redux';
import { Link } from 'react-router-dom';
import * as selectors from "../../user/selectors";
import { useDispatch } from 'react-redux';
import * as userActions from '../../user/actions';

const Header = () => {

  const userName = useSelector(selectors.getUserName);
  const dispatch = useDispatch();

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

export default Header;