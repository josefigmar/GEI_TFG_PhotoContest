import {config, appFetch} from './appFetch';

export const findUsers = ({nombre, page=0, size}, onSuccess) =>{
    
    let path = `/catalogo-usuarios/usuarios?page=${page}`;

    path += nombre? `&nombreUsuario=${nombre}` : "";
    path += size? `&size=${size}` : "";

    appFetch(path, config('GET'), onSuccess);
}

export const findUser = ({userName}, onSuccess) =>{
    
    let path = `/catalogo-usuarios/usuarios/${userName}`;

    appFetch(path, config('GET'), onSuccess);
}