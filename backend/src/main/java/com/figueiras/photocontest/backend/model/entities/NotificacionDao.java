package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificacionDao extends PagingAndSortingRepository<Notificacion, Long> {

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.nombreUsuario = ?1 ")
    Slice<Notificacion> findByUsuarioNombreUsuarioOrderByFechaCreacion(String nombreUsuario, Pageable pageable);
    @Query("SELECT COUNT(n), CASE WHEN COUNT(n) = 0 THEN FALSE ELSE TRUE END FROM Notificacion n WHERE n.usuario.nombreUsuario = ?1 AND n.leida = false")
    Long existenNotificacionesSinLeer(String nombreUsuario);
}
