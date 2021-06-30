package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.services.Block;
import com.figueiras.photocontest.backend.model.services.ServicioConcurso;
import com.figueiras.photocontest.backend.rest.dtos.ConcursoTablaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.figueiras.photocontest.backend.rest.dtos.ConcursoConversor.toConcursosTablaDto;

@RestController
@RequestMapping("/catalogo")
public class ControladorCatalogo {

    @Autowired
    private ServicioConcurso servicioConcurso;

    @RequestMapping("/concursos")
    public Block<ConcursoTablaDto> recuperarConcursos(@RequestParam(required = false) Long idCategoria,
                                                      @RequestParam(required = false) Integer estado,
                                                      @RequestParam(required = false) String nombre,
                                                      @RequestParam(defaultValue="0") int page,
                                                      @RequestParam(defaultValue="5") int size){
        Block<Concurso> blockConcursos = servicioConcurso.recuperarConcursos(idCategoria,
                estado, nombre, page, size);

        return new Block<>(toConcursosTablaDto( blockConcursos.getItems()), blockConcursos.getExistMoreItems());
    }


}
