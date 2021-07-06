import React from 'react';
import {Route, Switch} from 'react-router-dom';
import { Container } from 'react-bootstrap';
import FindContests from '../../catalog/components/FindContests';
import FindUsers from '../../user/components/FindUsers';
import User from '../../user/components/User';
import Home from './Home';
import SignUp from '../../user/components/SignUp';
import LogIn from '../../user/components/LogIn';


const Body = () => {
    
   return (

        <Container>
            <br/>
            <Switch>
                <Route exact path="/"><Home/></Route>
                <Route exact path="/catalog/find-contests"><FindContests/></Route>
                <Route exact path="/users/find-users"><FindUsers/></Route>
                <Route exact path="/users/signUp"><SignUp/></Route>
                <Route exact path="/users/login"><LogIn/></Route>
                <Route exact path="/users/:userName"><User/></Route>

                <Route><Home/></Route>
            </Switch>
        </Container>

    );

};

export default Body;
