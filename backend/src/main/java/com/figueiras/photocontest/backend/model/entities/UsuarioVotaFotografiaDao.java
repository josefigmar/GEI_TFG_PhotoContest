package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.UsuarioVotaFotografia;
import com.figueiras.photocontest.backend.model.entities.UsuarioVotaFotografiaPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.Tuple;
import javax.xml.transform.Result;
import java.util.List;
import java.util.Optional;

public interface UsuarioVotaFotografiaDao
        extends PagingAndSortingRepository<UsuarioVotaFotografia, UsuarioVotaFotografiaPK> {

    List<UsuarioVotaFotografia> findByFotografiaIdFotografia(Long idFotografia);
    @Query("SELECT u FROM UsuarioVotaFotografia u WHERE u.concurso.nombreConcurso = :nombreConcurso AND u.usuario.nombreUsuario = :nombreUsuario")
    List<UsuarioVotaFotografia> findByConcursoUsuario(String nombreConcurso, String nombreUsuario);
    @Query("SELECT u FROM UsuarioVotaFotografia u WHERE u.fotografia.idFotografia = :idFotografia AND u.usuario.nombreUsuario = :nombreUsuario")
    Optional<UsuarioVotaFotografia> findByFotografiaUsuario(Long idFotografia, String nombreUsuario);
    @Query("SELECT SUM(u.puntuacion), u.fotografia.idFotografia FROM UsuarioVotaFotografia u WHERE u.concurso.nombreConcurso = :nombreConcurso GROUP BY u.fotografia.idFotografia ORDER BY SUM(u.puntuacion) DESC")
    List<Tuple> findGanadoras(String nombreConcurso);
}
