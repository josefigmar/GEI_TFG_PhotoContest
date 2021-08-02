package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u WHERE u.nombreUsuario LIKE %:nombreUsuario% AND u.cuentaEliminada=false ORDER BY u.nombreUsuario")
    Slice<Usuario> findByNombreUsuario(String nombreUsuario, Pageable pageable);
    @Query("SELECT u FROM Usuario u WHERE u.cuentaEliminada=false ORDER BY u.nombreUsuario")
    Slice<Usuario> findAndOrderByNombreUsuario(Pageable pageable);
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    Optional<Usuario> findByCorreoElectronicoUsuario(String nombreUsuario);
    @Query("SELECT u.nombreUsuario FROM Usuario u ORDER BY u.nombreUsuario")
    List<String> findAllUserNames();
}
