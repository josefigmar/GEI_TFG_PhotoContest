package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.time.LocalDateTime;

@Entity
public class UsuarioVotaFotografia {

    private UsuarioVotaFotografiaPK usuarioVotaFotografiaPK = new UsuarioVotaFotografiaPK();
    private Usuario usuario;
    private Fotografia fotografia;
    private LocalDateTime fechaVoto;
    private Integer puntuacion;

    public UsuarioVotaFotografia() {
    }

    @EmbeddedId
    public UsuarioVotaFotografiaPK getUsuarioVotaFotografiaPK() {
        return usuarioVotaFotografiaPK;
    }

    public void setUsuarioVotaFotografiaPK(UsuarioVotaFotografiaPK usuarioVotaFotografiaPK) {
        this.usuarioVotaFotografiaPK = usuarioVotaFotografiaPK;
    }

    @ManyToOne
    @MapsId("idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @MapsId("idFotografia")
    public Fotografia getFotografia() {
        return fotografia;
    }

    public void setFotografia(Fotografia fotografia) {
        this.fotografia = fotografia;
    }

    public LocalDateTime getFechaVoto() {
        return fechaVoto;
    }

    public void setFechaVoto(LocalDateTime fechaVoto) {
        this.fechaVoto = fechaVoto;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}