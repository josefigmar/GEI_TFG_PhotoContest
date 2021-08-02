import {config, appFetch} from './appFetch';

export const findContests = ({idCategoria, estado, nombre, page=0, size}, onSuccess) =>{
    
    let path = `/catalogo-concursos/concursos?page=${page}`;

    path += idCategoria? `&idCategoria=${idCategoria}` : "";
    path += estado !==null? `&estado=${estado}` : "";
    path += nombre? `&nombre=${nombre}` : "";
    path += size? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findCategories = (onSuccess) =>{

    let path = "/catalogo-concursos/categorias";

    appFetch(path, config('GET'), onSuccess);
}

export const createContest = (contestData, userName, onSuccess, onErrors) => {

    let path = `/catalogo-concursos/concursos?userName=${userName}`;

    appFetch(path, config('POST', contestData), onSuccess, onErrors);
}