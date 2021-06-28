package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UsuarioGustaCategoria{

    private Long idUsuarioGustaCategoria;
    private Usuario usuario;
    private CategoriaFotografica categoriaFotografica;

    public UsuarioGustaCategoria() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdUsuarioGustaCategoria() {
        return idUsuarioGustaCategoria;
    }

    public void setIdUsuarioGustaCategoria(Long idUsuarioGustaCategoria) {
        this.idUsuarioGustaCategoria = idUsuarioGustaCategoria;
    }

    @Column(name="idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Column(name="idCategoria")
    public CategoriaFotografica getCategoriaFotografica() {
        return categoriaFotografica;
    }

    public void setCategoriaFotografica(CategoriaFotografica categoriaFotografica) {
        this.categoriaFotografica = categoriaFotografica;
    }
}