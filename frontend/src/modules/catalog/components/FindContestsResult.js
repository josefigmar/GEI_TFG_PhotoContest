import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import * as actions from "../actions";
import * as selectors from "../selectors";
import { FormattedMessage } from "react-intl";
import { Alert } from "react-bootstrap";
import Contests from "./Contests";
import Pager from "../../commons/components/Pager";

const FindContestsResult = () =>{

    const contestsSearch = useSelector(selectors.contestsSearch);
    const dispatch = useDispatch();

    useEffect(() => {
        dispatch(actions.clearContestsSearch);
    });

    if(!contestsSearch){
        return null;
    }

    if(contestsSearch.result.items.length === 0){
        return(
            <Alert  variant="dark">
                <FormattedMessage id="app.FindContestsResult.NoContests"/>
            </Alert>
        )
    }

    return(
        <div>
            <Contests contests={contestsSearch.result.items}/>
            <Pager 
                back={{
                    enabled: contestsSearch.criteria.page >= 1,
                    onClick: () => dispatch(actions.previousFindProductsResultPage(contestsSearch.criteria))}}
                next={{
                    enabled: contestsSearch.result.existMoreItems,
                    onClick: () => dispatch(actions.nextFindProductsResultPage(contestsSearch.criteria))}}/>
        </div>
    );
}

export default FindContestsResult;