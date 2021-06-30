package com.figueiras.photocontest.backend.model.entities.Daos;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    Slice<Usuario> findByNombreUsuario(String nombreUsuario, Pageable pageable);
    List<Usuario> findByNombreUsuario(String nombreUsuario);
}
