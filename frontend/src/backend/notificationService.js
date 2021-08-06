import { config, appFetch } from './appFetch';

export const findNotifications = ({ userName, page = 0, size }, onSuccess) => {

    let path = `/catalogo-notificaciones/notificaciones/${userName}?page=${page}`;

    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const hasUserNewNotifications = (userName, onSuccess) => {
    let path = `/catalogo-notificaciones/notificaciones/${userName}/has-new`;

    appFetch(path, config('GET'), onSuccess);
}