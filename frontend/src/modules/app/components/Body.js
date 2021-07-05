import React from 'react';
import {Route, Switch} from 'react-router-dom';
import { Container } from 'react-bootstrap';
import FindContests from '../../catalog/components/FindContests';
import FindUsers from '../../user/components/FindUsers';
import Home from './Home';


const Body = () => {
    
   return (

        <Container>
            <br/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/catalog/find-contests"><FindContests/></Route>
                <Route exact path="/users/find-users"><FindUsers/></Route>
                <Route><Home/></Route>
            </Switch>
        </Container>

    );

};

export default Body;
