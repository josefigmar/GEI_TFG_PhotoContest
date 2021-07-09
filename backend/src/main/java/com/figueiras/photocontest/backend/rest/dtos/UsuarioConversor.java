package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class UsuarioConversor {

    public static UsuarioParticipaConcursoDto toUsuarioParticipaConcursoDto(UsuarioParticipaConcurso usuarioParticipaConcurso){

        UsuarioParticipaConcursoDto usuarioParticipaConcursoDto = new UsuarioParticipaConcursoDto();

        usuarioParticipaConcursoDto.setIdConcurso(usuarioParticipaConcurso.getConcurso().getIdConcurso());
        usuarioParticipaConcursoDto.setNombreConcurso(usuarioParticipaConcurso.getConcurso().getNombreConcurso());
        usuarioParticipaConcursoDto.setRolUsuario(usuarioParticipaConcurso.getRolUsuarioConcurso().toString());
        usuarioParticipaConcursoDto.setFechaInicioParticipacion(
                Utilidades.toMillis(usuarioParticipaConcurso.getFechaInicioParticipacion()));

        return usuarioParticipaConcursoDto;
    }

    public static List<UsuarioParticipaConcursoDto> toUsuarioParticipaConcursosDto(
            Set<UsuarioParticipaConcurso> usuarioParticipaConcursos){

        List<UsuarioParticipaConcursoDto> usuarioParticipaConcursoDtos = new ArrayList<>();

        for(UsuarioParticipaConcurso usuarioParticipaConcurso: usuarioParticipaConcursos){
            usuarioParticipaConcursoDtos.add(toUsuarioParticipaConcursoDto(usuarioParticipaConcurso));
        }

        return usuarioParticipaConcursoDtos;
    }

    public static UsuarioTablaDto toUsuarioTablaDto(Usuario usuario){

        UsuarioTablaDto usuarioTablaDto = new UsuarioTablaDto();

        usuarioTablaDto.setIdUsuario(usuario.getIdUsuario());
        usuarioTablaDto.setFotoPerfil(usuario.getFotoPerfil());
        usuarioTablaDto.setNombreUsuario(usuario.getNombreUsuario());
        usuarioTablaDto.setNombrePilaUsuario(usuario.getNombrePilaUsuario());
        usuarioTablaDto.setApellidosUsuario(usuario.getApellidosUsuario());

        return usuarioTablaDto;
    }

    public static List<UsuarioTablaDto> toUsuariosTablaDto(List<Usuario> usuarios){

        List<UsuarioTablaDto> usuariosTablaDto = new ArrayList<>();

        for(Usuario u: usuarios){

            usuariosTablaDto.add(toUsuarioTablaDto(u));
        }
        return usuariosTablaDto;
    }

    public static UsuarioDto toUsuarioDto(Usuario usuario){

        UsuarioDto usuarioDto = new UsuarioDto();
        List<UsuarioTablaDto> usuariosQueSigueTablaDtos = new ArrayList<>();
        List<UsuarioTablaDto> usuariosQueLoSiguenTablaDtos = new ArrayList<>();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setFotoPerfil(usuario.getFotoPerfil());
        usuarioDto.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNombrePilaUsuario(usuario.getNombrePilaUsuario());
        usuarioDto.setApellidosUsuario(usuario.getApellidosUsuario());
        usuarioDto.setBiografiaUsuario(usuario.getBiografiaUsuario());
        usuarioDto.setEnlaceTwitterUsuario(usuario.getEnlaceTwitterUsuario());
        usuarioDto.setEnlaceFacebookUsuario(usuario.getEnlaceFacebookUsuario());
        usuarioDto.setNumeroSeguidores(Long.valueOf(usuario.getUsuariosQueLoSiguen().size()));
        usuarioDto.setNumeroSeguidos(Long.valueOf(usuario.getUsuariosQueSigue().size()));
        usuarioDto.setCategoriasFotograficasQueLeGustan(
                CategoriaFotograficaConversor.toCategoriaFotograficasDto(
                        usuario.getCategoriaFotograficasQueLeGustan()));
        usuarioDto.setConcursosEnLosQueParticipa(
                UsuarioConversor.toUsuarioParticipaConcursosDto(usuario.getConcursosEnLosQueParticipa()));

        
        return usuarioDto;
    }

    public static List<UsuarioDto> toUsuariosDto(
            List<Usuario> usuarioList){

        List<UsuarioDto> usuariosDto = new ArrayList<>();

        for(Usuario usuario: usuarioList){
            usuariosDto.add(toUsuarioDto(usuario));
        }

        return usuariosDto;
    }

    public static UsuarioAutenticadoDto toUsuarioAutenticadoDto(Usuario usuario, String token){

        UsuarioAutenticadoDto usuarioAutenticadoDto = new UsuarioAutenticadoDto();

        usuarioAutenticadoDto.setUsuarioDto(toUsuarioDto(usuario));
        usuarioAutenticadoDto.setTokenJwt(token);

        return usuarioAutenticadoDto;
    }
}
