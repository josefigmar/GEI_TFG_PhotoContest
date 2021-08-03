import ContestPresentation from "./ContestPresentation";
import ContestInfoTable from "./ContestInfoTable";
import ContestHeaderButtons from "./ContestHeaderButtons";

const ContestHeader = ({ contestData }) => {

    return (
        <div className="contestHeader">
            <div className="contestPresentationAndButtons">
                <ContestPresentation contestData={contestData} />
                <ContestHeaderButtons contestData={contestData} />
            </div>
            &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
            <ContestInfoTable contestData={contestData} />


        </div>
    )
}

export default ContestHeader;