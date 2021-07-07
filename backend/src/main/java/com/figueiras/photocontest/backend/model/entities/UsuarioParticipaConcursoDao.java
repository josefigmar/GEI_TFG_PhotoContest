package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.UsuarioParticipaConcurso;
import com.figueiras.photocontest.backend.model.entities.UsuarioParticipaConcursoPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface UsuarioParticipaConcursoDao
        extends PagingAndSortingRepository<UsuarioParticipaConcurso, UsuarioParticipaConcursoPK> {

    Optional<UsuarioParticipaConcurso> findByUsuarioIdUsuarioAndConcursoIdConcurso(Long idUsuario, Long idConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 2")
    List<UsuarioParticipaConcurso> findJurado(Long idConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 0")
    List<UsuarioParticipaConcurso> findOrganizadores(Long idConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u " +
            "WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuarioConcurso = 1")
    List<UsuarioParticipaConcurso> findInscritos(Long idConcurso);

}