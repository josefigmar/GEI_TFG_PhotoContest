package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface NotificacionDao extends PagingAndSortingRepository<Notificacion, Long> {

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.nombreUsuario = ?1 ORDER BY n.fechaCreacion DESC")
    Slice<Notificacion> findByUsuarioNombreUsuarioOrderByFechaCreacion(String nombreUsuario, Pageable pageable);
    @Query("SELECT COUNT(n) FROM Notificacion n WHERE n.usuario.nombreUsuario = ?1 AND n.leida = false")
    int existenNotificacionesSinLeer(String nombreUsuario);
}
