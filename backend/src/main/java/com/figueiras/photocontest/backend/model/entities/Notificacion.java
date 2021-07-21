package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Notificacion {

    private Long idNotificacion;
    @Size(min = 1, max = 50)
    private String nombreNotificacion;
    @Size(min = 1, max = 200)
    private String mensajeNotificacion;
    private String fotoNotificacion;
    private LocalDateTime fechaCreacion;
    private boolean leida;
    private Usuario usuario;

    public Notificacion() {
        leida = false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdNotificacion() {
        return idNotificacion;
    }

    public void setIdNotificacion(Long idNotificacion) {
        this.idNotificacion = idNotificacion;
    }

    public String getNombreNotificacion() {
        return nombreNotificacion;
    }

    public void setNombreNotificacion(String nombreNotificacion) {
        this.nombreNotificacion = nombreNotificacion;
    }

    public String getMensajeNotificacion() {
        return mensajeNotificacion;
    }

    public void setMensajeNotificacion(String mensajeNotificacion) {
        this.mensajeNotificacion = mensajeNotificacion;
    }

    public String getFotoNotificacion() {
        return fotoNotificacion;
    }

    public void setFotoNotificacion(String fotoNotificacion) {
        this.fotoNotificacion = fotoNotificacion;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}