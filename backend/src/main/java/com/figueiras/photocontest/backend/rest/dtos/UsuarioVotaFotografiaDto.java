package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioVotaFotografiaDto {

    private String nombreUsuario;
    private long idFotografia;
    private String nombreConcurso;
    private int puntuacion;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }
}
