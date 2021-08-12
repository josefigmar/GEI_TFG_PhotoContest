package com.figueiras.photocontest.backend.rest.dtos;

public class SupervisarFotografiaDto {

    private long idFotografia;
    private long idConcurso;
    private String nombreFotografia;
    private String nombreConcurso;
    private String decision;
    private String motivo;
    private String nombreUsuarioAutor;
    private String nombreUsuarioSupervisor;

    public long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public String getNombreFotografia() {
        return nombreFotografia;
    }

    public void setNombreFotografia(String nombreFotografia) {
        this.nombreFotografia = nombreFotografia;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getNombreUsuarioAutor() {
        return nombreUsuarioAutor;
    }

    public void setNombreUsuarioAutor(String nombreUsuarioAutor) {
        this.nombreUsuarioAutor = nombreUsuarioAutor;
    }

    public String getNombreUsuarioSupervisor() {
        return nombreUsuarioSupervisor;
    }

    public void setNombreUsuarioSupervisor(String nombreUsuarioSupervisor) {
        this.nombreUsuarioSupervisor = nombreUsuarioSupervisor;
    }
}
