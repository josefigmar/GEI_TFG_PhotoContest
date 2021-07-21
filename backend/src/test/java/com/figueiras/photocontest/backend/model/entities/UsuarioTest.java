package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

public class UsuarioTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        validator = vFactory.getValidator();
    }

    @Test
    public void crearInstanciaTest() {
        Usuario usuario = new Usuario();

        Assertions.assertNotNull(usuario);
    }

    @Test
    public void crearInstanciaRolPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getRolUsuarioSistema(), RolUsuarioSistema.ESTANDAR);
    }

    @Test
    public void crearInstanciaListaSeguidosPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getUsuariosQueSigue(), new HashSet<>());
    }

    @Test
    public void crearInstanciaListaSeguidoresPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getUsuariosQueLoSiguen(), new HashSet<>());
    }

    @Test
    public void crearInstanciaNotificacionesPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getNotificacionesUsuario(), new HashSet<>());
    }

    @Test
    public void crearInstanciaConcursosEnLosQuePArticipaPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getConcursosEnLosQueParticipa(), new HashSet<>());
    }

    @Test
    public void crearInstanciaCuentaEliminadaPorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertFalse(usuario.isCuentaEliminada());
    }

    @Test
    public void crearInstanciaLenguajePorDefectoTest() {
        Usuario usuario = new Usuario();

        Assertions.assertEquals(usuario.getLenguaje(), Lenguaje.es);
    }

    @Test
    public void setNombreTest() {
        Usuario usuario = new Usuario();
        String nombre = "name";

        usuario.setNombreUsuario(nombre);

        Assertions.assertEquals(usuario.getNombreUsuario(), nombre);
    }

    @Test
    public void setNombreCortoTest() {
        Usuario usuario = new Usuario();
        String nombre = UtilidadesParaPruebas.CADENA_VACIA;

        usuario.setNombreUsuario(nombre);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setNombreLargoTest() {
        Usuario usuario = new Usuario();
        String nombre = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setNombreUsuario(nombre);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setNombrePilaTest() {
        Usuario usuario = new Usuario();
        String nombrePila = "name";

        usuario.setNombrePilaUsuario(nombrePila);

        Assertions.assertEquals(usuario.getNombrePilaUsuario(), nombrePila);
    }

    @Test
    public void setNombrePilaLargoTest() {
        Usuario usuario = new Usuario();
        String nombre = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setNombrePilaUsuario(nombre);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setApellidosTest() {
        Usuario usuario = new Usuario();
        String apellidos = "apellido1 apellido2";

        usuario.setApellidosUsuario(apellidos);

        Assertions.assertEquals(usuario.getApellidosUsuario(), apellidos);
    }

    @Test
    public void setBiografiaTest() {
        Usuario usuario = new Usuario();
        String biografia = "biography";

        usuario.setBiografiaUsuario(biografia);

        Assertions.assertEquals(usuario.getBiografiaUsuario(), biografia);
    }

    @Test
    public void setBiografiaSuperaTamanoTest() {
        Usuario usuario = new Usuario();
        String biografia = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setBiografiaUsuario(biografia);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setEmailTest() {
        Usuario usuario = new Usuario();
        String email = "email@email.com";

        usuario.setCorreoElectronicoUsuario(email);

        Assertions.assertEquals(usuario.getCorreoElectronicoUsuario(), email);
    }

    @Test
    public void setEmailNoValidoTest() {
        Usuario usuario = new Usuario();
        String email = "email.com";

        usuario.setCorreoElectronicoUsuario(email);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setContrasenaTest() {
        Usuario usuario = new Usuario();
        String contrasena = "contraseña";

        usuario.setContrasenaUsuario(contrasena);

        Assertions.assertEquals(usuario.getContrasenaUsuario(), contrasena);
    }

    @Test
    public void setContrasenaLargaTest() {
        Usuario usuario = new Usuario();
        String contrasena = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setContrasenaUsuario(contrasena);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }


    @Test
    public void setEnlaceTwitterTest() {
        Usuario usuario = new Usuario();
        String enlace = "fasdjklhgfkjhsdfgj.com";

        usuario.setEnlaceTwitterUsuario(enlace);

        Assertions.assertEquals(usuario.getEnlaceTwitterUsuario(), enlace);
    }

    @Test
    public void setEnlaceTwitterLargoTest() {
        Usuario usuario = new Usuario();
        String enlace = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setEnlaceTwitterUsuario(enlace);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setEnlaceFacebook() {
        Usuario usuario = new Usuario();
        String enlace = "fasdjklhgfkjhsdfgj.com";

        usuario.setEnlaceFacebookUsuario(enlace);

        Assertions.assertEquals(usuario.getEnlaceFacebookUsuario(), enlace);
    }

    @Test
    public void setEnlaceFacebookLargoTest() {
        Usuario usuario = new Usuario();
        String enlace = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setEnlaceFacebookUsuario(enlace);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setEnlaceInstagram() {
        Usuario usuario = new Usuario();
        String enlace = "fasdjklhgfkjhsdfgj.com";

        usuario.setEnlaceInstagramUsuario(enlace);

        Assertions.assertEquals(usuario.getEnlaceInstagramUsuario(), enlace);
    }

    @Test
    public void setEnlaceInstagramLargoTest() {
        Usuario usuario = new Usuario();
        String enlace = UtilidadesParaPruebas.CADENA_LARGA;

        usuario.setEnlaceInstagramUsuario(enlace);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setCuentaEliminadaTest(){
        Usuario u = new Usuario();

        u.setCuentaEliminada(true);

        Assertions.assertTrue(u.isCuentaEliminada());
    }

    @Test
    public void setLenguajeTest(){
        Usuario u = new Usuario();

        u.setLenguaje(Lenguaje.gl);

        Assertions.assertEquals(u.getLenguaje(), Lenguaje.gl);
    }

    @Test
    public void setRolSistemaTest(){
        Usuario u = new Usuario();

        u.setRolUsuarioSistema(RolUsuarioSistema.DESARROLLADOR);

        Assertions.assertEquals(u.getRolUsuarioSistema(), RolUsuarioSistema.DESARROLLADOR);
    }



    @Test
    public void setSeguidores(){

        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");
        Set<UsuarioSigueUsuario> seguidos = new HashSet<>();
        UsuarioSigueUsuario usuarioSgue = new UsuarioSigueUsuario();
        usuarioSgue.setUsuarioSeguido(UtilidadesParaPruebas.crearUsuario("pepe"));
        seguidos.add(usuarioSgue);

        usuario.setUsuariosQueSigue(seguidos);

        Assertions.assertEquals(usuario.getUsuariosQueSigue(), seguidos);
    }

    @Test
    public void setUsuariosSeguidos(){

        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");
        Set<UsuarioSigueUsuario> seguidores = new HashSet<>();
        UsuarioSigueUsuario usuarioSgue = new UsuarioSigueUsuario();
        usuarioSgue.setUsuarioSeguido(UtilidadesParaPruebas.crearUsuario("pepe"));
        seguidores.add(usuarioSgue);

        usuario.setUsuariosQueLoSiguen(seguidores);

        Assertions.assertEquals(usuario.getUsuariosQueLoSiguen(), seguidores);
    }

    @Test
    public void setNotificacionesUsuarioTest(){
        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");
        Notificacion notificacion = new Notificacion();
        Set<Notificacion> notificaciones = new HashSet<>();
        notificaciones.add(notificacion);

        usuario.setNotificacionesUsuario(notificaciones);

        Assertions.assertEquals(usuario.getNotificacionesUsuario(), notificaciones);
    }

    @Test
    public void setConcursosEnLosQueParticipaTest() {
        Usuario usuario = new Usuario();
        UsuarioParticipaConcurso upc = new UsuarioParticipaConcurso();
        Set<UsuarioParticipaConcurso> usuarioParticipaConcursoSet = new HashSet<>();
        usuarioParticipaConcursoSet.add(upc);

        usuario.setConcursosEnLosQueParticipa(usuarioParticipaConcursoSet);

        Assertions.assertEquals(usuario.getConcursosEnLosQueParticipa(), usuarioParticipaConcursoSet);
    }
}