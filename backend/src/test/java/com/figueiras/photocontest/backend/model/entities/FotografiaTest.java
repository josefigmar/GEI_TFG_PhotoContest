package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Random;

public class FotografiaTest {

    public static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        validator = vFactory.getValidator();
    }

    @Test
    public void crearInstanciaTest(){
        Fotografia fotografia = new Fotografia();

        Assertions.assertNotNull(fotografia);
    }

    @Test
    public void setTituloFotografiaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setTituloFotografia(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getTituloFotografia(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setTituloFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setTituloFotografia(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDescripcionFotografiaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDescripcionFotografia(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDescripcionFotografia(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setDescripcionFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDescripcionFotografia(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setAperturaFotografiaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setAperturaDiafragma(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getAperturaDiafragma(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setAperturaFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setAperturaDiafragma(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setFabricanteCamaraTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFabricanteCamara(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getFabricanteCamara(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setFabricanteCamaraLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFabricanteCamara(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setModeloCamaraTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setModeloCamara(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getModeloCamara(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setModeloCamaraLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setModeloCamara(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDistanciaFocalTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDistanciaFocal(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDistanciaFocal(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setDistanciaFocalLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDistanciaFocal(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setVelocidadObturacionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setVelocidadObturacion(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getVelocidadObturacion(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setVelocidadObturacionLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setVelocidadObturacion(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setIsoTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setIso(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getIso(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setIsoLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setIso(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setResolucionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setResolucion(Utilidades.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getResolucion(), Utilidades.CADENA_MEDIA);
    }

    @Test
    public void setResolucionLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setResolucion(Utilidades.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDatosJpgTest() {
        Fotografia fotografia = new Fotografia();
        byte[] byteArr = new byte[20];
        new Random().nextBytes(byteArr);

        fotografia.setDatosJpg(byteArr);

        Assertions.assertEquals(fotografia.getDatosJpg(), byteArr);
    }

    @Test
    public void setDatosRawTest() {
        Fotografia fotografia = new Fotografia();
        byte[] byteArr = new byte[20];
        new Random().nextBytes(byteArr);

        fotografia.setDatosRaw(byteArr);

        Assertions.assertEquals(fotografia.getDatosRaw(), byteArr);
    }

    @Test
    public void setFechaInicioParticipacionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFechaInicioParticipacion(Utilidades.FECHA_HOY);

        Assertions.assertEquals(fotografia.getFechaInicioParticipacion(), Utilidades.FECHA_HOY);
    }

    @Test
    public void setEstadoModeracionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setEstadoModeracion(EstadoModeracion.EN_ESPERA);

        Assertions.assertEquals(fotografia.getEstadoModeracion(), EstadoModeracion.EN_ESPERA);
    }

    @Test
    public void setCategoriaTest(){
        Fotografia fotografia = new Fotografia();
        CategoriaFotografica categoriaFotografica = new CategoriaFotografica();

        fotografia.setCategoriaFotografica(categoriaFotografica);

        Assertions.assertEquals(fotografia.getCategoriaFotografica(), categoriaFotografica);
    }

    @Test
    public void setUsuarioTest(){
        Fotografia fotografia = new Fotografia();
        Usuario usuario = Utilidades.crearUsuario("root");

        fotografia.setUsuario(usuario);

        Assertions.assertEquals(fotografia.getUsuario(), usuario);
    }

    @Test
    public void setConcursoTest(){
        Fotografia fotografia = new Fotografia();
        Concurso concurso = new Concurso();

        fotografia.setConcurso(concurso);

        Assertions.assertEquals(fotografia.getConcurso(), concurso);
    }
}