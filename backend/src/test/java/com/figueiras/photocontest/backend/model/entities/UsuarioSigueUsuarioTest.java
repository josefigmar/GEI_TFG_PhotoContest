package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioSigueUsuarioTest {

    @Test
    public void crearInstanciaTest() {

        UsuarioSigueUsuario usuarioSigueUsuario = new UsuarioSigueUsuario();

        Assertions.assertNotNull(usuarioSigueUsuario);
    }

    @Test
    public void setUsuarioSeguidorTest() {

        Usuario usuarioSeguidor = UtilidadesParaPruebas.crearUsuario("seguidor");
        UsuarioSigueUsuario usuarioSigueUsuario = new UsuarioSigueUsuario();

        usuarioSigueUsuario.setUsuarioSeguidor(usuarioSeguidor);

        Assertions.assertEquals(usuarioSigueUsuario.getUsuarioSeguidor(), usuarioSeguidor);
    }

    @Test
    public void setUsuarioSeguidoTest() {

        Usuario usuarioSeguido = UtilidadesParaPruebas.crearUsuario("seguido");
        UsuarioSigueUsuario usuarioSigueUsuario = new UsuarioSigueUsuario();

        usuarioSigueUsuario.setUsuarioSeguido(usuarioSeguido);

        Assertions.assertEquals(usuarioSigueUsuario.getUsuarioSeguido(), usuarioSeguido);
    }

    @Test
    public void setFechaSeguidoTest() {

        UsuarioSigueUsuario usuarioSigueUsuario = new UsuarioSigueUsuario();

        usuarioSigueUsuario.setFechaSeguida(UtilidadesParaPruebas.FECHA_HOY);

        Assertions.assertEquals(usuarioSigueUsuario.getFechaSeguida(), UtilidadesParaPruebas.FECHA_HOY);
    }
}