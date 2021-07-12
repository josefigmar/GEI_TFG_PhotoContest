package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface ServicioUsuario {

    Block<Usuario> recuperarUsuarios(String nombre, int page, int size);
    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    void registrarUsuario(UsuarioDto usuarioDto);
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws InstanceNotFoundException;
    Block<UsuarioSigueUsuario> recuperarSeguidoresDeUsuario(String nombreUsuario, int page, int size);
    Block<UsuarioSigueUsuario> recuperarSeguidosDeUsuario(String nombreUsuario, int page, int size);
    void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto);
    Usuario actualizarDatosUsuario(UsuarioDto usuarioDto);
}
