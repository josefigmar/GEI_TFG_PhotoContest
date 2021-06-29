package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.Slice;

public interface CustomizedConcursoDao {

    Slice<Concurso> find(Integer estado, Long idCategoria, String nombreConcurso, int page, int size);
}