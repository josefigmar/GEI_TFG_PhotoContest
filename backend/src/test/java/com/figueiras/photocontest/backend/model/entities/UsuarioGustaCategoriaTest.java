package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioGustaCategoriaTest {

    @Test
    public void crearInstanciaTest(){
        UsuarioGustaCategoria usuarioGustaCategoria = new UsuarioGustaCategoria();

        Assertions.assertNotNull(usuarioGustaCategoria);
    }

    @Test
    public void setUsuarioTest(){
        UsuarioGustaCategoria usuarioGustaCategoria = new UsuarioGustaCategoria();
        Usuario usuario = new Usuario();

        usuarioGustaCategoria.setUsuario(usuario);

        Assertions.assertEquals(usuarioGustaCategoria.getUsuario(), usuario);
    }

    @Test
    public void setCategoriaTest(){
        UsuarioGustaCategoria usuarioGustaCategoria = new UsuarioGustaCategoria();
        CategoriaFotografica categoria = new CategoriaFotografica();

        usuarioGustaCategoria.setCategoriaFotografica(categoria);

        Assertions.assertEquals(usuarioGustaCategoria.getCategoriaFotografica(), categoria);
    }
}
