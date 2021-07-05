package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.CategoriaFotografica;

import javax.validation.constraints.Size;
import java.util.List;

public class UsuarioDto {

    private Long idUsuario;
    private String fotoPerfil;
    private String nombreUsuario;
    private String nombrePilaUsuario;
    private String apellidosUsuario;
    private String biografiaUsuario;
    private String enlaceTwitterUsuario;
    private String enlaceFacebookUsuario;
    private List<CategoriaFotograficaDto> categoriasFotograficasQueLeGustan;
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

    public void setEnlaceFacebookUsuario(String enlaceFacebookUsuario) {
        this.enlaceFacebookUsuario = enlaceFacebookUsuario;
    }

    public List<CategoriaFotograficaDto> getCategoriasFotograficasQueLeGustan() {
        return categoriasFotograficasQueLeGustan;
    }

    public void setCategoriasFotograficasQueLeGustan(List<CategoriaFotograficaDto> categoriasFotograficasQueLeGustan) {
        this.categoriasFotograficasQueLeGustan = categoriasFotograficasQueLeGustan;
    }

    public List<UsuarioParticipaConcursoDto> getConcursosEnLosQueParticipa() {
        return concursosEnLosQueParticipa;
    }

    public void setConcursosEnLosQueParticipa(List<UsuarioParticipaConcursoDto> concursosEnLosQueParticipa) {
        this.concursosEnLosQueParticipa = concursosEnLosQueParticipa;
    }
}
