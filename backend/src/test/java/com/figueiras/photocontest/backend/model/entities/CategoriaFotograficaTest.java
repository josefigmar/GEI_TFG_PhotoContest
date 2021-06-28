package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.HashSet;
import java.util.Set;

public class CategoriaFotograficaTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        validator = vFactory.getValidator();
    }

    @Test
    public void createInstanceTest() {
        CategoriaFotografica categoria = new CategoriaFotografica();

        Assertions.assertNotNull(categoria);
    }

    @Test
    public void setNombreTest() {
        CategoriaFotografica categoria = new CategoriaFotografica();
        String nombre = "name";

        categoria.setNombreCategoria(nombre);

        Assertions.assertEquals(categoria.getNombreCategoria(), nombre);
    }

    @Test
    public void setDescripcionTest() {
        CategoriaFotografica categoria = new CategoriaFotografica();
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris malesuada leo at tellus";
        categoria.setNombreCategoria(desc);

        Assertions.assertEquals(categoria.getNombreCategoria(), desc);
    }

    @Test
    public void setDescripcionErrorTest() {
        CategoriaFotografica categoria = new CategoriaFotografica();
        String desc = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean sed neque neque. Quisque gravida augue et elementum vulputate. Maecenas mattis in libero id aliquet. Pellentesque dignissim blandit arcu. Nam finibus suscipit tristique. Integer vehicula, mauris suscipit mollis auctor, neque orci pulvinar nulla, nec imperdiet quam augue eu justo. Donec sodales commodo dui. Quisque sed luctus mi. Duis pretium eros sed lorem pharetra aliquet vitae id nisl. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Sed consectetur faucibus nulla in rhoncus. Sed eros erat, hendrerit ac vehicula in, consectetur vel dui. Aenean luctus aliquet massa a dignissim.";
        categoria.setNombreCategoria(desc);

        Assertions.assertFalse(validator.validate(categoria).isEmpty());
    }

    @Test
    public void setUsuariosQueLeGustanCategoriaTest(){
        Usuario usuario = Utilidades.crearUsuario("root");
        CategoriaFotografica categoria = new CategoriaFotografica();
        Set<Usuario> usuariosQueGustanCategoria = new HashSet<>();
        usuariosQueGustanCategoria.add(usuario);

        categoria.setUsuariosALosQueLesGusta(usuariosQueGustanCategoria);

        Assertions.assertEquals(categoria.getUsuariosALosQueLesGusta(), usuariosQueGustanCategoria);
    }
}