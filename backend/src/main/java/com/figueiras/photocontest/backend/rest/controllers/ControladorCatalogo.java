package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import com.figueiras.photocontest.backend.model.entities.CategoriaFotograficaDao;
import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.EstadoConcurso;
import com.figueiras.photocontest.backend.model.exceptions.DatosDeConcursoNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioConcurso;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

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

    @GetMapping("/categorias")
    public List<CategoriaFotograficaDto> recuperarCategorias(){

        Iterable<CategoriaFotografica> categoriaFotograficaIterator = categoriaFotograficaDao.findAll();
        List<CategoriaFotograficaDto> categoriaFotograficaDtoList = new ArrayList<>();

        for(CategoriaFotografica cf: categoriaFotograficaIterator){
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

    @PostMapping("/concursos")
    public void crearConcurso(@RequestParam String userName,
                              @RequestBody CrearConcursoDto datosConcurso)
            throws InstanceNotFoundException, DatosDeConcursoNoValidosException {

        servicioConcurso.crearConcurso(datosConcurso, userName);
    }
}
