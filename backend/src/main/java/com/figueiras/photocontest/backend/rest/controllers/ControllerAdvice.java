package com.figueiras.photocontest.backend.rest.controllers;

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

    @ExceptionHandler(InstanceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErroresDto manejarExcepciónInstanceNotFound(InstanceNotFoundException e, Locale locale){

        String nombreMensaje = messageSource.getMessage(e.getName(), null, e.getName(), locale);
        String mensajeExcepcion = messageSource.getMessage(INSTANCE_NOT_FOUND_EXCEPTION_CODE,
                new Object[] {nombreMensaje, e.getName().toString()}, INSTANCE_NOT_FOUND_EXCEPTION_CODE, locale);

        return  new ErroresDto(mensajeExcepcion);
    }
}
