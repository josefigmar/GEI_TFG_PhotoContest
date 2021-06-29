package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioVotaFotografiaDao
        extends PagingAndSortingRepository<UsuarioVotaFotografia, UsuarioVotaFotografiaPK> {

    List<UsuarioVotaFotografia> findByFotografiaIdFotografia(Long idFotografia);
}
