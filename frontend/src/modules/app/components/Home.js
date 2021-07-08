import React, { useEffect } from 'react';
import { Container } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useDispatch, useSelector } from 'react-redux';
import catalog from '../../catalog';
import Contests from "../../catalog/components/Contests";
import * as catalogSelectors from '../../catalog/selectors';
import * as userSelectors from '../../user/selectors';
import RedirectHome from "./RedirectHome";


const Home = () => {

    const contestsSearch = useSelector(catalogSelectors.contestsSearch);
    const userName = useSelector(userSelectors.getName);

    const dispatch = useDispatch();

    useEffect(() => {

        dispatch(catalog.actions.findContests({}));

    }, [dispatch]);

    const existenConcursos = () => {
        if (contestsSearch !== null && contestsSearch.result.items.length !== 0) {
            return true
        }
        return false;
    }

    const noExistenConcursos = () => {
        return (
            <div className="d-flex justify-content-center">
                <h4><FormattedMessage id="app.Home.NoContests" /></h4>
                <br />
                <br />
                <br />
            </div>
        )
    }


    return (
        <Container>
            {
            userName ?
                <div className="d-flex justify-content-center">
                    <h4>
                        <FormattedMessage id="app.Home.WelcomeBack"/>{`, ${userName}`}
                    </h4>
                </div>
            :

            null

            }
            {
            existenConcursos() ?

                <div>
                    <hr />
                    <div className="d-flex justify-content-center">
                        <h5>
                            <FormattedMessage id="app.Home.Contests" />
                        </h5>
                    </div>
                    <br />

                    <Contests contests={contestsSearch.result.items} />
                </div>
                :
                noExistenConcursos()
            }
            <RedirectHome />
        </Container>
    );
}

export default Home;