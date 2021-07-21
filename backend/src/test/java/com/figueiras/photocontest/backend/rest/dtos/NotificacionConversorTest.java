package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Notificacion;
import com.figueiras.photocontest.backend.model.entities.UtilidadesParaPruebas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class NotificacionConversorTest {

    // En todos los test se asigna la fecha para no causar Null Pointer exception por la
    // transformación a Long

    @Test
    public void crearNotificacionDtoIdTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setIdNotificacion((long) 2);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.getIdNotificacion(), n.getIdNotificacion());
    }

    @Test
    public void crearNotificacionDtoNombreTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setNombreNotificacion(UtilidadesParaPruebas.CADENA_MEDIA);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.getNombreNotificacion(), n.getNombreNotificacion());
    }

    @Test
    public void crearNotificacionDtoMensajeTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_LARGA);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.getMensajeNotificacion(), n.getMensajeNotificacion());
    }

    @Test
    public void crearNotificacionDtoFotoTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setFotoNotificacion(UtilidadesParaPruebas.CADENA_LARGA);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.getFotoNotificacion(), n.getFotoNotificacion());
    }

    @Test
    public void crearNotificacionDtoFechaCreacionTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.getFechaCreacion(), Utilidades.toMillis(n.getFechaCreacion()));
    }

    @Test
    public void crearNotificacionDtoLeidaTest() {
        Notificacion n = new Notificacion();
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setLeida(true);

        NotificacionDto nDto = NotificacionConversor.toNotificacionDto(n);

        Assertions.assertEquals(nDto.isLeida(), n.isLeida());
    }

    @Test
    public void crearNotificacionDtoTest() {
        Notificacion n = new Notificacion();
        Notificacion n2 = new Notificacion();
        n.setLeida(true);
        n.setFotoNotificacion(UtilidadesParaPruebas.CADENA_LARGA);
        n.setNombreNotificacion(UtilidadesParaPruebas.CADENA_MEDIA);
        n.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_LARGA);
        n.setIdNotificacion((long) 2);
        n2.setLeida(false);
        n2.setFotoNotificacion(UtilidadesParaPruebas.CADENA_LARGA);
        n2.setNombreNotificacion(UtilidadesParaPruebas.CADENA_MEDIA);
        n2.setFechaCreacion(UtilidadesParaPruebas.FECHA_HOY);
        n2.setMensajeNotificacion(UtilidadesParaPruebas.CADENA_LARGA);
        n2.setIdNotificacion((long) 3);
        List<Notificacion> notificacionList = Arrays.asList(n, n2);

        List<NotificacionDto> notificacionDtoList = NotificacionConversor.toNotificacionDtos(notificacionList);

        Assertions.assertEquals(notificacionDtoList.size(), 2);
    }
}
