package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import com.figueiras.photocontest.backend.rest.common.JwtGenerator;
import com.figueiras.photocontest.backend.rest.common.JwtInfo;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalogo-usuarios")
public class ControladorUsuarios {

    @Autowired
    ServicioUsuario servicioUsuario;

    @Autowired
    private JwtGenerator jwtGenerator;

    @GetMapping("/usuarios")
    public Block<UsuarioTablaDto> buscarUsuarios(@RequestParam(required = false) String nombreUsuario,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size){
        Block<Usuario> blockUsuarios = servicioUsuario.recuperarUsuarios(nombreUsuario, page, size);

        Block<UsuarioTablaDto> usuarioDtoBlock =
                new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                        blockUsuarios.getExistMoreItems());

        return usuarioDtoBlock;
    }

    @GetMapping("/usuarios/{nombreUsuario}")
    public UsuarioDto buscarUsuario(@PathVariable String nombreUsuario) throws InstanceNotFoundException {
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);

        return UsuarioConversor.toUsuarioDto(usuario);
    }

     @GetMapping("/usuarios/{nombreUsuario}/followers")
     public Block<UsuarioTablaDto> buscarSeguidores(@PathVariable String nombreUsuario,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size){
        Block<UsuarioSigueUsuario> seguidoresDeUsuario =
                servicioUsuario.recuperarSeguidoresDeUsuario(nombreUsuario, page, size);

        List<UsuarioTablaDto> seguidoresDeUsuarioTabla = UsuarioConversor.toUsuariosTablaDto(
                seguidoresDeUsuario.getItems().stream().map(u -> u.getUsuarioSeguidor()).collect(Collectors.toList()));

        return new Block<>(seguidoresDeUsuarioTabla, seguidoresDeUsuario.getExistMoreItems());
     }

    @GetMapping("/usuarios/{nombreUsuario}/following")
    public Block<UsuarioTablaDto> buscarSeguidos(@PathVariable String nombreUsuario,
                                                   @RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "5") int size){
        Block<UsuarioSigueUsuario> seguidosUsuario =
                servicioUsuario.recuperarSeguidosDeUsuario(nombreUsuario, page, size);

        List<UsuarioTablaDto> seguidosDeUsuarioTabla = UsuarioConversor.toUsuariosTablaDto(
                seguidosUsuario.getItems().stream().map(u -> u.getUsuarioSeguido()).collect(Collectors.toList()));

        return new Block<>(seguidosDeUsuarioTabla, seguidosUsuario.getExistMoreItems());
    }

    @PostMapping("/registrarse")
    public ResponseEntity registrarUsuario(@RequestBody UsuarioDto usuarioDto){

        servicioUsuario.registrarUsuario(usuarioDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/iniciar-sesion")
    public UsuarioAutenticadoDto iniciarSesion(@RequestBody UsuarioLoginDto usuarioLoginDto){

        Usuario usuario = servicioUsuario.iniciarSesionUsuario(usuarioLoginDto);
        String jwt = generateServiceToken(usuario);

        return UsuarioConversor.toUsuarioAutenticadoDto(usuario, jwt);

    }

    private String generateServiceToken(Usuario usuario) {

        JwtInfo jwtInfo = new JwtInfo(usuario.getIdUsuario(), usuario.getNombreUsuario(),
                usuario.getRolUsuarioSistema().toString());

        return jwtGenerator.generate(jwtInfo);

    }
}
