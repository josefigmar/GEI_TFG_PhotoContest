package com.figueiras.photocontest.backend.rest.dtos;

import javax.validation.constraints.Size;

public class CategoriaFotograficaDto {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaFotograficaDto() {
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}