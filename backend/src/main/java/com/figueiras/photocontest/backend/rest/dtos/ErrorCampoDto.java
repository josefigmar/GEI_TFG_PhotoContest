package com.figueiras.photocontest.backend.rest.dtos;

public class ErrorCampoDto {

    private String nombreCampo;
    private String mensaje;

    public ErrorCampoDto(String fieldName, String message) {

        this.nombreCampo = fieldName;
        this.mensaje = message;

    }

    public String getNombreCampo() {
        return nombreCampo;
    }

    public void setNombreCampo(String nombreCampo) {
        this.nombreCampo = nombreCampo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

}