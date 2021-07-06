import {useDispatch} from 'react-redux';
import * as actions from "../actions";
import { useState, useEffect} from 'react';
import { Container} from 'react-bootstrap';
import CategorySelector from './CategorySelector';
import StatusSelector from './StatusSelector';
import { FormattedMessage } from 'react-intl';
import {useHistory} from 'react-router-dom';
import FindContestsResult from './FindContestsResult';

const FindContests = () => {

    const dispatch = useDispatch();
    const history = useHistory();
    const [idCategoria, setIdCategoria] = useState("");
    const [idEstado, setIdEstado] = useState("");
    const [nombreConcurso, setNombreConcurso] = useState("");

    const handleSubmit = event => {
        event.preventDefault();
        dispatch(actions.findContests(
            {idCategoria: toNumber(idCategoria), 
                estado: toNumber(idEstado),
                nombre: nombreConcurso.trim(),
                page: 0,
                size: 5}));
        history.push('/catalog/find-contests');
    }

    const toNumber = value => value.length > 0 ? Number(value) : null;

    return (

        <Container>
            <div className="d-flex justify-content-center">
                <form className="form-inline mt-2 mt-md-0" onSubmit={e => handleSubmit(e)}>

                    <CategorySelector id="categoryId" className="custom-select my-1 mr-sm-2"
                        value={idCategoria} onChange={e => setIdCategoria(e.target.value)}/>

                    <StatusSelector id="statusId" className="custom-select my-1 mr-sm-2"
                        value={idEstado} onChange={e => setIdEstado(e.target.value)}/>

                    <FormattedMessage  id='app.FindContests.PlaceHolder'>
                        {placeholder=>  <input className="form-control mr-sm-2" id="keywords" placeholder={placeholder} value={nombreConcurso} onChange={e => setNombreConcurso(e.target.value)}/>}
                    </FormattedMessage> 

                    <button type="submit" className="btn btn-primary my-2 my-sm-0">
                        <FormattedMessage id='global.buttons.search'/>
                    </button>
                </form>
                &nbsp;
                <form className="form-inline mt-2 mt-md-0" onSubmit={() => dispatch(actions.clearContestsSearch)}>
                    <button type="submit" className="btn btn-primary my-2 my-sm-0">
                        <FormattedMessage id='app.FindContests.CleanSearch'/>
                    </button>
                </form>
            </div>
            <br/>
            <br/>
            <br/>
            <div>
                <FindContestsResult/>
            </div>
        </Container>

    );

}

export default FindContests;