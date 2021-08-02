package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.exceptions.DatosDeConcursoNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.CrearConcursoDto;

public interface ServicioConcurso {

     Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                        int page, int size);

     Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException;

     void crearConcurso(CrearConcursoDto datosConcurso, String nombreUsuario)
             throws InstanceNotFoundException, DatosDeConcursoNoValidosException;
}