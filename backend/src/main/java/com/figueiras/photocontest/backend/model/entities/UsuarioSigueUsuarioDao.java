package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UsuarioSigueUsuarioDao
        extends PagingAndSortingRepository<UsuarioSigueUsuario, Long> {

    @Query("SELECT u FROM UsuarioSigueUsuario u WHERE u.usuarioSeguido.idUsuario = ?1")
    Slice<UsuarioSigueUsuario> recuperarSeguidoresDeUsuario(Long idUsuario);

    @Query("SELECT u FROM UsuarioSigueUsuario u WHERE u.usuarioSeguidor.idUsuario = ?1")
    Slice<UsuarioSigueUsuario> recuperarSeguidosDeUsuario(Long idUsuario);

    @Query("SELECT u FROM UsuarioSigueUsuario u WHERE u.usuarioSeguidor.nombreUsuario = ?1 AND" +
            " u.usuarioSeguido.nombreUsuario = ?2")
    Optional<UsuarioSigueUsuario> recuperarUsuarioSigueUsuario(String nombreUsuarioSeguidor, String nombreUsuarioSeguido);

}
