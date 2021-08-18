import Users from "./Users";
import { Alert} from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import backend from "../../../backend";
import Pager from "../../commons/components/Pager";

const FindUsersResult = ({findUsersResult, updateFindUsersResult}) =>{

    const updateUsers = (data) =>{
        updateFindUsersResult(data);
    }

    if(findUsersResult === ''){
        return null;
    }

    if(findUsersResult.result.items.length === 0){

        return(
            <Alert  variant="dark">
                <FormattedMessage id="app.FindUsers.NoUsers"/>
            </Alert>
        )
    };

    return(
        <div>
            <Users users={findUsersResult.result.items}/>
            <Pager 
                back={{
                    enabled: findUsersResult.page >= 1,
                    onClick: () => backend.userService.findUsers({nombre: findUsersResult.nombre, page: findUsersResult.page - 1, size: findUsersResult.size}, result => updateUsers({...findUsersResult, result, page: findUsersResult.page-1}))}}
                next={{
                    enabled: findUsersResult.result.existMoreItems,
                    onClick: () => backend.userService.findUsers({nombre: findUsersResult.nombre, page: findUsersResult.page + 1, size: findUsersResult.size}, result => updateUsers({...findUsersResult, result, page: findUsersResult.page+1}))}}/>
                    <br/>
                    <br/>
                    <br/>
                    <br/>
        </div>
    );
}

export default FindUsersResult;