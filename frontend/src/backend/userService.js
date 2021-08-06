import { config, appFetch } from './appFetch';

export const findUsers = ({ nombre, page = 0, size }, onSuccess) => {

    let path = `/catalogo-usuarios/usuarios?page=${page}`;

    path += nombre ? `&nombreUsuario=${nombre}` : "";
    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findUserNames = (onSuccess) => {

    let path = `/catalogo-usuarios/usuarios/nombresUsuario`;

    appFetch(path, config('GET'), onSuccess);
}

export const findUser = ({ userName }, onSuccess) => {

    let path = `/catalogo-usuarios/usuarios/${userName}`;

    appFetch(path, config('GET'), onSuccess);
}

export const signUp = (usuarioDto, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/registrarse`;

    appFetch(path, config('POST', usuarioDto), onSuccess, onErrors);
}

export const logIn = (usuarioDto, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/iniciar-sesion`;

    appFetch(path, config('POST', usuarioDto), onSuccess, onErrors);
}

export const followersOfUser = ({ userName, page, size }, onSuccess) => {
    let path = `/catalogo-usuarios/usuarios/${userName}/followers`;

    path += page === 0 || page ? `?page=${page}` : "";
    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const followingOfUser = ({ userName, page, size }, onSuccess) => {
    let path = `/catalogo-usuarios/usuarios/${userName}/following`;

    path += page === 0 || page ? `?page=${page}` : "";
    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const changuePassword = (changuePasswordDto, isFromReset, onSuccess, onErrors) => {
    let path = `/catalogo-usuarios/usuarios/${changuePasswordDto.userName}/cambio-contrasena`;
    isFromReset ? path += `?isFromReset=${isFromReset}` : path += "";

    appFetch(path, config('POST', changuePasswordDto), onSuccess, onErrors);
}

export const updateUserData = (userUpdatedData, onSuccess, onErrors) => {
    let path = `/catalogo-usuarios/usuarios/${userUpdatedData.nombreUsuario}`;

    appFetch(path, config('PUT', userUpdatedData), onSuccess, onErrors);
}

export const userFollowsUser = (followerUserName, followedUserName, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${followerUserName}/seguir/${followedUserName}`;

    appFetch(path, config('POST'), onSuccess, onErrors);
}

export const userUnfollowsUser = (followerUserName, followedUserName, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${followerUserName}/dejar-seguir/${followedUserName}`;

    appFetch(path, config('POST'), onSuccess, onErrors);
}

export const doesUserFollowUser = (followerUserName, followedUserName, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${followerUserName}/sigue/${followedUserName}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const sendRecoverMail = (userName, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${userName}/recuperar-cuenta`;

    appFetch(path, config('POST'), onSuccess, onErrors);
}

export const isRecoveryTokenOk = (data, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${data.userName}/restablecer-contrasena/${data.token}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const deleteAccount = (userName, onSuccess, onErrors) => {

    let path = `/catalogo-usuarios/usuarios/${userName}/eliminar-cuenta`;

    appFetch(path, config('POST'), onSuccess, onErrors);
}