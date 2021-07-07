package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioAutenticadoDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioLoginDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioTablaDto;

public interface ServicioUsuario {

    Block<Usuario> recuperarUsuarios(String nombre, int page, int size);
    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    void registrarUsuario(UsuarioDto usuarioDto);
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto);
}
