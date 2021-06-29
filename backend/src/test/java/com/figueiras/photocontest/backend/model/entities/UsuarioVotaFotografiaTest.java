package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioVotaFotografiaTest {

    @Test
    public void crearInstanciaTest(){
        UsuarioVotaFotografia usuarioVotaFotografia = new UsuarioVotaFotografia();

        Assertions.assertNotNull(usuarioVotaFotografia);
    }

    @Test
    public void setUsuarioTest(){
        UsuarioVotaFotografia usuarioVotaFotografia = new UsuarioVotaFotografia();
        Usuario usuario = Utilidades.crearUsuario("root");

        usuarioVotaFotografia.setUsuario(usuario);

        Assertions.assertEquals(usuarioVotaFotografia.getUsuario(), usuario);
    }

    @Test
    public void setFotografiaTest(){
        UsuarioVotaFotografia usuarioVotaFotografia = new UsuarioVotaFotografia();
        Fotografia fotografia = new Fotografia();

        usuarioVotaFotografia.setFotografia(fotografia);

        Assertions.assertEquals(usuarioVotaFotografia.getFotografia(), fotografia);
    }

    @Test
    public void setFechaVotoTest(){
        UsuarioVotaFotografia usuarioVotaFotografia = new UsuarioVotaFotografia();

        usuarioVotaFotografia.setFechaVoto(Utilidades.FECHA_HOY);

        Assertions.assertEquals(usuarioVotaFotografia.getFechaVoto(), Utilidades.FECHA_HOY);
    }

    @Test
    public void setPuntucionTest(){
        UsuarioVotaFotografia usuarioVotaFotografia = new UsuarioVotaFotografia();
        Integer puntuacion = 2;

        usuarioVotaFotografia.setPuntuacion(puntuacion);

        Assertions.assertEquals(usuarioVotaFotografia.getPuntuacion(), puntuacion);
    }
}