package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import org.springframework.data.domain.Slice;

public interface CustomizedConcursoDao {

    Slice<Concurso> find(Integer estado, Long idCategoria, String nombreConcurso, int page, int size);
}
