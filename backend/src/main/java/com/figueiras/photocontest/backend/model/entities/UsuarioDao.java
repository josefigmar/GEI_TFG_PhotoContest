package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    Slice<Usuario> findByNombreUsuario(String nombreUsuario, Pageable pageable);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
}
