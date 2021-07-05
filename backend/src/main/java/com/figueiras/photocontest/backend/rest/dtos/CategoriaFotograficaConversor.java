package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoriaFotograficaConversor {

    public static CategoriaFotograficaDto toCategoriaFotograficaDto(CategoriaFotografica categoriaFotografica){

        CategoriaFotograficaDto categoriaFotograficaDto = new CategoriaFotograficaDto();

        categoriaFotograficaDto.setIdCategoria(categoriaFotografica.getIdCategoria());
        categoriaFotograficaDto.setNombreCategoria(categoriaFotografica.getNombreCategoria());
        categoriaFotograficaDto.setDescripcion(categoriaFotografica.getDescripcionCategoria());

        return categoriaFotograficaDto;
    }

    public static List<CategoriaFotograficaDto> toCategoriaFotograficasDto(
            Set<CategoriaFotografica> categoriaFotograficas){

        List<CategoriaFotograficaDto> categoriaFotograficaDtoList = new ArrayList<>();
        for(CategoriaFotografica categoriaFotografica: categoriaFotograficas){
            categoriaFotograficaDtoList.add(toCategoriaFotograficaDto(categoriaFotografica));
        }

        return categoriaFotograficaDtoList;
    }
}