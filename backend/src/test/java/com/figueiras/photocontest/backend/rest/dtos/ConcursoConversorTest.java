package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.EstadoConcurso;
import com.figueiras.photocontest.backend.model.entities.UtilidadesParaPruebas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class ConcursoConversorTest {

    @Test
    public void crearConcursoTablaDtoIdTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setIdConcurso((long) 2);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getIdConcurso(), c.getIdConcurso());
    }

    @Test
    public void crearConcursoTablaDtoFotoTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFotoConcurso(UtilidadesParaPruebas.CADENA_LARGA);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getFotografia(), c.getFotoConcurso());
    }

    @Test
    public void crearConcursoTablaDtoNombreTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setNombreConcurso(UtilidadesParaPruebas.CADENA_MEDIA);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getNombre(), c.getNombreConcurso());
    }

    @Test
    public void crearConcursoTablaDtoEstadoTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getEstadoConcurso(), c.getEstadoConcurso().toString());
    }

    @Test
    public void crearConcursoTablaDtoFechaInicioTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getFechaInicio(), Utilidades.toMillis(c.getFechaInicioConcurso()));
    }

    @Test
    public void crearConcursoTablaDtoFechaFinTest () {
        Concurso c = new Concurso();
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);

        ConcursoTablaDto concursoTablaDto = ConcursoConversor.toConcursoTablaDto(c);

        Assertions.assertEquals(concursoTablaDto.getFechaFin(), Utilidades.toMillis(c.getFechaFinConcurso()));
    }

    @Test
    public void crearConcursosTablaDtoTest() {
        Concurso c = new Concurso();
        Concurso c2 = new Concurso();
        c.setIdConcurso((long) 2);
        c.setFotoConcurso(UtilidadesParaPruebas.CADENA_LARGA);
        c.setNombreConcurso(UtilidadesParaPruebas.CADENA_MEDIA);
        c.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c2.setIdConcurso((long) 2);
        c2.setFotoConcurso(UtilidadesParaPruebas.CADENA_LARGA);
        c2.setNombreConcurso(UtilidadesParaPruebas.CADENA_MEDIA);
        c2.setEstadoConcurso(EstadoConcurso.ABIERTO);
        c2.setFechaInicioConcurso(UtilidadesParaPruebas.FECHA_HOY);
        c2.setFechaFinConcurso(UtilidadesParaPruebas.FECHA_HOY);
        List<Concurso> concursoList = Arrays.asList(c, c2);

        List<ConcursoTablaDto> concursoTablaDtos = ConcursoConversor.toConcursosTablaDto(concursoList);

        Assertions.assertTrue(concursoTablaDtos.size() == 2);
    }
}