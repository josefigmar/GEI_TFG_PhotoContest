package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioLoginDto {

    private String nombreUsuario;
    private String contraseñaUsuario;

    public UsuarioLoginDto(String nombreUsuario, String contraseñaUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }
}
