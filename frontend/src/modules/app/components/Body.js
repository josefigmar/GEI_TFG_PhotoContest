import React from 'react';
import { Route, Switch } from 'react-router-dom';
import { Container } from 'react-bootstrap';
import FindContests from '../../contest/components/FindContests';
import FindUsers from '../../user/components/FindUsers';
import User from '../../user/components/User';
import Home from './Home';
import SignUp from '../../user/components/SignUp';
import LogIn from '../../user/components/LogIn';
import ChanguePassword from '../../user/components/ChanguePassword';
import ChangueProfileData from '../../user/components/ChangueProfileData';
import RecoverUser from '../../user/components/RecoverUser';
import Notifications from '../../notificacion/components/Notifications';
import CreateContest from '../../contest/components/CreateContest';
import ResetPassword from '../../user/components/ResetPassword';
import * as selectors from "../../user/selectors";
import { useSelector } from 'react-redux';
import ContestDemux from '../../contest/components/ContestDemux';
import UserList from '../../commons/components/UserList';

const Body = () => {

    const isUserLoggedIn = useSelector(selectors.isUserLoggedIn);

    return (

        <Container>
            <br />
            <Switch>
                <Route exact path="/"><Home /></Route>
                <Route exact path="/catalog/find-contests"><FindContests /></Route>
                <Route exact path="/users/find-users"><FindUsers /></Route>
                <Route exact path="/users/signUp"><SignUp /></Route>
                <Route exact path="/users/login"><LogIn /></Route>
                <Route exact path="/users/recover"><RecoverUser /></Route>
                <Route exact path="/users/:userName"><User /></Route>
                <Route exact path="/users/:userName/changue-password"><ChanguePassword /></Route>
                <Route exact path="/users/:userName/reset-password/:token"><ResetPassword /></Route>
                <Route exact path="/users/:userName/changue-data"><ChangueProfileData /></Route>
                <Route exact path="/users/:userName/followers"><UserList /></Route>
                <Route exact path="/users/:userName/following"><UserList /></Route>
                <Route exact path="/contests/:contestName/:contestId/staffMembers"><UserList /></Route>
                <Route exact path="/contests/:contestName/:contestId/contenders"><UserList /></Route>
                <Route exact path="/contests/:contestName/:contestId/juryMembers"><UserList /></Route>
                <Route exact path="/notifications/:userName"><Notifications /></Route>
                {!isUserLoggedIn && <Route exact path="/contests/create-contest"><LogIn /></Route>}
                {isUserLoggedIn && <Route exact path="/contests/create-contest"><CreateContest /></Route>}
                {!isUserLoggedIn && <Route exact path="/contests/:contestName/:contestId"><LogIn /></Route>}
                {isUserLoggedIn && <Route exact path="/contests/:contestName/:contestId"><ContestDemux /></Route>}
                <Route><Home /></Route>
            </Switch>
        </Container>

    );

};

export default Body;
