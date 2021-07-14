package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioTablaDto {

    private Long idUsuario;
    private String fotoPerfil;
    private String nombreUsuario;
    private String nombrePilaUsuario;
    private String apellidosUsuario;
    private boolean cuentaEliminada;

    public UsuarioTablaDto() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFotoPerfil() {
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil) {
        this.fotoPerfil = fotoPerfil;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombrePilaUsuario() {
        return nombrePilaUsuario;
    }

    public void setNombrePilaUsuario(String nombrePilaUsuario) {
        this.nombrePilaUsuario = nombrePilaUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }

    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }

    public void setCuentaEliminada(boolean cuentaEliminada) {
        this.cuentaEliminada = cuentaEliminada;
    }
}