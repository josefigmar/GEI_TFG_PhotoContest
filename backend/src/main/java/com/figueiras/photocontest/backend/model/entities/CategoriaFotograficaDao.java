package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoriaFotograficaDao extends PagingAndSortingRepository<CategoriaFotografica, Long> {

    List<CategoriaFotografica> findByNombreCategoriaOrderByNombreCategoria(String nombreCategoria);

}