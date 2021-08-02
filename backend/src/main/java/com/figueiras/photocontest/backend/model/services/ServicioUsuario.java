package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioLoginDto;

import javax.mail.internet.AddressException;
import java.util.List;

public interface ServicioUsuario {

    Block<Usuario> recuperarUsuarios(String nombre, int page, int size);
    List<String> recuperarNombresUsuarios();
    Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException, CamposIntroducidosNoValidosException,
            InstanceNotFoundException;
    Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException;
    Block<UsuarioSigueUsuario> recuperarSeguidoresDeUsuario(String nombreUsuario, int page, int size);
    Block<UsuarioSigueUsuario> recuperarSeguidosDeUsuario(String nombreUsuario, int page, int size);
    void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto, boolean isFromReset)
            throws IncorrectPasswordException;
    Usuario actualizarDatosUsuario(UsuarioDto usuarioDto);
    Usuario usuarioSigueAUsuario(String usuarioSeguidor, String usuarioSeguido) throws InstanceNotFoundException;
    Usuario usuarioDejaDeSeguirAUsuario(String usuarioSeguidor, String usuarioSeguido) throws InstanceNotFoundException;
    boolean sigueUsuarioAUsuario(String usuarioSeguidor, String usuarioSeguido);
    void enviarEnlaceRecuperacionContrasena(String nombreUsuarioDestinatario) throws InstanceNotFoundException;
    boolean comprobarEnlaceRecuperacionContrasena(String jwt) throws InstanceNotFoundException;
    void eliminarUsuario(String nombreUsuario) throws InstanceNotFoundException;
    Usuario actualizarUsuario(Usuario usuario);
}