package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.Notificacion;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface NotificacionDao extends PagingAndSortingRepository<Notificacion, Long> {

    @Query("SELECT n FROM Notificacion n WHERE n.usuario.idUsuario = ?1 ")
    Slice<Notificacion> findByUsuarioIdUsuarioOrderByFechaCreacion(Long idUsuario, Pageable pageable);
}
