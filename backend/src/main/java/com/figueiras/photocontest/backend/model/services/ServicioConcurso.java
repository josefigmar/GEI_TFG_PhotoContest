package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.RolUsuarioConcurso;
import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.rest.dtos.CategoriaFotograficaDto;
import com.figueiras.photocontest.backend.rest.dtos.ConcursoDto;
import com.figueiras.photocontest.backend.rest.dtos.FotografiaDto;

import java.util.List;

public interface ServicioConcurso {

    Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                       int page, int size);

    Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException;

    ConcursoDto recuperarDatosConcurso(Long idConcurso) throws InstanceNotFoundException;

    void crearConcurso(ConcursoDto datosConcurso, String nombreUsuario)
            throws InstanceNotFoundException, DatosDeConcursoNoValidosException;

    void participarConcurso(FotografiaDto datosFotografia)
            throws InstanceNotFoundException, DatosDeFotografiaNoValidosException, UsuarioNoPuedeParticiparException;

    void crearCategoria(CategoriaFotograficaDto datosCategoria) throws CategoriaDuplicadaException;

    int getNumeroDeParticipantes(long idConcurso);

    boolean isRol(String nombreUsuario, long idConcurso, RolUsuarioConcurso rolUsuarioConcurso)
            throws InstanceNotFoundException;

    Block<Usuario> recuperarOrganizadores(long idConcurso, int page, int size) throws InstanceNotFoundException;

    Block<Usuario> recuperarParticipantes(long idConcurso, int page, int size);

    Block<Usuario> recuperarJurado(long idConcurso, int page, int size);

    List<CategoriaFotografica> recuperarCategoriasConcurso(long idConcurso) throws InstanceNotFoundException;

    void eliminarConcurso(long idConcurso) throws InstanceNotFoundException;
}