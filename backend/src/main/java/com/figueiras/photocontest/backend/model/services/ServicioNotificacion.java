package com.figueiras.photocontest.backend.model.services;


import com.figueiras.photocontest.backend.model.entities.Notificacion;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;

import java.util.List;

public interface ServicioNotificacion {

    Notificacion crearNotificacion(String nombreNotificacion,
                                   String mensaje, String nombreUsuario) throws InstanceNotFoundException;
    Block<Notificacion> buscarNotificacionesDeUsuario(String nombreUsuario, int page, int size);
    boolean notificacionesPendientesDeVisualizar(String nombreUsuario);
    public void marcarComoLeidas(List<Notificacion> notificaciones);
}
