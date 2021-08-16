package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioParticipaConcursoDao
        extends PagingAndSortingRepository<UsuarioParticipaConcurso, UsuarioParticipaConcursoPK> {

    Optional<UsuarioParticipaConcurso> findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
            Long idUsuario, Long idConcurso, RolUsuarioConcurso rolConcurso);
    List<UsuarioParticipaConcurso> findByConcursoIdConcursoAndRolUsuarioConcurso(Long idConcurso, RolUsuarioConcurso rolConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 2")
    Slice<UsuarioParticipaConcurso> findJurado(Long idConcurso, Pageable pageable);
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 0")
    Slice<UsuarioParticipaConcurso> findOrganizadores(Long idConcurso, Pageable pageable);
    @Query("SELECT u FROM UsuarioParticipaConcurso u " +
            "WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 1")
    Slice<UsuarioParticipaConcurso> findInscritos(Long idConcurso, Pageable pageable);
    @Query("SELECT u FROM UsuarioParticipaConcurso  u WHERE u.usuario.nombreUsuario = :nombreUsuario AND u.concurso.nombreConcurso = :nombreConcurso")
    Optional<UsuarioParticipaConcurso> findByUsuarioConcurso(String nombreUsuario, String nombreConcurso);

}