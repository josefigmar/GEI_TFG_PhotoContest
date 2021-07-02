package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;

public class CategoriaFotograficaConversor {

    public static CategoriaFotograficaDto toCategoriaFotograficaDto(CategoriaFotografica categoriaFotografica){

        CategoriaFotograficaDto categoriaFotograficaDto = new CategoriaFotograficaDto();

        categoriaFotograficaDto.setIdCategoria(categoriaFotografica.getIdCategoria());
        categoriaFotograficaDto.setNombreCategoria(categoriaFotografica.getNombreCategoria());
        categoriaFotograficaDto.setDescripcion(categoriaFotografica.getDescripcionCategoria());

        return categoriaFotograficaDto;
    }
}