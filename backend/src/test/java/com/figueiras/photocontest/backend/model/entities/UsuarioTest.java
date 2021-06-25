package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.data.web.HateoasPageableHandlerMethodArgumentResolver;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    public void setNombreTest() {
        Usuario usuario = new Usuario();
        String nombre = "name";

        usuario.setNombreUsuario(nombre);

        Assertions.assertEquals(usuario.getNombreUsuario(), nombre);
    }

    @Test
    public void setNombreCortoTest() {
        Usuario usuario = new Usuario();
        String nombre = Utilidades.CADENA_VACIA;

        usuario.setNombreUsuario(nombre);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setNombreLargoTest() {
        Usuario usuario = new Usuario();
        String nombre = Utilidades.CADENA_LARGA;

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
    public void setNombrePilaCortoTest() {
        Usuario usuario = new Usuario();
        String nombre = Utilidades.CADENA_VACIA;

        usuario.setNombrePilaUsuario(nombre);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setNombrePilaLargoTest() {
        Usuario usuario = new Usuario();
        String nombre = Utilidades.CADENA_LARGA;

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
    public void setApellidosInferiorTamanoTest() {
        Usuario usuario = new Usuario();
        String apellidos = Utilidades.CADENA_VACIA;

        usuario.setApellidosUsuario(apellidos);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
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
        String biografia = Utilidades.CADENA_LARGA;

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
    public void setContrasenaCortaTest() {
        Usuario usuario = new Usuario();
        String contrasena = Utilidades.CADENA_VACIA;

        usuario.setContrasenaUsuario(contrasena);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setContrasenaLargaTest() {
        Usuario usuario = new Usuario();
        String contrasena = Utilidades.CADENA_LARGA;

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
        String enlace = Utilidades.CADENA_LARGA;

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
        String enlace = Utilidades.CADENA_LARGA;

        usuario.setEnlaceFacebookUsuario(enlace);

        Assertions.assertFalse(validator.validate(usuario).isEmpty());
    }

    @Test
    public void setSeguidores(){

        Usuario usuario = Utilidades.crearUsuario("root");
        Set<UsuarioSigueUsuario> seguidos = new HashSet<>();
        UsuarioSigueUsuario usuarioSgue = new UsuarioSigueUsuario();
        usuarioSgue.setUsuarioSeguido(Utilidades.crearUsuario("pepe"));
        seguidos.add(usuarioSgue);

        usuario.setUsuariosQueSigue(seguidos);

        Assertions.assertEquals(usuario.getUsuariosQueSigue(), seguidos);
    }

    @Test
    public void setUsuariosSeguidos(){

        Usuario usuario = Utilidades.crearUsuario("root");
        Set<UsuarioSigueUsuario> seguidores = new HashSet<>();
        UsuarioSigueUsuario usuarioSgue = new UsuarioSigueUsuario();
        usuarioSgue.setUsuarioSeguido(Utilidades.crearUsuario("pepe"));
        seguidores.add(usuarioSgue);

        usuario.setUsuariosQueLoSiguen(seguidores);

        Assertions.assertEquals(usuario.getUsuariosQueLoSiguen(), seguidores);
    }
}