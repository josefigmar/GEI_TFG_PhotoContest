package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import com.figueiras.photocontest.backend.model.entities.CategoriaFotograficaDao;
import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.EstadoConcurso;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioConcurso;
import com.figueiras.photocontest.backend.rest.dtos.CategoriaFotograficaConversor;
import com.figueiras.photocontest.backend.rest.dtos.CategoriaFotograficaDto;
import com.figueiras.photocontest.backend.rest.dtos.ConcursoTablaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static com.figueiras.photocontest.backend.rest.dtos.ConcursoConversor.toConcursosTablaDto;

@RestController
@RequestMapping("/catalogo-concursos")
public class ControladorCatalogo {

    @Autowired
    private ServicioConcurso servicioConcurso;
    @Autowired
    private CategoriaFotograficaDao categoriaFotograficaDao;

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
        // El frontend no funcionaba si el estado Abierto tenía el valor 0. Se suma 1 a los estados en el Frontend y se
        // vuelve a restar aquí.
        Block<Concurso> blockConcursos = servicioConcurso.recuperarConcursos(idCategoria,
                estado != null? estado - 1 : null, nombre, page, size);

        return new Block<>(toConcursosTablaDto( blockConcursos.getItems()), blockConcursos.getExistMoreItems());
    }
}
