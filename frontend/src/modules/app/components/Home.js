import React, { useEffect } from 'react';
import { Container } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from 'react-redux';
import catalog from '../../catalog';
import Contests from "../../catalog/components/Contests";
import * as selectors from '../../catalog/selectors';
import RedirectHome from "./RedirectHome";


const Home = () =>{

    const contestsSearch = useSelector(selectors.contestsSearch);

    const dispatch = useDispatch();

    useEffect(() => {

      dispatch(catalog.actions.findContests({}));

    },[dispatch]);

    const existenConcursos = () => {
        if(contestsSearch !== null && contestsSearch.result.items.length !== 0){
            return true
        }
        return false;
    }

    const noExistenConcursos = () => {
        return(
            <div className="d-flex justify-content-center">
                <h4><FormattedMessage id="app.Home.NoContests"/></h4>
                <br/>
                <br/>
                <br/>
            </div>
        )
    }


    return(
        <Container>
            {existenConcursos()? <Contests contests={contestsSearch.result.items}/> : noExistenConcursos()}
            <RedirectHome/>
        </Container>
    );
}

export default Home;