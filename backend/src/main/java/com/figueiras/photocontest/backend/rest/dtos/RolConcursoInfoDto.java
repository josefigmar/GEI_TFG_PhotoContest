package com.figueiras.photocontest.backend.rest.dtos;

public class RolConcursoInfoDto {

    // Atributos de salida
    private boolean participa;
    private String RolConcurso;
    private String TipoVoto;
    private String tipoVotante;
    private String estadoConcurso;

    // Atributos de entrada
    private String nombreConcurso;
    private String nombreUsuario;

    public boolean isParticipa() {
        return participa;
    }

    public void setParticipa(boolean participa) {
        this.participa = participa;
    }

    public String getRolConcurso() {
        return RolConcurso;
    }

    public void setRolConcurso(String rolConcurso) {
        RolConcurso = rolConcurso;
    }

    public String getTipoVoto() {
        return TipoVoto;
    }

    public void setTipoVoto(String tipoVoto) {
        TipoVoto = tipoVoto;
    }

    public String getTipoVotante() {
        return tipoVotante;
    }

    public void setTipoVotante(String tipoVotante) {
        this.tipoVotante = tipoVotante;
    }

    public String getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(String estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
}
