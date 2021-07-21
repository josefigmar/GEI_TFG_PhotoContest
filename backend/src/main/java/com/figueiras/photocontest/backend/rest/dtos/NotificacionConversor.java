package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Notificacion;

import java.util.List;
import java.util.stream.Collectors;

public class NotificacionConversor {

    public static NotificacionDto toNotificacionDto(Notificacion notificacion){

        NotificacionDto resultado = new NotificacionDto();

        resultado.setIdNotificacion(notificacion.getIdNotificacion());
        resultado.setNombreNotificacion(notificacion.getNombreNotificacion());
        resultado.setMensajeNotificacion(notificacion.getMensajeNotificacion());
        resultado.setFotoNotificacion(notificacion.getFotoNotificacion());
        resultado.setFechaCreacion(Utilidades.toMillis(notificacion.getFechaCreacion()));
        resultado.setLeida(notificacion.isLeida());

        return resultado;
    }

    public static List<NotificacionDto> toNotificacionDtos(List<Notificacion> notificaciones){

        return notificaciones.stream().map(notificacion ->
                toNotificacionDto(notificacion)).collect(Collectors.toList());
    }
}
