package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.UsuarioVotaFotografia;
import com.figueiras.photocontest.backend.model.entities.UsuarioVotaFotografiaPK;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UsuarioVotaFotografiaDao
        extends PagingAndSortingRepository<UsuarioVotaFotografia, UsuarioVotaFotografiaPK> {

    List<UsuarioVotaFotografia> findByFotografiaIdFotografia(Long idFotografia);
}
