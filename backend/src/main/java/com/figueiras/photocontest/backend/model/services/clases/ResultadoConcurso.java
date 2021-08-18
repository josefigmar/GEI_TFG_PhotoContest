package com.figueiras.photocontest.backend.model.services.clases;

public class ResultadoConcurso {

    private int puntuacionFinal;
    private long idFotografia;

    public int getPuntuacionFinal() {
        return puntuacionFinal;
    }

    public void setPuntuacionFinal(int puntuacionFinal) {
        this.puntuacionFinal = puntuacionFinal;
    }

    public long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(long idFotografia) {
        this.idFotografia = idFotografia;
    }
}
