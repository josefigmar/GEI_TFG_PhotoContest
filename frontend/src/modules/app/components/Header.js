import Navbar from 'react-bootstrap/Navbar';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import NavDropdown from 'react-bootstrap/NavDropdown';
import {FormattedMessage} from 'react-intl';
import {Link} from 'react-router-dom';

const Header = () =>{
    

    return(
        <div>
        <Navbar bg="dark" variant="dark">
          <Container>
          <Link className="navbar-brand" to="/">PhotoContest</Link>
          <Nav className="me-auto">
            <Link className="nav-link" to="/">
                <FormattedMessage id="app.Header.Home"/>
            </Link>
            <Link className="nav-link" to="/catalog/find-contests">
                <FormattedMessage id="app.Header.Contests"/>
            </Link>
            <Link className="nav-link" to="/users/find-users">
                <FormattedMessage id="app.Header.People"/>
            </Link>
          </Nav>
          <NavDropdown title={<FormattedMessage id="app.Header.Profile"/>} id="basic-nav-dropdown">

            <Link className="dropdown-item" to="/">
              <FormattedMessage id="app.Header.LogIn"/>
            </Link>
            <Link className="dropdown-item" to="/">
              <FormattedMessage id="app.Header.SignIn"/>
            </Link>

            {/*<NavDropdown.Item href="#action/3.2">Another action</NavDropdown.Item>
            <NavDropdown.Item href="#action/3.3">Something</NavDropdown.Item>
            <NavDropdown.Divider />
            <NavDropdown.Item href="#action/3.4">Separated link</NavDropdown.Item>*/}
          </NavDropdown>
          </Container>
        </Navbar>
      </div>
    );
}

export default Header;