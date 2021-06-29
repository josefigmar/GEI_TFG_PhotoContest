package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConcursoDao extends PagingAndSortingRepository<Concurso, Long>, CustomizedConcursoDao {

    Slice<Concurso> findAllByOrderByFechaCreacionAsc(Pageable pageable);

}
