package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.exceptions.CategoriaDuplicadaException;
import com.figueiras.photocontest.backend.model.exceptions.DatosDeConcursoNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.CategoriaFotograficaDto;
import com.figueiras.photocontest.backend.rest.dtos.ConcursoDto;

public interface ServicioConcurso {

     Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                        int page, int size);
     Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException;
     ConcursoDto recuperarDatosConcurso(Long idConcurso) throws InstanceNotFoundException;
     void crearConcurso(ConcursoDto datosConcurso, String nombreUsuario)
             throws InstanceNotFoundException, DatosDeConcursoNoValidosException;
     void crearCategoria(CategoriaFotograficaDto datosCategoria) throws CategoriaDuplicadaException;
     int getNumeroDeParticipantes(long idConcurso);
     boolean isOrganizador(String nombreUsuario, long idConcurso) throws InstanceNotFoundException;
     Block<Usuario> recuperarOrganizadores(long idConcurso, int page, int size) throws InstanceNotFoundException;
     Block<Usuario> recuperarParticipantes(long idConcurso, int page, int size);
     Block<Usuario> recuperarJurado(long idConcurso, int page, int size);
}