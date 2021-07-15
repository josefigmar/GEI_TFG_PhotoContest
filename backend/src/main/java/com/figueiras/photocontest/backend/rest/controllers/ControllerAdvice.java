package com.figueiras.photocontest.backend.rest.controllers;

import com.figueiras.photocontest.backend.model.exceptions.CampoDuplicadoException;
import com.figueiras.photocontest.backend.model.exceptions.CamposIntroducidosNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.ErroresDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Locale;

/**
 * Punto centralizado de excepciones. Aquí se manejarán excepciones de toda la aplicación. Util para excepciones
 * que no se lancen sólo en un controlador.
 */
@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @Autowired
    MessageSource messageSource;

    private final static String INSTANCE_NOT_FOUND_EXCEPTION_CODE = "project.exceptions.InstanceNotFoundException";
    private final static String CAMPO_DUPLICADO_EXCEPTION_CODE = "project.exceptions.CampoDuplicadoException";
    private final static String CAMPOS_INTRODUCIDOS_NO_VALIDOS_EXCEPTION_CODE =
            "project.exceptions.CamposIntroducidosNoValidosException";

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionInstanceNotFound(InstanceNotFoundException e, Locale locale){

        String nombreMensaje = messageSource.getMessage(e.getName(), null, e.getName(), locale);
        String mensajeExcepcion = messageSource.getMessage(INSTANCE_NOT_FOUND_EXCEPTION_CODE,
                new Object[] {nombreMensaje, e.getName().toString()}, INSTANCE_NOT_FOUND_EXCEPTION_CODE, locale);

        return  new ErroresDto(mensajeExcepcion);
    }

    @ExceptionHandler(CampoDuplicadoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionCampoDuplicado(CampoDuplicadoException e, Locale locale){

        String nombreCampo = messageSource.getMessage(e.getName().toString(), null,
                e.getName().toString(), locale);
        String mensajeExcepcion = messageSource.getMessage(CAMPO_DUPLICADO_EXCEPTION_CODE,
                new Object[] {nombreCampo, e.getKey().toString()}, CAMPO_DUPLICADO_EXCEPTION_CODE, locale);

        return  new ErroresDto(mensajeExcepcion);
    }

    @ExceptionHandler(CamposIntroducidosNoValidosException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepcionCamposNoValidos(CamposIntroducidosNoValidosException e, Locale locale){

        String mensajeExcepcion = messageSource.getMessage(CAMPOS_INTRODUCIDOS_NO_VALIDOS_EXCEPTION_CODE,
                null, CAMPOS_INTRODUCIDOS_NO_VALIDOS_EXCEPTION_CODE, locale);

        return  new ErroresDto(mensajeExcepcion);
    }
}
