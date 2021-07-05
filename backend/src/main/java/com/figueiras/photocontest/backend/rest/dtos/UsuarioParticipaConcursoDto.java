package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioParticipaConcursoDto {

    private Long idConcurso;
    private String rolUsuario;
    private Long fechaInicioParticipacion;

    public UsuarioParticipaConcursoDto() {
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
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
}
