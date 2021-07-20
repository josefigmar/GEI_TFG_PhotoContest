package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.Notificacion;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioNotificacion;
import com.figueiras.photocontest.backend.rest.dtos.NotificacionConversor;
import com.figueiras.photocontest.backend.rest.dtos.NotificacionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/catalogo-notificaciones")
public class ControladorNotificacion {

    @Autowired
    private ServicioNotificacion servicioNotificacion;

    @GetMapping("/notificaciones/{nombreUsuario}")
    public Block<NotificacionDto> recuperarNotificacionesDeUsuario(@PathVariable String nombreUsuario,
                                                                   @RequestParam int page,
                                                                   @RequestParam int size) {

        Block<Notificacion> notificacionBlock =
                servicioNotificacion.buscarNotificacionesDeUsuario(nombreUsuario, page, size);

        Block<NotificacionDto> notificacionDtoBlock = new Block<>(NotificacionConversor.toNotificacionDtos(notificacionBlock.getItems()),
                notificacionBlock.getExistMoreItems());

        servicioNotificacion.marcarComoLeidas(notificacionBlock.getItems());

        return notificacionDtoBlock;
    }

    @GetMapping("/notificaciones/{nombreUsuario}/has-new")
    public boolean tieneNuevasNotificacionesUsuario(@PathVariable String nombreUsuario){

        return servicioNotificacion.notificacionesPendientesDeVisualizar(nombreUsuario);
    }
}
