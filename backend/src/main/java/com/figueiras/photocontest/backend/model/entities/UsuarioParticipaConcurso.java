package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsuarioParticipaConcurso {

    private UsuarioParticipaConcursoPK usuarioParticipaConcursoPK = new UsuarioParticipaConcursoPK();

    private Usuario usuario;
    private Concurso concurso;
    private RolUsuarioConcurso rolUsuarioConcurso;
    private LocalDateTime fechaInicioParticipacion;

    @EmbeddedId
    public UsuarioParticipaConcursoPK getUsuarioParticipaConcursoPK() {
        return usuarioParticipaConcursoPK;
    }

    public void setUsuarioParticipaConcursoPK(UsuarioParticipaConcursoPK usuarioParticipaConcursoPK) {
        this.usuarioParticipaConcursoPK = usuarioParticipaConcursoPK;
    }

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name="idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne
    @MapsId("idConcurso")
    @JoinColumn(name="idConcurso")
    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }

    public RolUsuarioConcurso getRolUsuarioConcurso() {
        return rolUsuarioConcurso;
    }

    public void setRolUsuarioConcurso(RolUsuarioConcurso rolUsuarioConcurso) {
        this.rolUsuarioConcurso = rolUsuarioConcurso;
    }

    public LocalDateTime getFechaInicioParticipacion() {
        return fechaInicioParticipacion;
    }

    public void setFechaInicioParticipacion(LocalDateTime fechaInicioParticipacion) {
        this.fechaInicioParticipacion = fechaInicioParticipacion;
    }
}