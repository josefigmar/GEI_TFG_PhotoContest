package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ConcursoDao extends PagingAndSortingRepository<Concurso, Long>, CustomizedConcursoDao {

    @Query("SELECT c FROM Concurso c ORDER BY c.fechaCreacion ASC")
    Slice<Concurso> buscarConcursosOrdenarPorFechaCreacion(Pageable pageable);

    Optional<Concurso> findByNombreConcurso(String nombreConcurso);

}
