package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CategoriaFotografica {

    private Long idCategoria;
    private String nombreCategoria;
    private String descripcion;

    public CategoriaFotografica(){}

    public void setIdCategoria(Long idCategoria)
    {
        this.idCategoria = idCategoria;
    }

    @Id
    public Long getIdCategoria()
    {
        return idCategoria;
    }

    @Column(length = 50)
    public String getNombreCategoria()
    {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria)
    {
        this.nombreCategoria = nombreCategoria;
    }

    @Column(length = 200)
    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }
}
