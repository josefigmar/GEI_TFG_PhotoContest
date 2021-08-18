package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.model.services.clases.ConcursosParaCambioDeEstado;
import com.figueiras.photocontest.backend.rest.dtos.*;

import java.util.List;

public interface ServicioConcurso {

    Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                       int page, int size);

    Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException;

    ConcursoDto recuperarDatosConcurso(Long idConcurso) throws InstanceNotFoundException;

    Block<Fotografia> recuperarFotografiasModeracion(Long idConcurso, int page, int size);

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

    Fotografia recuperarDatosFotografia(long idFotografia) throws InstanceNotFoundException;

    Block<Fotografia> recuperarFotografiasDeConcurso(String nombreConcurso, int page, int size);

    void supervisarFotografia(long idFotografia, String nombreFotografia,
                              long idConcurso, String nombreConcurso, String decision, String motivo,
                              String nombreUsuarioAutor, String nombreUsuarioSupervisor)
            throws InstanceNotFoundException, DatosSupervisionFotografiaNovalidosException;

    RolConcursoInfoDto recuperarDatosRolUsuario(String nombreConcurso, String nombreUsuario);

    DatosParaVotarDto recuperarInfoVoto(long idFotografia, String nombreConcurso, String nombreUsuario);

    void votarFotografia(String nombreUsuario, long idFotografia, String nombreConcurso, int puntuacion)
            throws InstanceNotFoundException;

    List<ResultadoConcursoDto> recuperarGanadoras(String nombreConcurso, int numeroGanadoras);

    String generarPDFResultados(String nombreConcurso);

    void comprobarYRealizarCambiosDeEstado();

}