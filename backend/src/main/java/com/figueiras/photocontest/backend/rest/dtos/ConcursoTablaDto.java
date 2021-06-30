package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.EstadoConcurso;

public class ConcursoTablaDto {

    private Long idConcurso;
    private byte[] fotografia;
    private String nombre;
    private int estadoConcurso;
    private String[] categorias;
    private Long fechaInicio;
    private Long fechaFin;

    public ConcursoTablaDto(Long idConcurso, byte[] fotografia, String nombre, int estadoConcurso,
                            String[] categorias, Long fechaInicio, Long fechaFin) {
        this.idConcurso = idConcurso;
        this.fotografia = fotografia;
        this.nombre = nombre;
        this.estadoConcurso = estadoConcurso;
        this.categorias = categorias;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(int estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }

    public String[] getCategorias() {
        return categorias;
    }

    public void setCategorias(String[] categorias) {
        this.categorias = categorias;
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
