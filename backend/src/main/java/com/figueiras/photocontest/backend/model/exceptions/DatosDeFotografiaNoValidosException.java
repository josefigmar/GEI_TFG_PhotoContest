package com.figueiras.photocontest.backend.model.exceptions;

import com.figueiras.photocontest.backend.rest.dtos.ErroresDto;

public class DatosDeFotografiaNoValidosException extends Exception {

    private ErroresDto erroresDto;

    public DatosDeFotografiaNoValidosException(ErroresDto erroresDto){
        this.erroresDto = erroresDto;
    }

    public ErroresDto getErroresDto() {
        return erroresDto;
    }

    public void setErroresDto(ErroresDto erroresDto) {
        this.erroresDto = erroresDto;
    }
}
