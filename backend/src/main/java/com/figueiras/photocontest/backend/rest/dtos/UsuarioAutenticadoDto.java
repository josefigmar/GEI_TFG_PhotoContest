package com.figueiras.photocontest.backend.rest.dtos;

public class UsuarioAutenticadoDto {

    private String tokenJwt;
    private UsuarioDto usuarioDto;

    public UsuarioAutenticadoDto() {
    }

    public String getTokenJwt() {
        return tokenJwt;
    }

    public void setTokenJwt(String tokenJwt) {
        this.tokenJwt = tokenJwt;
    }

    public UsuarioDto getUsuarioDto() {
        return usuarioDto;
    }

    public void setUsuarioDto(UsuarioDto usuarioDto) {
        this.usuarioDto = usuarioDto;
    }
}
