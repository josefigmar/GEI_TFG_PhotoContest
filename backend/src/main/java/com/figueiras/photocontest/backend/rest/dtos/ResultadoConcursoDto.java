package com.figueiras.photocontest.backend.rest.dtos;

public class ResultadoConcursoDto {

    private int puntuacionFinal;
    private int posicion;
    private FotografiaDto fotografiaDto;

    public ResultadoConcursoDto(int puntuacionFinal, int posicion, FotografiaDto fotografiaDto) {
        this.puntuacionFinal = puntuacionFinal;
        this.posicion = posicion;
        this.fotografiaDto = fotografiaDto;
    }

    public int getPuntuacionFinal() {
        return puntuacionFinal;
    }

    public void setPuntuacionFinal(int puntuacionFinal) {
        this.puntuacionFinal = puntuacionFinal;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public FotografiaDto getFotografiaDto() {
        return fotografiaDto;
    }

    public void setFotografiaDto(FotografiaDto fotografiaDto) {
        this.fotografiaDto = fotografiaDto;
    }
}
