package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioParticipaConcursoDto {

    private Long idConcurso;
    private String nombreConcurso;
    private String rolUsuario;
    private Long fechaInicioParticipacion;
    private String estadoConcurso;

    public UsuarioParticipaConcursoDto() {
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public Long getFechaInicioParticipacion() {
        return fechaInicioParticipacion;
    }

    public void setFechaInicioParticipacion(Long fechaInicioParticipacion) {
        this.fechaInicioParticipacion = fechaInicioParticipacion;
    }

    public String getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(String estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }
}
