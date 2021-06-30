package com.figueiras.photocontest.backend.model.entities.Daos;

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
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuario = 3")
    List<UsuarioParticipaConcurso> findJurado(Long idConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuario = 1")
    List<UsuarioParticipaConcurso> findOrganizadores(Long idConcurso);
    @Query("SELECT u FROM UsuarioParticipaConcurso u " +
            "WHERE u.concurso.idConcurso = :idConcurso AND u.rolUsuario = 0 OR u.rolUsuario = 2")
    List<UsuarioParticipaConcurso> findParticipantes(Long idConcurso);

}