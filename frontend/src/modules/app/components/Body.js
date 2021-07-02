import React from 'react';

import {Route, Switch} from 'react-router-dom';

import Home from './Home';

import { Container } from 'react-bootstrap';
import FindContests from '../../catalog/components/FindContests';
import FindContestsResult from '../../catalog/components/FindContestsResult';


const Body = () => {
    
   return (

        <Container>
            <br/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/catalog/find-contests"><FindContests/></Route>
                <Route><Home/></Route>
            </Switch>
        </Container>

    );

};

export default Body;
