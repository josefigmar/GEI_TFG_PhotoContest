package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsuarioParticipaConcursoTest {

    @Test
    public void crearInstanciaTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();

        Assertions.assertNotNull(usuarioParticipaConcurso);
    }

    @Test
    public void setUsuarioTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();
        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");

        usuarioParticipaConcurso.setUsuario(usuario);

        Assertions.assertEquals(usuarioParticipaConcurso.getUsuario(), usuario);
    }

    @Test
    public void setConcursoTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();
        Concurso concurso = new Concurso();

        usuarioParticipaConcurso.setConcurso(concurso);

        Assertions.assertEquals(usuarioParticipaConcurso.getConcurso(), concurso);
    }

    @Test
    public void setRolUsuarioTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();

        usuarioParticipaConcurso.setRolUsuario(RolUsuario.ESTANDAR);

        Assertions.assertEquals(usuarioParticipaConcurso.getRolUsuario(), RolUsuario.ESTANDAR);
    }

    @Test
    public void setFechaTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();

        usuarioParticipaConcurso.setFechaInicioParticipacion(UtilidadesParaPruebas.FECHA_HOY);

        Assertions.assertEquals(usuarioParticipaConcurso.getFechaInicioParticipacion(), UtilidadesParaPruebas.FECHA_HOY);
    }
}