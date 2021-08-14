package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface FotografiaDao extends PagingAndSortingRepository<Fotografia, Long> {

    @Query("SELECT f FROM Fotografia f WHERE f.concurso.nombreConcurso = :nombreConcurso AND " +
            "f.estadoModeracion = :estadoModeracion ORDER BY f.fechaInicioParticipacion DESC")
    Slice<Fotografia> recuperarFotografiasPaginadas(String nombreConcurso, EstadoModeracion estadoModeracion,
                                                    Pageable pageable);
    @Query("SELECT f FROM Fotografia f WHERE f.concurso.idConcurso = :idConcurso")
    List<Fotografia> recuperarFotografias(long idConcurso);
    @Query("SELECT f FROM Fotografia f WHERE f.concurso.idConcurso = :idConcurso AND f.usuario.idUsuario = :idUsuario")
    List<Fotografia> recuperarFotografiasConcursoUsuario(long idConcurso, long idUsuario);
    Slice<Fotografia> findByConcursoIdConcursoAndEstadoModeracion(long idConcurso,
                                                                  EstadoModeracion estadoModeracion,
                                                                  Pageable pageable);
}
