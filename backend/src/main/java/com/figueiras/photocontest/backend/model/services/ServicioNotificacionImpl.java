package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Notificacion;
import com.figueiras.photocontest.backend.model.entities.NotificacionDao;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioDao;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioNotificacionImpl implements ServicioNotificacion {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private NotificacionDao notificacionDao;


    /**
     * Crea una nueva notificación y la persiste en BBDD.
     *
     * @param nombreNotificacion Es el título que tendrá la notificación creada
     * @param mensaje   El contenido principal de la notificación
     * @param nombreUsuario El usuario al que va dirigida la notificación
     * @return  Devuelve la nueva notificación
     * @throws InstanceNotFoundException    En caso de que el usuario no exista
     */
    @Override
    public Notificacion crearNotificacion(
            final String nombreNotificacion,
            final String mensaje, final String nombreUsuario) throws InstanceNotFoundException {

        Optional<Usuario> uOpt = usuarioDao.findByNombreUsuario(nombreUsuario);

        if (!uOpt.isPresent()) {
            throw new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
        }

        Notificacion notificacionResultado = new Notificacion();
        notificacionResultado.setNombreNotificacion(nombreNotificacion);
        notificacionResultado.setMensajeNotificacion(mensaje);
        notificacionResultado.setUsuario(uOpt.get());
        notificacionResultado.setFechaCreacion(LocalDateTime.now());

        notificacionDao.save(notificacionResultado);

        return notificacionResultado;
    }

    /**
     * Recupera, de manera paginada, las notificaciones de un usuario.
     *
     * @param nombreUsuario El usuario del que se desean recuperar las notificaciones
     * @param page El índice inicial sobre el que se empiezan a recuperar notificaciones
     * @param size  El número máximo de notificaciones que se recuperan por petición
     * @return Un Block que contiene las notificaciones y un booleano que indica si hay más.
     */
    @Override
    public Block<Notificacion> buscarNotificacionesDeUsuario(String nombreUsuario, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Slice<Notificacion> notificacionSlice =
                notificacionDao.findByUsuarioNombreUsuarioOrderByFechaCreacion(nombreUsuario, pageRequest);

        Block<Notificacion> notificacionBlock =
                new Block<>(notificacionSlice.getContent(), notificacionSlice.hasNext());

        return notificacionBlock;
    }

    /**
     * Método que comprueba si existen notificaciones no vistas por el usuario
     *
     * @param nombreUsuario El usuario sobre el que se comprueba
     * @return True si tiene notificaciones pendientes de visualizar, false en caso contrario.
     */
    @Override
    public boolean notificacionesPendientesDeVisualizar(String nombreUsuario) {

         return notificacionDao.existenNotificacionesSinLeer(nombreUsuario) == 1;
    }

    public void marcarComoLeidas(List<Notificacion> notificaciones){
        for (Notificacion n : notificaciones){
            n.setLeida(true);
            notificacionDao.save(n);
        }
    }
}
