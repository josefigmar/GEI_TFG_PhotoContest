package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioConcurso;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.figueiras.photocontest.backend.rest.dtos.ConcursoConversor.toConcursosTablaDto;

@RestController
@RequestMapping("/catalogo-concursos")
public class ControladorCatalogo {

    @Autowired
    private ServicioConcurso servicioConcurso;
    @Autowired
    private CategoriaFotograficaDao categoriaFotograficaDao;

    @ExceptionHandler(DatosDeConcursoNoValidosException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionDatosDeConcurso(DatosDeConcursoNoValidosException e){

        return e.getErroresDto();
    }

    @PostMapping("/categorias")
    public void crearCategoria(@RequestBody CategoriaFotograficaDto datosCategoria) throws CategoriaDuplicadaException {

        servicioConcurso.crearCategoria(datosCategoria);
    }

    @GetMapping("/categorias")
    public List<CategoriaFotograficaDto> recuperarCategorias(){

        Iterable<CategoriaFotografica> categoriaFotograficaIterator = categoriaFotograficaDao.findAll();
        List<CategoriaFotograficaDto> categoriaFotograficaDtoList = new ArrayList<>();

        for(CategoriaFotografica cf: categoriaFotograficaIterator){
            categoriaFotograficaDtoList.add(CategoriaFotograficaConversor.toCategoriaFotograficaDto(cf));
        }
        return categoriaFotograficaDtoList;
    }

    @GetMapping("/concursos/{idConcurso}/categorias")
    public List<CategoriaFotograficaDto> recuperarCategoriasConcurso(@PathVariable long idConcurso)
            throws InstanceNotFoundException {

        List<CategoriaFotografica> categoriaFotograficas = servicioConcurso.recuperarCategoriasConcurso(idConcurso);
        List<CategoriaFotograficaDto> categoriaFotograficaDtoList = new ArrayList<>();

        for(CategoriaFotografica cf: categoriaFotograficas){
            categoriaFotograficaDtoList.add(CategoriaFotograficaConversor.toCategoriaFotograficaDto(cf));
        }
        return categoriaFotograficaDtoList;
    }

    @GetMapping("/concursos")
    public Block<ConcursoTablaDto> recuperarConcursos(@RequestParam(required = false) Long idCategoria,
                                                      @RequestParam(required = false) Integer estado,
                                                      @RequestParam(required = false) String nombre,
                                                      @RequestParam(defaultValue="0") int page,
                                                      @RequestParam(defaultValue="5") int size){

        Block<Concurso> blockConcursos = servicioConcurso.recuperarConcursos(idCategoria,
                estado != null? estado : null, nombre, page, size);

        return new Block<>(toConcursosTablaDto( blockConcursos.getItems()), blockConcursos.getExistMoreItems());
    }

    @GetMapping("/concursos/{idConcurso}/numeroParticipantes")
    public int numeroDeParticipantes(@PathVariable long idConcurso){

        int numeroDeParticipantes = servicioConcurso.getNumeroDeParticipantes(idConcurso);

        return numeroDeParticipantes;
    }

    @GetMapping("/concursos/{idConcurso}/organizadores")
    public Block<UsuarioTablaDto> recuperarOrganizadores(@PathVariable long idConcurso,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size)
            throws InstanceNotFoundException {

        Block<Usuario> blockUsuarios = servicioConcurso.recuperarOrganizadores(idConcurso, page, size);

        Block<UsuarioTablaDto> usuarioDtoBlock =
                new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                        blockUsuarios.getExistMoreItems());

        return usuarioDtoBlock;
    }

    @GetMapping("/concursos/{idConcurso}/participantes")
    public Block<UsuarioTablaDto> recuperarParticipantes(@PathVariable long idConcurso,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size)
            throws InstanceNotFoundException {

        Block<Usuario> blockUsuarios = servicioConcurso.recuperarParticipantes(idConcurso, page, size);

        Block<UsuarioTablaDto> usuarioDtoBlock =
                new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                        blockUsuarios.getExistMoreItems());

        return usuarioDtoBlock;
    }

    @GetMapping("/concursos/{idConcurso}/jurado")
    public Block<UsuarioTablaDto> recuperarJurado(@PathVariable long idConcurso,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "5") int size)
            throws InstanceNotFoundException {

        Block<Usuario> blockUsuarios = servicioConcurso.recuperarJurado(idConcurso, page, size);

        Block<UsuarioTablaDto> usuarioDtoBlock =
                new Block<>(UsuarioConversor.toUsuariosTablaDto(blockUsuarios.getItems()),
                        blockUsuarios.getExistMoreItems());

        return usuarioDtoBlock;
    }

    @GetMapping("/concursos/{idConcurso}/{nombreUsuario}/esOrganizador")
    public boolean esOrganizador(@PathVariable long idConcurso, @PathVariable String nombreUsuario)
            throws InstanceNotFoundException {

        boolean resultado = servicioConcurso.isRol(nombreUsuario, idConcurso, RolUsuarioConcurso.ORGANIZADOR);

        return resultado;
    }

    @GetMapping("/concursos/{idConcurso}/{nombreUsuario}/esParticipante")
    public boolean esParticipante(@PathVariable long idConcurso, @PathVariable String nombreUsuario)
            throws InstanceNotFoundException {

        boolean resultado = servicioConcurso.isRol(nombreUsuario, idConcurso, RolUsuarioConcurso.INSCRITO);

        return resultado;
    }

    @GetMapping("/concursos/{idConcurso}/{nombreUsuario}/esJurado")
    public boolean esJurado(@PathVariable long idConcurso, @PathVariable String nombreUsuario)
            throws InstanceNotFoundException {

        boolean resultado = servicioConcurso.isRol(nombreUsuario, idConcurso, RolUsuarioConcurso.JURADO);

        return resultado;
    }

    @PostMapping("/concursos")
    public ResponseEntity crearConcurso(@RequestParam String userName,
                              @RequestBody ConcursoDto datosConcurso)
            throws InstanceNotFoundException, DatosDeConcursoNoValidosException {

        servicioConcurso.crearConcurso(datosConcurso, userName);

        // After a resource has been created successfully, the server
        // should respond with HTTP 201 (Created)
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/concursos/{nombreConcurso}")
    public ConcursoDto recuperarConcursos(@RequestBody Long idConcurso)
            throws InstanceNotFoundException {

        ConcursoDto concursoDto = servicioConcurso.recuperarDatosConcurso(idConcurso);

        return concursoDto;
    }

    @DeleteMapping("/concursos/{nombreConcurso}")
    public ResponseEntity eliminarConcurso(@RequestBody Long idConcurso)
            throws InstanceNotFoundException {

        servicioConcurso.eliminarConcurso(idConcurso);

        //  (No content) is used to indicate a successful deletion with no
        //  additional information (response body is empty).
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/concursos/{nombreConcurso}/fotografias")
    public ResponseEntity participar(@RequestBody FotografiaDto datosFotografia)
            throws UsuarioNoPuedeParticiparException, InstanceNotFoundException, DatosDeFotografiaNoValidosException {

        servicioConcurso.participarConcurso(datosFotografia);

        // After a resource has been created successfully, the server
        // should respond with HTTP 201 (Created)
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/concursos/{nombreConcurso}/fotografias/enEspera")
    public Block<FotografiaDto> fotografiasEnEspera(@RequestBody FotografiaDto datos,
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "5") int size){

        Block<Fotografia> fotografiaBlock =
                servicioConcurso.recuperarFotografiasModeracion(datos.getIdConcurso(), page, size);

        return new Block<FotografiaDto>(
                FotografiaConversor.toFotografiasDto(fotografiaBlock.getItems()), fotografiaBlock.getExistMoreItems());
    }

    @GetMapping("/concursos/{nombreConcurso}/fotografias/{idFotografia}")
    public FotografiaDto recuperarFotografia(@PathVariable long idFotografia)
            throws InstanceNotFoundException{

        Fotografia fotografia = servicioConcurso.recuperarDatosFotografia(idFotografia);

        return FotografiaConversor.toFotografiaDto(fotografia);
    }

    @GetMapping("/concursos/{nombreConcurso}/fotografias")
    public Block<FotografiaDto> recuperarFotografiasConcurso(@PathVariable String nombreConcurso,
                                                             @RequestParam (defaultValue = "0") int page,
                                                             @RequestParam (defaultValue = "5") int size) {

        Block<Fotografia> fotografias = servicioConcurso.recuperarFotografiasDeConcurso(
                nombreConcurso, page, size);

        Block<FotografiaDto> fotografiaDtoBlock = new Block<>(
                FotografiaConversor.toFotografiasDto(fotografias.getItems()), fotografias.getExistMoreItems());

        return fotografiaDtoBlock;
    }

    @PostMapping("/concursos/{nombreConcurso}/fotografias/{idFotografia}/supervisar")
    public void supervisarFotografia(@RequestBody SupervisarFotografiaDto datosSupervision)
            throws InstanceNotFoundException, DatosSupervisionFotografiaNovalidosException {

        servicioConcurso.supervisarFotografia(datosSupervision.getIdFotografia(),
                datosSupervision.getNombreFotografia(), datosSupervision.getIdConcurso(),
                datosSupervision.getNombreConcurso(), datosSupervision.getDecision(), datosSupervision.getMotivo(),
                datosSupervision.getNombreUsuarioAutor(), datosSupervision.getNombreUsuarioSupervisor());
    }
}