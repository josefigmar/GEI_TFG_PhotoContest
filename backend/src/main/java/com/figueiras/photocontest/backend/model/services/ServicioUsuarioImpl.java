package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.rest.common.JwtGenerator;
import com.figueiras.photocontest.backend.rest.common.JwtInfo;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioCambioContraseñaDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioDto;
import com.figueiras.photocontest.backend.rest.dtos.UsuarioLoginDto;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ServicioUsuarioImpl implements ServicioUsuario {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private UsuarioSigueUsuarioDao usuarioSigueUsuarioDao;

    @Autowired
    private ServicioEmail servicioEmail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ServicioNotificacion servicioNotificacion;

    @Autowired
    MessageSource messageSource;

    @Autowired
    JwtGenerator jwtGenerator;

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
    public List<String> recuperarNombresUsuarios() {

        return usuarioDao.findAllUserNames();
    }

    @Override
    public Usuario recuperarUsuario(String nombreUsuario) throws InstanceNotFoundException {
        Optional<Usuario> u = usuarioDao.findByNombreUsuario(nombreUsuario);

        if (!u.isPresent()) {
            throw new InstanceNotFoundException(Usuario.class.getName(), nombreUsuario);
        }

        return u.get();
    }

    /**
     * Crea un nuevo usuario con los datos del formulario de registro.
     *
     * @param usuarioDto Datos del formulario de registro.
     * @throws CampoDuplicadoException Si el nombre de usuario o el correo electrónico ya existen en la aplicaciçon.
     */
    @Override
    public void registrarUsuario(UsuarioDto usuarioDto) throws CampoDuplicadoException,
            CamposIntroducidosNoValidosException, InstanceNotFoundException {

        // Petición no permitida en la aplicación.
        if(!esValidoFormRegistro(usuarioDto)){
            throw new CamposIntroducidosNoValidosException();
        }

        Optional<Usuario> usuarioOptionalNombreUsuario = usuarioDao.findByNombreUsuario(usuarioDto.getNombreUsuario());
        if(usuarioOptionalNombreUsuario.isPresent()){
            throw new CampoDuplicadoException("entidades.usuario.nombreusuario", usuarioDto.getNombreUsuario());
        }

        Optional<Usuario> usuarioOptionalCorreo = usuarioDao.findByCorreoElectronicoUsuario(usuarioDto.getEmail());
        if(usuarioOptionalCorreo.isPresent()){
            throw new CampoDuplicadoException("entidades.usuario.correoelectronicousuario", usuarioDto.getEmail());
        }

        Usuario usuario = new Usuario();

        usuario.setNombreUsuario(usuarioDto.getNombreUsuario());
        usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioDto.getContraseña()));
        usuario.setNombrePilaUsuario(usuarioDto.getNombrePilaUsuario());
        usuario.setApellidosUsuario(usuarioDto.getApellidosUsuario());
        usuario.setCorreoElectronicoUsuario(usuarioDto.getEmail());
        usuario.setLenguaje(Lenguaje.values()[usuarioDto.getLenguaje()]);

        usuarioDao.save(usuario);

        // Envío de mensaje de bienvenida
        servicioNotificacion.crearNotificacion(messageSource.getMessage("project.notifications.welcomeTitle",
                new Object[] {usuarioDto.getNombrePilaUsuario()}, new Locale(usuario.getLenguaje().toString())),
                messageSource.getMessage("project.notifications.welcomeMessage",
                null, new Locale(usuario.getLenguaje().toString())),
                usuario.getNombreUsuario());

        // Envio de email de bienvenida
        servicioEmail.enviarMailGmail(usuario.getCorreoElectronicoUsuario(),
                messageSource.getMessage("project.notifications.welcomeTitle",
                new Object[] {usuarioDto.getNombrePilaUsuario()}, new Locale(usuario.getLenguaje().toString())),
                messageSource.getMessage("project.notifications.welcomeMessage",
                        null, new Locale(usuario.getLenguaje().toString())));
    }

    private boolean esValidoFormRegistro(UsuarioDto usuarioDto){
        if((usuarioDto.getNombreUsuario() == null || usuarioDto.getNombreUsuario() == "")
        || (usuarioDto.getContraseña() == null || usuarioDto.getContraseña() == "")
        || (usuarioDto.getNombrePilaUsuario() == null || usuarioDto.getNombrePilaUsuario() == "")
        || (usuarioDto.getApellidosUsuario() == null || usuarioDto.getApellidosUsuario() == "")
        || (usuarioDto.getEmail() == null || usuarioDto.getEmail() == "")){
            return false;
        }
        return true;
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
    public void cambiarContraseñaUsuario(UsuarioCambioContraseñaDto usuarioCambioContraseñaDto, boolean isFromReset)
            throws IncorrectPasswordException {

        Optional<Usuario> usuarioOptional =
                usuarioDao.findByNombreUsuario(usuarioCambioContraseñaDto.getNombreUsuario());



        // Esto no debería ser posible
        if (!usuarioOptional.isPresent()) {
            return;
        }

        Usuario usuario = usuarioOptional.get();

        // Este método se reutiliza para el cambio normal de contraseña como para restablecerla luego de olvidarla.
        if(!isFromReset){
            if (!passwordEncoder.matches(usuarioCambioContraseñaDto.getContraseñaAntigua(),
                    usuario.getContrasenaUsuario())) {
                throw new IncorrectPasswordException();
            }
        }

        usuario.setContrasenaUsuario(passwordEncoder.encode(usuarioCambioContraseñaDto.getContraseñaNueva()));

        usuarioDao.save(usuario);

        try{
            servicioEmail.enviarMailGmail(usuario.getCorreoElectronicoUsuario(),
                    messageSource.getMessage("project.emails.subject.NewPasswordSubject",
                            null, new Locale(usuario.getLenguaje().toString())),
                    messageSource.getMessage("project.emails.subject.NewPasswordMessage",
                            null, new Locale(usuario.getLenguaje().toString())));
        }catch(Exception e){
            e.printStackTrace();
        }
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
        usuario.setEnlaceInstagramUsuario(datosFormularioActualizacion.getEnlaceInstagramUsuario());
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

        String asunto = messageSource.getMessage(
                "project.Follow.title",
                null,
                new Locale(usuarioSeguido.getLenguaje().toString()));

        String textoCuerpo = messageSource.getMessage(
                "project.Follow.msg",
                new Object[]{nombreUsuarioSeguidor},
                new Locale(usuarioSeguido.getLenguaje().toString()));

        servicioEmail.enviarMailGmail(usuarioSeguido.getCorreoElectronicoUsuario(), asunto, textoCuerpo);
        servicioNotificacion.crearNotificacion(asunto, textoCuerpo, usuarioSeguido.getNombreUsuario());

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
    public void enviarEnlaceRecuperacionContrasena(String nombreUsuarioDestinatario) throws InstanceNotFoundException {

        Optional<Usuario> usuarioOptional = usuarioDao.findByNombreUsuario(nombreUsuarioDestinatario);

        if(!usuarioOptional.isPresent()){
            throw new InstanceNotFoundException(null, null);
        }

        Usuario usuario = usuarioOptional.get();
        Long idUsuario = usuario.getIdUsuario();

        JwtInfo jwtInfoUsuario = new JwtInfo(idUsuario);

        String jwtRecuperarContraseña = jwtGenerator.generateForPassword(jwtInfoUsuario);
        String path = "http://localhost:3000/users/" + usuario.getNombreUsuario() + "/reset-password/" + jwtRecuperarContraseña;

        try{
            servicioEmail.enviarMailGmail(usuario.getCorreoElectronicoUsuario(), messageSource.getMessage("project.emails.subject.LinkToNewPasswordSubject", null, new Locale(usuario.getLenguaje().toString())), path);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public boolean comprobarEnlaceRecuperacionContrasena(String jwt) throws InstanceNotFoundException {

        // Si el token no se decodifica bien y salta excepción o si el usuario no existe, se devuelve falso
        // indicando que algo ha fallado.
        try {
            JwtInfo jwtInfo = jwtGenerator.getInfoForPassword(jwt);
            Optional<Usuario> usuarioOptional = usuarioDao.findById(jwtInfo.getIdUsuario());
            if(!usuarioOptional.isPresent()){
                return false;
            }
        } catch(Exception e){
            return false;
        }
        return true;
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

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }
}
