import {config, appFetch} from './appFetch';

export const findContests = ({idCategoria, estado, nombre, page=0, size}, onSuccess) =>{
    
    let path = `/catalogo/concursos?page=${page}`;

    path += idCategoria? `&idCategoria=${idCategoria}` : "";
    path += estado? `&estado=${estado}` : "";
    path += nombre? `&nombre=${nombre}` : "";
    path += size? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findCategories = (onSuccess) =>{

    let path = "/catalogo/categorias";

    appFetch(path, config('GET'), onSuccess);
}