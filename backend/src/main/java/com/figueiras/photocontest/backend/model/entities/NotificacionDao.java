package com.figueiras.photocontest.backend.model.entities.Daos;

import com.figueiras.photocontest.backend.model.entities.Notificacion;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface NotificacionDao extends PagingAndSortingRepository<Notificacion, Long> {

    Slice<Notificacion> findByUsuarioIdUsuarioOrderByFechaCreacion(Long idUsuario, Pageable pageable);
}
