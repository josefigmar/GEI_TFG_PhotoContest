package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioDao;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioConversor;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioTablaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario{

    @Autowired
    UsuarioDao usuarioDao;

    @Override
    public Block<UsuarioTablaDto> recuperarUsuarios(String nombre, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Usuario> sliceUsuario;

        if(nombre == null){
            sliceUsuario = usuarioDao.findAndOrderByNombreUsuario(pageRequest);
        }else{
            sliceUsuario = usuarioDao.findByNombreUsuario(nombre, pageRequest);
        }

        return new Block<>(UsuarioConversor.toUsuariosTablaDto(sliceUsuario.getContent()), sliceUsuario.hasNext());

    }

    @Override
    public UsuarioDto recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException {
        Optional<Usuario> u = usuarioDao.findByNombreUsuario(nombreUsuario);

        if(!u.isPresent()){
            throw  new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
        }

        UsuarioDto uDto = UsuarioConversor.toUsuarioDto(u.get());

        return uDto;
    }
}
