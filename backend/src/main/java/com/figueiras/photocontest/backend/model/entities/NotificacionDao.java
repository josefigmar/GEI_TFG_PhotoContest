package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.print.Pageable;

public interface NotificacionDao extends PagingAndSortingRepository<Notificacion, Long> {

    Slice<Notificacion> findByUsuarioNombreUsuario(String nombreUsuario, Pageable pageable);
}
