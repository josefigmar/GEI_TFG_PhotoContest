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
    public void setEmbeddedIdTest() {
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();
        UsuarioParticipaConcursoPK upPk = new UsuarioParticipaConcursoPK();

        usuarioParticipaConcurso.setUsuarioParticipaConcursoPK(upPk);

        Assertions.assertEquals(usuarioParticipaConcurso.getUsuarioParticipaConcursoPK(), upPk);
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
    public void setRolUsuarioConcursoTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();

        usuarioParticipaConcurso.setRolUsuarioConcurso(RolUsuarioConcurso.INSCRITO);

        Assertions.assertEquals(usuarioParticipaConcurso.getRolUsuarioConcurso(), RolUsuarioConcurso.INSCRITO);
    }

    @Test
    public void setFechaTest(){
        UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();

        usuarioParticipaConcurso.setFechaInicioParticipacion(UtilidadesParaPruebas.FECHA_HOY);

        Assertions.assertEquals(usuarioParticipaConcurso.getFechaInicioParticipacion(), UtilidadesParaPruebas.FECHA_HOY);
    }
}