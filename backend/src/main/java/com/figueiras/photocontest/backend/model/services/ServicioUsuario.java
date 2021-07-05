package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;

public interface ServicioUsuario {

    Block<UsuarioDto> recuperarUsuarios(String nombre, int page, int size);
}
