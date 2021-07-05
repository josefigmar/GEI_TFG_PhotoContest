package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Concurso;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

public class ConcursoConversor {

    public static List<ConcursoTablaDto> toConcursosTablaDto(List<Concurso> concursos){
        List<ConcursoTablaDto> concursoTablaDtos =
                concursos.stream().map(i -> toConcursoTablaDto(i)).collect(Collectors.toList());

        return concursoTablaDtos;
    }


    private static ConcursoTablaDto toConcursoTablaDto(Concurso concurso){
        return new ConcursoTablaDto(concurso.getIdConcurso(), concurso.getFotoConcurso(), concurso.getNombreConcurso(),
                concurso.getEstadoConcurso().toString(), Utilidades.toMillis(concurso.getFechaInicioConcurso()),
                Utilidades.toMillis(concurso.getFechaFinConcurso()));
    }

}