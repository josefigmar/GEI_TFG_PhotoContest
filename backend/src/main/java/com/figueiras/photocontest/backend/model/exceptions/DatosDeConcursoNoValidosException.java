package com.figueiras.photocontest.backend.model.exceptions;

import com.figueiras.photocontest.backend.rest.dtos.ErroresDto;

public class DatosDeConcursoNoValidosException extends Exception{

    private ErroresDto erroresDto;

    public DatosDeConcursoNoValidosException(ErroresDto erroresDto){
        this.erroresDto = erroresDto;
    }

    public ErroresDto getErroresDto() {
        return erroresDto;
    }

    public void setErroresDto(ErroresDto erroresDto) {
        this.erroresDto = erroresDto;
    }
}
