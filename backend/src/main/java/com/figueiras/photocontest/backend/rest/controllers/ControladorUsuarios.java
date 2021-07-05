package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/catalogo-usuarios")
public class ControladorUsuarios {

    @Autowired
    ServicioUsuario servicioUsuario;


    @GetMapping("/usuarios")
    public Block<UsuarioDto> buscarUsuarios(@RequestParam(required = false) String nombreUsuario,
                                            @RequestParam(defaultValue = "0") int page,
                                            @RequestParam(defaultValue = "5") int size){
        Block<UsuarioDto> blockUsuarios = servicioUsuario.recuperarUsuarios(nombreUsuario, page, size);

        return blockUsuarios;
    }
}
