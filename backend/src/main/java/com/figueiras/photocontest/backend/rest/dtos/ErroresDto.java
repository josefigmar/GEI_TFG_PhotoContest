package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class ErroresDto {

    private String errorGlobal;
    private List<ErrorCampoDto> erroresCampos;

    public ErroresDto(String errorGlobal) {
        this.errorGlobal = errorGlobal;
    }

    public ErroresDto(List<ErrorCampoDto> erroresCampos) {

        this.erroresCampos = erroresCampos;

    }

    public String getErrorGlobal() {
        return errorGlobal;
    }

    public void setErrorGlobal(String errorGlobal) {
        this.errorGlobal = errorGlobal;
    }

    public List<ErrorCampoDto> getErroresCampos() {
        return erroresCampos;
    }

    public void setErroresCampos(List<ErrorCampoDto> erroresCampos) {
        this.erroresCampos = erroresCampos;
    }

}