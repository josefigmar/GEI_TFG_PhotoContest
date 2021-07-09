package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioDao;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuarioDao;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario{

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    UsuarioSigueUsuarioDao usuarioSigueUsuarioDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Block<Usuario> recuperarUsuarios(String nombre, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Usuario> sliceUsuario;

        if(nombre == null){
            sliceUsuario = usuarioDao.findAndOrderByNombreUsuario(pageRequest);
        }else{
            sliceUsuario = usuarioDao.findByNombreUsuario(nombre, pageRequest);
        }

        return new Block<>(sliceUsuario.getContent(), sliceUsuario.hasNext());
    }

    @Override
    public Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException {
        Optional<Usuario> u = usuarioDao.findByNombreUsuario(nombreUsuario);

        if(!u.isPresent()){
            throw  new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
        }

        return u.get();
    }

    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) {

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioDto.getContraseña()));
        usuario.setNombrePilaUsuario(usuarioDto.getNombrePilaUsuario());
        usuario.setApellidosUsuario(usuarioDto.getApellidosUsuario());
        usuario.setCorreoElectronicoUsuario(usuarioDto.getEmail());

        usuarioDao.save(usuario);

    }

    @Override
    public Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(usuarioLoginDto.getNombreUsuario());

        if (!usuarioOptional.isPresent()) {
            //TODO
        }

        if (!passwordEncoder.matches(usuarioLoginDto.getContraseñaUsuario(),
                usuarioOptional.get().getContrasenaUsuario())) {
            //TODO
        }

        return usuarioOptional.get();

    }

    @Override
    public Block<UsuarioSigueUsuario> recuperarSeguidoresDeUsuario(String nombreUsuario, int page, int size) {

        Optional<Usuario> usuario = usuarioDao.findByNombreUsuario(nombreUsuario);

        if(!usuario.isPresent()){
            // todo
        }

        Slice<UsuarioSigueUsuario> sliceSeguidores =
                usuarioSigueUsuarioDao.recuperarSeguidoresDeUsuario(usuario.get().getIdUsuario());

        Block<UsuarioSigueUsuario> usuariosQueLoSiguen =
                new Block<>(sliceSeguidores.getContent(), sliceSeguidores.hasNext());

        return usuariosQueLoSiguen;
    }

    @Override
    public Block<UsuarioSigueUsuario> recuperarSeguidosDeUsuario(String nombreUsuario, int page, int size) {
        Optional<Usuario> usuario = usuarioDao.findByNombreUsuario(nombreUsuario);

        if(!usuario.isPresent()){
            // todo
        }
        Slice<UsuarioSigueUsuario> sliceSeguidos =
                usuarioSigueUsuarioDao.recuperarSeguidosDeUsuario(usuario.get().getIdUsuario());

        Block<UsuarioSigueUsuario> usuariosQueSigue = new Block<>(sliceSeguidos.getContent(), sliceSeguidos.hasNext());

        return usuariosQueSigue;
    }
}
