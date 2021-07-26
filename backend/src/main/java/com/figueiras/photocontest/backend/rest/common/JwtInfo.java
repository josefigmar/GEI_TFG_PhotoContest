package com.figueiras.photocontest.backend.rest.common;

public class JwtInfo {

    private Long idUsuario;
    private String nombreUsuario;
    private String rolUsuario;


    public JwtInfo(Long idUsuario) {

        this.idUsuario = idUsuario;

    }

    public JwtInfo(Long idUsuario, String nombreUsuario, String rolUsuario) {

        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;

    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }
}
