import { FormattedMessage } from "react-intl";
import { Link } from "react-router-dom";

const Photographies = (pageData) => {

    return (
        <table className="table table-striped table-hover">
            <thead>
                <tr>
                    <th scope="col">
                        <FormattedMessage id='contest.supervision.table.previsualization' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='contest.supervision.table.username' />
                    </th>
                    <th scope="col">
                        <FormattedMessage id='contest.supervision.table.category' />
                    </th>
                    <th>

                    </th>
                </tr>
            </thead>

            <tbody>
                {
                    pageData.photographies.map(photography =>

                        <tr key={Math.floor(Math.random() * 100)}>
                            {
                                photography.datosJpg === "" ?

                                    <td className="align-middle"><img alt="previsualization" src={"data:image/jpeg;base64," + photography.datosRaw} /></td>

                                    :

                                    <td className="align-middle"><img alt="previsualization" src={"data:image/jpeg;base64," + photography.datosJpg} /></td>

                            }

                            <td className="align-middle">
                                {`${photography.nombreUsuario}`}
                            </td>
                            <td className="align-middle">
                                {`${photography.nombreCategoria}`}
                            </td>
                            <td className="align-middle">
                                <Link to={`/contests/${pageData.contestName}/${photography.idConcurso}/photography/${photography.idFotografia}/supervise`}>
                                    <FormattedMessage id='contest.supervision.table.manage' />
                                </Link>
                            </td>
                        </tr>
                    )}
            </tbody>
        </table>
    );
}

export default Photographies;