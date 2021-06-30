package com.figueiras.photocontest.backend.model.entities.Daos;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ConcursoDao extends PagingAndSortingRepository<Concurso, Long>, CustomizedConcursoDao {

    Slice<Concurso> findAllByOrderByFechaCreacionAsc(Pageable pageable);

}
