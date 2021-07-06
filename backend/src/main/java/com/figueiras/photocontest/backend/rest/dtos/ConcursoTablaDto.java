package com.figueiras.photocontest.backend.rest.dtos;

public class ConcursoTablaDto {

    private Long idConcurso;
    private String fotografia;
    private String nombre;
    private String estadoConcurso;
    private Long fechaInicio;
    private Long fechaFin;

    public ConcursoTablaDto(Long idConcurso, String fotografia, String nombre, String estadoConcurso,
                            Long fechaInicio, Long fechaFin) {
        this.idConcurso = idConcurso;
        this.fotografia = fotografia;
        this.nombre = nombre;
        this.estadoConcurso = estadoConcurso;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(String estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }

    public Long getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Long fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Long fechaFin) {
        this.fechaFin = fechaFin;
    }
}
