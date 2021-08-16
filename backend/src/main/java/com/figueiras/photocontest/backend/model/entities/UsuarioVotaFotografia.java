package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsuarioVotaFotografia {

    private UsuarioVotaFotografiaPK usuarioVotaFotografiaPK = new UsuarioVotaFotografiaPK();
    private Usuario usuario;
    private Fotografia fotografia;
    private LocalDateTime fechaVoto;
    private Concurso concurso;
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
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @MapsId("idFotografia")
    @JoinColumn(name = "idFotografia")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idConcurso")
    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }
}