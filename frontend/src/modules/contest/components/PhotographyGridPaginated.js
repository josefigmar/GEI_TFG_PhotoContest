import { useEffect } from "react";
import backend from "../../../backend";
import PhotographyGrid from "./PhotographyGrid";
import Pager from "../../commons/components/Pager";
import { Container, Spinner } from "react-bootstrap";
import { useState } from "react";
import { FormattedMessage } from "react-intl";

const PhotographyGridPaginated = (params) => {

    const [photographiesData, setPhotographiesData] = useState(-1);
    const [page, setPage] = useState(0);
    const size = 9;

    useEffect(() => {
        backend.catalogService.getPhotographies(
            {
                contestName: params.contestName,
                page,
                size
            },
            result => setPhotographiesData(result),
            () => null
        )
        // eslint-disable-next-line
    }, [page])

    if (photographiesData === -1) {
        return (
            <Container className="centering">
                <Spinner animation="border" role="status">
                </Spinner>
            </Container>
        )
    }

    if (photographiesData.items.length === 0) {
        return (
            <h4 className="centeredParagraph"><FormattedMessage id='contest.contestDetail.Body.OpenState.PhotographySection.NoPhotos'/></h4>
        )

    }


    return (
        <div>
            <PhotographyGrid photographies={photographiesData.items} />
            <br/>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => setPage(page - 1)
                }}
                next={{
                    enabled: photographiesData.existMoreItems,
                    onClick: () => setPage(page + 1)
                }} />
        </div>

    )

}

export default PhotographyGridPaginated;