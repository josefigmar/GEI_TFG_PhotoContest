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

        fotografia.setTituloFotografia(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getTituloFotografia(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setTituloFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setTituloFotografia(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDescripcionFotografiaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDescripcionFotografia(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDescripcionFotografia(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setDescripcionFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDescripcionFotografia(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setAperturaFotografiaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setAperturaDiafragma(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getAperturaDiafragma(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setAperturaFotografiaLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setAperturaDiafragma(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setFabricanteCamaraTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFabricanteCamara(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getFabricanteCamara(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setFabricanteCamaraLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFabricanteCamara(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setModeloCamaraTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setModeloCamara(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getModeloCamara(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setModeloCamaraLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setModeloCamara(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDistanciaFocalTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDistanciaFocal(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDistanciaFocal(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setDistanciaFocalLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setDistanciaFocal(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setVelocidadObturacionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setVelocidadObturacion(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getVelocidadObturacion(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setVelocidadObturacionLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setVelocidadObturacion(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setIsoTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setIso(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getIso(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setIsoLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setIso(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setResolucionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setResolucion(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getResolucion(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setResolucionLargaTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setResolucion(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(fotografia).isEmpty());
    }

    @Test
    public void setDatosJpgTest() {
        Fotografia fotografia = new Fotografia();

        fotografia.setDatosJpg(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDatosJpg(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setDatosRawTest() {
        Fotografia fotografia = new Fotografia();

        fotografia.setDatosRaw(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(fotografia.getDatosRaw(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setFechaInicioParticipacionTest(){
        Fotografia fotografia = new Fotografia();

        fotografia.setFechaInicioParticipacion(UtilidadesParaPruebas.FECHA_HOY);

        Assertions.assertEquals(fotografia.getFechaInicioParticipacion(), UtilidadesParaPruebas.FECHA_HOY);
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
        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");

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