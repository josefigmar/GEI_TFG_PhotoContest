import { useState, useEffect } from 'react';
import { Container } from 'react-bootstrap';
import { FormattedMessage } from 'react-intl';
import FindUsersResult from './FindUsersResult';
import backend from '../../../backend';

const FindUsers = () => {

    const [nombreUsuario, setNombreUsuario] = useState("");
    const [findUsersResult, setFindUsersResult] = useState("");

    const paginationSize = 5;

    const handleSubmit = event => {
        event.preventDefault();
        backend.userService.findUsers(
            {
                nombre: nombreUsuario.trim(),
                page: 0,
                size: paginationSize
            }, result => setFindUsersResult({ result, nombre: nombreUsuario.trim(), page: 0, size: paginationSize }));
    }

    // Por defecto, se cargan los usuarios ordenados alfabéticamente por nombre de usuario
    // para que la apariencia de la página no sea tan vacía
    useEffect(() => {
        backend.userService.findUsers(
            {
                nombre: "",
                page: 0,
                size: paginationSize
            }, result => setFindUsersResult({
                result, nombre: "", page: 0, size: paginationSize
            }
            ));
        // eslint-disable-next-line
    }, [])

    return (

        <Container>
            <div className="d-flex justify-content-center">
                <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>
                    <FormattedMessage id='user.FindUsers.PlaceHolder'>
                        {placeholder => <input id="keywords" placeholder={placeholder} type="text" className="form-control mr-sm-2" value={nombreUsuario} onChange={e => setNombreUsuario(e.target.value)} />}
                    </FormattedMessage>

                    <button placeholder="" type="submit" className="btn btn-primary my-2 my-sm-0" onSubmit={e => setNombreUsuario(e.target.value)}>
                        <FormattedMessage id='global.buttons.search' />
                    </button>
                </form>


            </div>
            <br />
            <br />
            <br />
            <div>
                <FindUsersResult findUsersResult={findUsersResult} updateFindUsersResult={setFindUsersResult} />
            </div>
        </Container>

    );

}

export default FindUsers;