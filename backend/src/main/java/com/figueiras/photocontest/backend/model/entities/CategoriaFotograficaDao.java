package com.figueiras.photocontest.backend.model.entities;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CategoriaFotograficaDao extends PagingAndSortingRepository<CategoriaFotografica, Long> {

    List<CategoriaFotografica> findByNombreCategoriaOrderByNombreCategoria(String nombreCategoria);

    Optional<CategoriaFotografica> findByNombreCategoria(String nombreCategoria);

}