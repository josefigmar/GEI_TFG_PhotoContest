import { config, appFetch } from './appFetch';

export const findContests = ({ idCategoria, estado, nombre, page = 0, size }, onSuccess) => {

    let path = `/catalogo-concursos/concursos?page=${page}`;

    path += idCategoria ? `&idCategoria=${idCategoria}` : "";
    path += estado !== null ? `&estado=${estado}` : "";
    path += nombre ? `&nombre=${nombre}` : "";
    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const getContestData = ({ contestId, contestName }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestName}`;

    appFetch(path, config('POST', contestId), onSuccess);
}

export const getNumberPhotos = (contestName, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestName}/numeroFotos`;

    appFetch(path, config('GET'), onSuccess);
}

export const isOrganizador = ({ userName, contestId }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/${userName}/esOrganizador`;

    appFetch(path, config('GET'), onSuccess);
}

export const isContender = ({ userName, contestId }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/${userName}/esParticipante`;

    appFetch(path, config('GET'), onSuccess);
}

export const isJury = ({ userName, contestId }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/${userName}/esJurado`;

    appFetch(path, config('GET'), onSuccess);
}

export const findCategories = (onSuccess) => {

    let path = "/catalogo-concursos/categorias";

    appFetch(path, config('GET'), onSuccess);
}

export const findCategoriesOfContest = ({contestId}, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/categorias`;

    appFetch(path, config('GET'), onSuccess);
}

export const createContest = (contestData, userName, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos?userName=${userName}`;

    appFetch(path, config('POST', contestData), onSuccess, onErrors);
}

export const createCategory = (categoryData, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/categorias`;

    appFetch(path, config('POST', categoryData), onSuccess, onErrors);
}

export const findStaff = ({ contestId, page = 0, size }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/organizadores?page=${page}`;

    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findContenders = ({ contestId, page = 0, size }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/participantes?page=${page}`;

    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findJury = ({ contestId, page = 0, size }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestId}/jurado?page=${page}`;

    path += size ? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const deleteContest = ({ contestName, contestId }, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestName}`;

    appFetch(path, config('DELETE', contestId), onSuccess);
}

export const participate = (datosFotografia, contestname, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestname}/fotografias`;

    appFetch(path, config('POST', datosFotografia), onSuccess, onErrors);
}

export const supervise = (datosFotografia, contestName, page, size, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestName}/fotografias/enEspera?page=${page}`;

    path += size ? `&size=${size}` : "";

    appFetch(path, config('POST', datosFotografia), onSuccess, onErrors);
}

export const getPhotography = ({contestName, photographyId}, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestName}/fotografias/${photographyId}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const getPhotographies = ({contestName, page, size}, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestName}/fotografias?page=${page}&size=${size}`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const supervisePhotography = (superviseData, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${superviseData.nombreConcurso}/fotografias/${superviseData.idFotografia}/supervisar`;

    appFetch(path, config('POST', superviseData), onSuccess, onErrors);
}

export const getContestRolInfo = ({contestName, userName}, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestName}/${userName}/informacionRol`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const getVoteInfo = ({contestName, userName, photographyId}, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos/${contestName}/fotografias/${photographyId}/${userName}/informacionVoto`;

    appFetch(path, config('GET'), onSuccess, onErrors);
}

export const vote = (voteData, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${voteData.nombreConcurso}/fotografias/${voteData.idFotografia}/votar`;

    appFetch(path, config('POST', voteData), onSuccess);
}

export const getWinners = (contestName, ganadoras, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestName}/fotografias/ganadoras?size=${ganadoras}`;

    appFetch(path, config('GET'), onSuccess);
}

export const getVotes = ({contestName}, onSuccess) => {

    let path = `/catalogo-concursos/concursos/${contestName}/votos`;

    appFetch(path, config('GET'), onSuccess);
}

