package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;

public interface ServicioConcurso {

     Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                        int page, int size);
     Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException;
}
