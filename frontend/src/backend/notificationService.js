import { config, appFetch } from './appFetch';

export const findNotifications = ({ userName, page, size }, onSuccess) => {

    let path = `/catalogo-notificaciones/notificaciones/${userName}?page=${page}&size=${size}`;

    appFetch(path, config('GET'), onSuccess);
}

export const hasUserNewNotifications = (userName, onSuccess) => {
    let path = `/catalogo-notificaciones/notificaciones/${userName}/has-new`;

    appFetch(path, config('GET'), onSuccess);
}