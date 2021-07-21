import { useState, useEffect } from "react";
import { Container, Alert } from "react-bootstrap";
import { FormattedMessage } from "react-intl";
import { useParams } from "react-router";
import backend from "../../../backend";
import commonExportObj from "../../commons";
import Pager from "../../commons/components/Pager";

const Notifications = () => {


    const [notificationsData, setNotificationsData] = useState("");
    const [page, setPage] = useState(0);
    const SIZE = 5;
    const { userName } = useParams();

    const notificationPhoto = (databasePhoto) => {
        if (databasePhoto === null || databasePhoto === undefined) {
            return commonExportObj.constants.IMG_PHOTOCONTEST;
        }

        return databasePhoto;
    }

    useEffect(() => {
        backend.notificationService.findNotifications(
            {
                userName,
                page,
                size: SIZE
            },
            (result) => setNotificationsData(result)
        );
        // eslint-disable-next-line
    }, []);


    if (notificationsData === "") {
        return null;
    }

    if (notificationsData.items.length === 0) {
        return (
            <Alert variant="dark">
                <FormattedMessage id="user.Notifications.NoNotifications" />
            </Alert>
        )
    }

    return (
        <Container>
            <table className="table table-striped table-hover">
                <tbody>
                    {
                        notificationsData.items.map(notification =>
                            <tr>
                                <td className="align-middle"><img className="notificationImg" alt="notification" src={`data:image/jpeg;base64, ${notificationPhoto(notification.fotoNotificacion)}`} /></td>
                                <div>
                                    <td>
                                        {notification.nombreNotificacion}
                                    </td>
                                </div>
                                <div>
                                    <td className="align-middle">
                                        {notification.mensajeNotificacion}
                                    </td>
                                </div>
                                <td className="align-middle">
                                    {

                                        notification.leida ?
                                            <div>
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-check-circle" viewBox="0 0 16 16">
                                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                                    <path d="M10.97 4.97a.235.235 0 0 0-.02.022L7.477 9.417 5.384 7.323a.75.75 0 0 0-1.06 1.06L6.97 11.03a.75.75 0 0 0 1.079-.02l3.992-4.99a.75.75 0 0 0-1.071-1.05z" />
                                                </svg>
                                                &nbsp;
                                                <FormattedMessage id="user.Notifications.Read" />
                                            </div>



                                            :

                                            <div>
                                                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-circle" viewBox="0 0 16 16">
                                                    <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z" />
                                                </svg>
                                                &nbsp;
                                                <FormattedMessage id="user.Notifications.Unread" />
                                            </div>
                                    }
                                </td>


                            </tr>
                        )


                    }
                </tbody>
            </table>
            <Pager
                back={{
                    enabled: page >= 1,
                    onClick: () => backend.notificationService.findNotifications({ userName, page: page - 1, SIZE }, result => { setNotificationsData(result); setPage(page - 1) })
                }}
                next={{
                    enabled: notificationsData.existMoreItems,
                    onClick: () => backend.notificationService.findNotifications({ userName, page: page + 1, SIZE }, result => { setNotificationsData(result); setPage(page + 1) })
                }} />


        </Container>



    );
}

export default Notifications;