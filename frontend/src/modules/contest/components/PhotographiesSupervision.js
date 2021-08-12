import { useEffect, useState } from "react";
import { useParams } from "react-router";
import { Container, Spinner } from "react-bootstrap";
import Photographies from "./Photographies";
import Pager from "../../commons/components/Pager";
import backend from "../../../backend";
import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";

const PhotographiesSupervision = () => {

    const { contestName, contestId } = useParams();
    const [photographiesData, setPhotographiesData] = useState("");
    const [page, setPage] = useState(0);
    const size = 5;

    const retrievePhotographies = () => {
        backend.catalogService.supervise(
            {
                idConcurso: contestId
            },
            contestName,
            page,
            size,
            result => setPhotographiesData(result),
        )

    }

    useEffect(() => {
        retrievePhotographies();
    }, [])

    if (photographiesData === "") {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    return (
        <Container>
            <h5>
                <FormattedMessage id='contest.supervision.title' />
                :
                &nbsp;
                <Link to={`/contests/${contestName}/${contestId}`}>{contestName}</Link>
            </h5>
            <br/>

            <Photographies photographies={photographiesData.items} contestName={contestName} />
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => setPage(page - 1)
                }}
                next={{
                    enabled: photographiesData.existMoreItems,
                    onClick: () => setPage(page + 1)
                }} />
        </Container>
    )

}

export default PhotographiesSupervision;