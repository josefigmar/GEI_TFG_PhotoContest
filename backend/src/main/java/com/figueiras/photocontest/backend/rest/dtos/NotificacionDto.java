package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Usuario;


public class NotificacionDto {

    private Long idNotificacion;
    private String nombreNotificacion;
    private String previsualizacionNotificacion;
    private String mensajeNotificacion;
    private String fotoNotificacion;
    private Long fechaCreacion;
    private boolean leida;

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

    public String getPrevisualizacionNotificacion() {
        return previsualizacionNotificacion;
    }

    public void setPrevisualizacionNotificacion(String previsualizacionNotificacion) {
        this.previsualizacionNotificacion = previsualizacionNotificacion;
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

    public Long getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Long fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public boolean isLeida() {
        return leida;
    }

    public void setLeida(boolean leida) {
        this.leida = leida;
    }
}