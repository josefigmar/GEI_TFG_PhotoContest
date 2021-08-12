package com.figueiras.photocontest.backend.model.exceptions;

import com.figueiras.photocontest.backend.rest.dtos.ErroresDto;

public class DatosSupervisionFotografiaNovalidosException extends Exception{

    private ErroresDto erroresDto;

    public DatosSupervisionFotografiaNovalidosException() {}

    public DatosSupervisionFotografiaNovalidosException(ErroresDto erroresDto){
        this.erroresDto = erroresDto;
    }

    public ErroresDto getErroresDto() {
        return erroresDto;
    }
}
