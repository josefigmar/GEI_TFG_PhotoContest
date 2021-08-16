package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class DatosParaVotarDto {

    private boolean haVotado;
    private int puntuacion;
    private int puntuacionTotal;
    private int numeroDeVotosUsuario;
    private int numeroMaximoVotosPorUsuarioConcurso;
    private List<Integer> puntuacionesEurovision;
    private boolean ocultarVotos;

    public boolean isHaVotado() {
        return haVotado;
    }

    public void setHaVotado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    public int getPuntuacion() {
        return this.puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public int getPuntuacionTotal() {
        return puntuacionTotal;
    }

    public void setPuntuacionTotal(int puntuacionTotal) {
        this.puntuacionTotal = puntuacionTotal;
    }

    public int getNumeroDeVotosUsuario() {
        return numeroDeVotosUsuario;
    }

    public void setNumeroDeVotosUsuario(int numeroDeVotosUsuario) {
        this.numeroDeVotosUsuario = numeroDeVotosUsuario;
    }

    public int getNumeroMaximoVotosPorUsuarioConcurso() {
        return numeroMaximoVotosPorUsuarioConcurso;
    }

    public void setNumeroMaximoVotosPorUsuarioConcurso(int numeroMaximoVotosPorUsuarioConcurso) {
        this.numeroMaximoVotosPorUsuarioConcurso = numeroMaximoVotosPorUsuarioConcurso;
    }

    public List<Integer> getPuntuacionesEurovision() {
        return puntuacionesEurovision;
    }

    public void setPuntuacionesEurovision(List<Integer> puntuacionesEurovision) {
        this.puntuacionesEurovision = puntuacionesEurovision;
    }

    public boolean isOcultarVotos() {
        return ocultarVotos;
    }

    public void setOcultarVotos(boolean ocultarVotos) {
        this.ocultarVotos = ocultarVotos;
    }
}
