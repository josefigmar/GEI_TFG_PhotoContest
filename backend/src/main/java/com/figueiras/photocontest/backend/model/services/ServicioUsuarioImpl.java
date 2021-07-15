package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Usuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioDao;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuario;
import com.figueiras.photocontest.backend.model.entities.UsuarioSigueUsuarioDao;
import com.figueiras.photocontest.backend.model.exceptions.IncorrectLoginException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioLoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

    @Autowired
    UsuarioDao usuarioDao;

    @Autowired
    UsuarioSigueUsuarioDao usuarioSigueUsuarioDao;

    @Autowired
    ServicioEmail servicioEmail;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Block<Usuario> recuperarUsuarios(String nombre, int page, int size) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Slice<Usuario> sliceUsuario;

        if (nombre == null) {
            sliceUsuario = usuarioDao.findAndOrderByNombreUsuario(pageRequest);
        } else {
            sliceUsuario = usuarioDao.findByNombreUsuario(nombre, pageRequest);
        }

        return new Block<>(sliceUsuario.getContent(), sliceUsuario.hasNext());
    }

    @Override
    public Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException {
        Optional<Usuario> u = usuarioDao.findByNombreUsuario(nombreUsuario);

        if (!u.isPresent()) {
            throw new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
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

    /**
     * Función que permite a un usuario iniciar sesión
     *
     * @param usuarioLoginDto Datos del formulario de inicio de sesión
     * @return Los datos del usuario
     * @throws IncorrectLoginException Si el usuario no existe o existe pero su cuenta ha sido borrada
     *
     */
    @Override
    public Usuario iniciarSesionUsuario(UsuarioLoginDto usuarioLoginDto) throws IncorrectLoginException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(usuarioLoginDto.getNombreUsuario());


        if (!usuarioOptional.isPresent() || (usuarioOptional.isPresent()) && usuarioOptional.get().isCuentaEliminada()){
            throw new IncorrectLoginException();
        }

        if (!passwordEncoder.matches(usuarioLoginDto.getContraseñaUsuario(),
                usuarioOptional.get().getContrasenaUsuario())) {
            throw new IncorrectLoginException();
        }

        return usuarioOptional.get();
    }

    @Override
    public Block<UsuarioSigueUsuario> recuperarSeguidoresDeUsuario(String nombreUsuario, int page, int size) {

        Optional<Usuario> usuario = usuarioDao.findByNombreUsuario(nombreUsuario);

        if (!usuario.isPresent()) {
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

        if (!usuario.isPresent()) {
            // todo
        }
        Slice<UsuarioSigueUsuario> sliceSeguidos =
                usuarioSigueUsuarioDao.recuperarSeguidosDeUsuario(usuario.get().getIdUsuario());

        Block<UsuarioSigueUsuario> usuariosQueSigue = new Block<>(sliceSeguidos.getContent(), sliceSeguidos.hasNext());

        return usuariosQueSigue;
    }

    @Override
    public void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto) {

        Optional<Usuario> usuarioOptional =
                usuarioDao.findByNombreUsuario(usuarioCambioContraseñaDto.getNombreUsuario());

        if (!usuarioOptional.isPresent()) {
            // todo
        }

        Usuario usuario = usuarioOptional.get();

        if (!passwordEncoder.matches(usuarioCambioContraseñaDto.getContraseñaAntigua(), usuario.getContrasenaUsuario())) {
            // todo
        }

        usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioCambioContraseñaDto.getContraseñaNueva()));

        usuarioDao.save(usuario);
    }

    @Override
    public Usuario actualizarDatosUsuario(UsuarioDto datosFormularioActualizacion) {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(datosFormularioActualizacion.getNombreUsuario());

        if (!usuarioOptional.isPresent()) {
            // todo
        }
        Usuario usuario = usuarioOptional.get();

        // Incorporación de datos del formulario
        usuario.setNombrePilaUsuario(datosFormularioActualizacion.getNombrePilaUsuario());
        usuario.setApellidosUsuario(datosFormularioActualizacion.getApellidosUsuario());
        usuario.setBiografiaUsuario(datosFormularioActualizacion.getBiografiaUsuario());
        usuario.setEnlaceTwitterUsuario(datosFormularioActualizacion.getEnlaceTwitterUsuario());
        usuario.setEnlaceFacebookUsuario(datosFormularioActualizacion.getEnlaceFacebookUsuario());
        usuario.setFotoPerfil(datosFormularioActualizacion.getFotoPerfil());
        usuario.setCorreoElectronicoUsuario(datosFormularioActualizacion.getEmail());

        usuarioDao.save(usuario);
        return usuario;
    }

    @Override
    public Usuario usuarioSigueAUsuario(String nombreUsuarioSeguidor, String nombreUsuarioSeguido)
            throws InstanceNotFoundException {

        Optional<Usuario> usuarioSeguidorOptional = usuarioDao.findByNombreUsuario(nombreUsuarioSeguidor);
        Optional<Usuario> usuarioSeguidoOptional = usuarioDao.findByNombreUsuario(nombreUsuarioSeguido);

        if (!usuarioSeguidorOptional.isPresent() || !usuarioSeguidoOptional.isPresent()) {
            throw new InstanceNotFoundException(null, null);
        }

        Usuario usuarioSeguidor = usuarioSeguidorOptional.get();
        Usuario usuarioSeguido = usuarioSeguidoOptional.get();

        UsuarioSigueUsuario usuarioSigueUsuario = new UsuarioSigueUsuario();
        usuarioSigueUsuario.setUsuarioSeguidor(usuarioSeguidor);
        usuarioSigueUsuario.setUsuarioSeguido(usuarioSeguido);
        usuarioSigueUsuario.setFechaSeguida(LocalDateTime.now());

        usuarioSeguidor.getUsuariosQueSigue().add(usuarioSigueUsuario);
        usuarioSeguido.getUsuariosQueLoSiguen().add(usuarioSigueUsuario);

        usuarioSigueUsuarioDao.save(usuarioSigueUsuario);
        usuarioDao.save(usuarioSeguidor);
        usuarioDao.save(usuarioSeguido);

        return usuarioSeguidor;
    }

    @Override
    public Usuario usuarioDejaDeSeguirAUsuario(String nombreUsuarioSeguidor, String nombreUsuarioSeguido)
            throws InstanceNotFoundException {

        Optional<Usuario> usuarioSeguidorOptional = usuarioDao.findByNombreUsuario(nombreUsuarioSeguidor);
        Optional<Usuario> usuarioSeguidoOptional = usuarioDao.findByNombreUsuario(nombreUsuarioSeguido);
        Optional<UsuarioSigueUsuario> usuarioSigueUsuarioOptional =
                usuarioSigueUsuarioDao.recuperarUsuarioSigueUsuario(nombreUsuarioSeguidor, nombreUsuarioSeguido);

        if (!usuarioSeguidorOptional.isPresent() || !usuarioSeguidoOptional.isPresent()
                || !usuarioSigueUsuarioOptional.isPresent()) {
            throw new InstanceNotFoundException(null, null);
        }

        Usuario usuarioSeguidor = usuarioSeguidorOptional.get();
        Usuario usuarioSeguido = usuarioSeguidoOptional.get();
        UsuarioSigueUsuario usuarioSigueUsuario = usuarioSigueUsuarioOptional.get();

        usuarioSeguidor.getUsuariosQueSigue().remove(usuarioSigueUsuario);
        usuarioSeguido.getUsuariosQueLoSiguen().remove(usuarioSigueUsuario);

        usuarioSigueUsuarioDao.delete(usuarioSigueUsuario);
        usuarioDao.save(usuarioSeguidor);
        usuarioDao.save(usuarioSeguido);

        return usuarioSeguidor;
    }

    @Override
    public boolean sigueUsuarioAUsuario(String usuarioSeguidor, String usuarioSeguido) {

        Optional<UsuarioSigueUsuario> usuarioSigueUsuario =
                usuarioSigueUsuarioDao.recuperarUsuarioSigueUsuario(usuarioSeguidor, usuarioSeguido);

        if (!usuarioSigueUsuario.isPresent()) {
            return false;
        }
        return true;
    }

    @Override
    public void enviarNuevaContraseña(String nombreUsuarioDestinatario) throws InstanceNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(nombreUsuarioDestinatario);

        if(!usuarioOptional.isPresent()){
            throw new InstanceNotFoundException(null, null);
        }

        Usuario usuario = usuarioOptional.get();
        String nuevaContraseña = generarContraseña(10);
        String nuevaContraseñaCodificada = passwordEncoder.encode(nuevaContraseña);

        usuario.setContrasenaUsuario(nuevaContraseñaCodificada);
        try{
            servicioEmail.enviarMailGmail(usuario.getCorreoElectronicoUsuario(), "New password", nuevaContraseña);
            usuarioDao.save(usuario);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private String generarContraseña(int longitud){

        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();
    }

    @Override
    public void eliminarUsuario(String nombreUsuario) throws InstanceNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(nombreUsuario);

        if(!usuarioOptional.isPresent()){
            throw new InstanceNotFoundException(null, null);
        }

        Usuario usuario = usuarioOptional.get();

        // Borrado de datos personales en BBDD
        usuario.setNombrePilaUsuario("");
        usuario.setApellidosUsuario("");
        usuario.setBiografiaUsuario("");
        usuario.setEnlaceTwitterUsuario("");
        usuario.setEnlaceFacebookUsuario("");
        // Borrado de seguidos
        Set<UsuarioSigueUsuario> usuariosQueSigue = usuario.getUsuariosQueSigue();
        for(UsuarioSigueUsuario usu : usuariosQueSigue){
            usuarioSigueUsuarioDao.delete(usu);
        }
        // Borrado de seguidores
        Set<UsuarioSigueUsuario> usuariosQueLoSiguen = usuario.getUsuariosQueLoSiguen();
        for(UsuarioSigueUsuario usu : usuariosQueLoSiguen){
            usuarioSigueUsuarioDao.delete(usu);
        }

        usuario.setUsuariosQueSigue(new HashSet<>());
        usuario.setUsuariosQueLoSiguen(new HashSet<>());
        usuario.setFotoPerfil(null);
        usuario.setCorreoElectronicoUsuario(null);
        usuario.setCuentaEliminada(true);

        usuarioDao.save(usuario);
    }
}
