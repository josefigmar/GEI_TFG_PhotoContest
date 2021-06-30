package com.figueiras.photocontest.backend.model.entities.Daos;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CategoriaFotograficaDao extends PagingAndSortingRepository<CategoriaFotografica, Long> {

    List<CategoriaFotografica> findByNombreCategoriaOrderByNombreCategoria(String nombreCategoria);

}