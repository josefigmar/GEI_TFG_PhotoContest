package com.figueiras.photocontest.backend.model.exceptions;

import com.figueiras.photocontest.backend.rest.dtos.ErroresDto;

public class UsuarioNoPuedeParticiparException extends Exception {

    private ErroresDto erroresDto;

    public UsuarioNoPuedeParticiparException(ErroresDto erroresDto){
        this.erroresDto = erroresDto;
    }

    public ErroresDto getErroresDto() {
        return erroresDto;
    }

    public void setErroresDto(ErroresDto erroresDto) {
        this.erroresDto = erroresDto;
    }
}
