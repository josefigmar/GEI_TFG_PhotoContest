package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class UsuarioDto {

    private Long idUsuario;
    private String fotoPerfil;
    private String contraseña;
    private String email;
    private String nombreUsuario;
    private String nombrePilaUsuario;
    private String apellidosUsuario;
    private String biografiaUsuario;
    private String enlaceTwitterUsuario;
    private String enlaceFacebookUsuario;
    private Long numeroSeguidores;
    private Long numeroSeguidos;
    private boolean cuentaEliminada;
    private int lenguaje;
    private List<UsuarioParticipaConcursoDto> concursosEnLosQueParticipa;

    public UsuarioDto() {
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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getBiografiaUsuario() {
        return biografiaUsuario;
    }

    public void setBiografiaUsuario(String biografiaUsuario) {
        this.biografiaUsuario = biografiaUsuario;
    }

    public String getEnlaceTwitterUsuario() {
        return enlaceTwitterUsuario;
    }

    public void setEnlaceTwitterUsuario(String enlaceTwitterUsuario) {
        this.enlaceTwitterUsuario = enlaceTwitterUsuario;
    }

    public String getEnlaceFacebookUsuario() {
        return enlaceFacebookUsuario;
    }

    public Long getNumeroSeguidores() {
        return numeroSeguidores;
    }

    public void setNumeroSeguidores(Long numeroSeguidores) {
        this.numeroSeguidores = numeroSeguidores;
    }

    public Long getNumeroSeguidos() {
        return numeroSeguidos;
    }

    public void setNumeroSeguidos(Long numeroSeguidos) {
        this.numeroSeguidos = numeroSeguidos;
    }

    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }

    public void setCuentaEliminada(boolean cuentaEliminada) {
        this.cuentaEliminada = cuentaEliminada;
    }

    public int getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(int lenguaje) {
        this.lenguaje = lenguaje;
    }

    public void setEnlaceFacebookUsuario(String enlaceFacebookUsuario) {
        this.enlaceFacebookUsuario = enlaceFacebookUsuario;
    }

    public List<UsuarioParticipaConcursoDto> getConcursosEnLosQueParticipa() {
        return concursosEnLosQueParticipa;
    }

    public void setConcursosEnLosQueParticipa(List<UsuarioParticipaConcursoDto> concursosEnLosQueParticipa) {
        this.concursosEnLosQueParticipa = concursosEnLosQueParticipa;
    }
}