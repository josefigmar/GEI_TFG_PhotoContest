package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioTablaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/catalogo-usuarios")
public class ControladorUsuarios {

    @Autowired
    ServicioUsuario servicioUsuario;


    @GetMapping("/usuarios")
    public Block<UsuarioTablaDto> buscarUsuarios(@RequestParam(required = false) String nombreUsuario,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size){
        Block<UsuarioTablaDto> blockUsuarios = servicioUsuario.recuperarUsuarios(nombreUsuario, page, size);

        return blockUsuarios;
    }

    @GetMapping("usuarios/{idUsuario}")
    public UsuarioDto buscarUsuario(@PathVariable Long idUsuario) throws InstanceNotFoundException {
        UsuarioDto uDto = servicioUsuario.recuperarUsuario(idUsuario);

        return uDto;
    }
}
