package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioTablaDto;

public interface ServicioUsuario {

    Block<UsuarioTablaDto> recuperarUsuarios(String nombre, int page, int size);
    UsuarioDto recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException;
}
