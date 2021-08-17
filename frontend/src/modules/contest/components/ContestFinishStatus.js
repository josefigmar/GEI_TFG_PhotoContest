import ContestHeader from "./ContestHeader";
import ResultPresentation from "./ResultPresentation";

const ContestFinishStatus = ({ contestData }) => {

    return (
        <div>
            <ContestHeader contestData={contestData} />
            <hr />
            <ResultPresentation contestData={contestData}/>
            
        </div>
    )

}

export default ContestFinishStatus;