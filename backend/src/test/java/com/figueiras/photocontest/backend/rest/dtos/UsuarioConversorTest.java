package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UtilidadesParaPruebas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class UsuarioConversorTest {

    private Usuario crearUsuario() {
        Usuario u = new Usuario();
        u.setIdUsuario((long) 1);
        u.setFotoPerfil(UtilidadesParaPruebas.CADENA_LARGA);
        u.setNombreUsuario(UtilidadesParaPruebas.CADENA_MEDIA);
        u.setNombrePilaUsuario(UtilidadesParaPruebas.CADENA_MEDIA);
        u.setApellidosUsuario(UtilidadesParaPruebas.CADENA_MEDIA);
        u.setBiografiaUsuario(UtilidadesParaPruebas.CADENA_LARGA);
        u.setEnlaceTwitterUsuario(UtilidadesParaPruebas.CADENA_MEDIA);
        u.setEnlaceFacebookUsuario(UtilidadesParaPruebas.CADENA_MEDIA);

        return u;
    }

    // USUARIO-TABLA-DTO

    @Test
    public void crearUsuarioTablaDtoIdTest() {
        Usuario u = crearUsuario();

        UsuarioTablaDto usuarioTablaDto = UsuarioConversor.toUsuarioTablaDto(u);

        Assertions.assertEquals(usuarioTablaDto.getIdUsuario(), u.getIdUsuario());
    }

    @Test
    public void crearUsuarioTablaDtoFotoTest() {
        Usuario u = crearUsuario();

        UsuarioTablaDto usuarioTablaDto = UsuarioConversor.toUsuarioTablaDto(u);

        Assertions.assertEquals(usuarioTablaDto.getFotoPerfil(), u.getFotoPerfil());
    }

    @Test
    public void crearUsuarioTablaDtoNombreTest() {
        Usuario u = crearUsuario();

        UsuarioTablaDto usuarioTablaDto = UsuarioConversor.toUsuarioTablaDto(u);

        Assertions.assertEquals(usuarioTablaDto.getNombreUsuario(), u.getNombreUsuario());
    }

    @Test
    public void crearUsuarioTablaDtoNombrePilaTest() {
        Usuario u = crearUsuario();

        UsuarioTablaDto usuarioTablaDto = UsuarioConversor.toUsuarioTablaDto(u);

        Assertions.assertEquals(usuarioTablaDto.getNombrePilaUsuario(), u.getNombrePilaUsuario());
    }

    @Test
    public void crearUsuarioTablaDtoApellidosTest() {
        Usuario u = crearUsuario();

        UsuarioTablaDto usuarioTablaDto = UsuarioConversor.toUsuarioTablaDto(u);

        Assertions.assertEquals(usuarioTablaDto.getApellidosUsuario(), u.getApellidosUsuario());
    }

    @Test
    public void crearUsuariosTablaDtoTest() {
        Usuario u = crearUsuario();
        Usuario u2 = crearUsuario();
        List<Usuario> usuarioList = Arrays.asList(u, u2);

        List<UsuarioTablaDto> usuarioTablaDtos = UsuarioConversor.toUsuariosTablaDto(usuarioList);

        Assertions.assertEquals(2, usuarioTablaDtos.size());
    }

    // USUARIO-DTO


    @Test
    public void crearUsuarioDtoIdTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getIdUsuario(), u.getIdUsuario());
    }

    @Test
    public void crearUsuarioDtoFotoTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getFotoPerfil(), u.getFotoPerfil());
    }

    @Test
    public void crearUsuarioDtoNombreTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getNombreUsuario(), u.getNombreUsuario());
    }

    @Test
    public void crearUsuarioDtoEmailTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getEmail(), u.getCorreoElectronicoUsuario());
    }

    @Test
    public void crearUsuarioDtoNombrePilaTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getNombrePilaUsuario(), u.getNombrePilaUsuario());
    }

    @Test
    public void crearUsuarioDtoApellidosTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getApellidosUsuario(), u.getApellidosUsuario());
    }

    @Test
    public void crearUsuarioDtoBiografiaTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getBiografiaUsuario(), u.getBiografiaUsuario());
    }

    @Test
    public void crearUsuarioDtoEnlaceTwitterTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getEnlaceTwitterUsuario(), u.getEnlaceTwitterUsuario());
    }

    @Test
    public void crearUsuarioDtoEnlaceFacebookTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getEnlaceFacebookUsuario(), u.getEnlaceFacebookUsuario());
    }

    @Test
    public void crearUsuarioDtoNumeroSeguidoresTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getNumeroSeguidores(), 0);
    }

    @Test
    public void crearUsuarioDtoNumeroSeguidosTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getNumeroSeguidos(), 0);
    }

    // 0 equivale a español

    @Test
    public void crearUsuarioDtoLenguajeTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getLenguaje(), (long) 0);
    }

    @Test
    public void crearUsuarioDtoConcursosTest() {
        Usuario u = crearUsuario();

        UsuarioDto usuarioDto = UsuarioConversor.toUsuarioDto(u);

        Assertions.assertEquals(usuarioDto.getConcursosEnLosQueParticipa(),
                UsuarioConversor.toUsuarioParticipaConcursosDto(u.getConcursosEnLosQueParticipa()));
    }
}
