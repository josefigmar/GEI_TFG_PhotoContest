package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;

public class UsuarioGustaCategoria{

    private Usuario usuario;
    private CategoriaFotografica categoriaFotografica;

    public UsuarioGustaCategoria() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CategoriaFotografica getCategoriaFotografica() {
        return categoriaFotografica;
    }

    public void setCategoriaFotografica(CategoriaFotografica categoriaFotografica) {
        this.categoriaFotografica = categoriaFotografica;
    }
}