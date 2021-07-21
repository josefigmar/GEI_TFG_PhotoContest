package com.figueiras.photocontest.backend.model.entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class NotificacionTest {

    private static Validator validator;

    @BeforeAll
    public static void setUp() {
        ValidatorFactory vFactory = Validation.buildDefaultValidatorFactory();
        validator = vFactory.getValidator();
    }

    @Test
    public void crearInstanciaTest(){
        Notificacion notificacion = new Notificacion();

        Assertions.assertNotNull(notificacion);
    }

    @Test
    public void crearInstanciaComprobarValorLeidoFalse(){
        Notificacion notificacion = new Notificacion();

        Assertions.assertFalse(notificacion.isLeida());
    }

    @Test
    public void setNombreNotificacionCortoTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setNombreNotificacion(UtilidadesParaPruebas.CADENA_VACIA);

        Assertions.assertFalse(validator.validate(notificacion).isEmpty());
    }

    @Test
    public void setNombreNotificacionTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setNombreNotificacion(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(notificacion.getNombreNotificacion(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setNombreNotificacionLargaTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setNombreNotificacion(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(notificacion).isEmpty());
    }

    @Test
    public void setDescripcionNotificacionCortaTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_VACIA);

        Assertions.assertFalse(validator.validate(notificacion).isEmpty());
    }

    @Test
    public void setDescripcionNotificacionTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_MEDIA);

        Assertions.assertEquals(notificacion.getMensajeNotificacion(), UtilidadesParaPruebas.CADENA_MEDIA);
    }

    @Test
    public void setDescripcionNotificacionLargaTest(){
        Notificacion notificacion = new Notificacion();

        notificacion.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertFalse(validator.validate(notificacion).isEmpty());
    }

    @Test
    public void setFotoNotificacion() {
        Notificacion notificacion = new Notificacion();

        notificacion.setFotoNotificacion(UtilidadesParaPruebas.CADENA_LARGA);

        Assertions.assertEquals(notificacion.getFotoNotificacion(), UtilidadesParaPruebas.CADENA_LARGA);
    }

    @Test
    public void setFechaCreacion(){
        Notificacion notificacion = new Notificacion();

        notificacion.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);

        Assertions.assertEquals(notificacion.getFechaCreacion(), UtilidadesParaPruebas.FECHA_HOY);
    }

    @Test
    public void setLeidoTrue(){
        Notificacion notification = new Notificacion();

        notification.setLeida(true);

        Assertions.assertTrue(notification.isLeida());
    }

    @Test
    public void setUsuarioTest(){
        Notificacion notificacion = new Notificacion();
        Usuario usuario = UtilidadesParaPruebas.crearUsuario("root");

        notificacion.setUsuario(usuario);

        Assertions.assertEquals(notificacion.getUsuario(), usuario);
    }
}