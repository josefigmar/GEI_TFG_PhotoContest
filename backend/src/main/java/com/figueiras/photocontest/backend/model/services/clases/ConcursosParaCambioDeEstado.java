package com.figueiras.photocontest.backend.model.services.clases;

import com.figueiras.photocontest.backend.model.entities.Concurso;

import java.util.List;

public class ConcursosParaCambioDeEstado {

    private List<Concurso> concursosEnPreparacionAAbierto;
    private List<Concurso> concursosEnAbiertoAVotacion;
    private List<Concurso> concursosEnVotacionAFinalizado;

    public List<Concurso> getConcursosEnPreparacionAAbierto() {
        return concursosEnPreparacionAAbierto;
    }

    public void setConcursosEnPreparacionAAbierto(List<Concurso> concursosEnPreparacionAAbierto) {
        this.concursosEnPreparacionAAbierto = concursosEnPreparacionAAbierto;
    }

    public List<Concurso> getConcursosEnAbiertoAVotacion() {
        return concursosEnAbiertoAVotacion;
    }

    public void setConcursosEnAbiertoAVotacion(List<Concurso> concursosEnAbiertoAVotacion) {
        this.concursosEnAbiertoAVotacion = concursosEnAbiertoAVotacion;
    }

    public List<Concurso> getConcursosEnVotacionAFinalizado() {
        return concursosEnVotacionAFinalizado;
    }

    public void setConcursosEnVotacionAFinalizado(List<Concurso> concursosEnVotacionAFinalizado) {
        this.concursosEnVotacionAFinalizado = concursosEnVotacionAFinalizado;
    }
}
