package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsuarioSigueUsuario {

    private Long idUsuarioSigueUsuario;
    private Usuario usuarioSeguidor;
    private Usuario usuarioSeguido;
    private LocalDateTime fechaSeguida;

    public UsuarioSigueUsuario() {
    }

    @Id
    public Long getIdUsuarioSigueUsuario() {
        return idUsuarioSigueUsuario;
    }

    public void setIdUsuarioSigueUsuario(Long idUsuarioSigueUsuario) {
        this.idUsuarioSigueUsuario = idUsuarioSigueUsuario;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idUsuarioSeguidor")
    public Usuario getUsuarioSeguidor() {
        return usuarioSeguidor;
    }

    public void setUsuarioSeguidor(Usuario usuarioSeguidor) {
        this.usuarioSeguidor = usuarioSeguidor;
    }

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="idUsuarioSeguido")
    public Usuario getUsuarioSeguido() {
        return usuarioSeguido;
    }

    public void setUsuarioSeguido(Usuario usuarioSeguido) {
        this.usuarioSeguido = usuarioSeguido;
    }

    public LocalDateTime getFechaSeguida() {
        return fechaSeguida;
    }

    public void setFechaSeguida(LocalDateTime fechaSeguida) {
        this.fechaSeguida = fechaSeguida;
    }
}