package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    private static Lenguaje LENGUAJE_POR_DEFECTO = Lenguaje.es;

    private Long idUsuario;
    private String fotoPerfil;
    @Size(min = 1, max = 50)
    private String nombreUsuario;
    @Size(max = 50)
    private String nombrePilaUsuario;
    @Size(max = 100)
    private String apellidosUsuario;
    @Size(max = 500)
    private String biografiaUsuario;
    @Size(max = 90)
    @Email
    private String correoElectronicoUsuario;
    @Size(min = 0, max = 100)
    private String contrasenaUsuario;
    @Size(max = 200)
    private String enlaceTwitterUsuario;
    @Size(max = 200)
    private String enlaceFacebookUsuario;
    private boolean cuentaEliminada;
    private Lenguaje lenguaje;
    private RolUsuarioSistema rolUsuarioSistema;
    private Set<UsuarioSigueUsuario> usuariosQueSigue;
    private Set<UsuarioSigueUsuario> usuariosQueLoSiguen;
    private Set<CategoriaFotografica> categoriaFotograficasQueLeGustan;
    private Set<Notificacion> notificacionesUsuario;
    private Set<UsuarioParticipaConcurso> concursosEnLosQueParticipa;


    public Usuario() {
        rolUsuarioSistema = RolUsuarioSistema.ESTANDAR;
        usuariosQueSigue = new HashSet<>();
        usuariosQueLoSiguen = new HashSet<>();
        categoriaFotograficasQueLeGustan = new HashSet<>();
        notificacionesUsuario = new HashSet<>();
        concursosEnLosQueParticipa = new HashSet<>();
        cuentaEliminada = false;
        lenguaje = LENGUAJE_POR_DEFECTO;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFotoPerfil() { return fotoPerfil; }

    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

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

    public String getCorreoElectronicoUsuario() {
        return correoElectronicoUsuario;
    }

    public void setCorreoElectronicoUsuario(String correoElectronicoUsuario) {
        this.correoElectronicoUsuario = correoElectronicoUsuario;
    }

    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    public void setContrasenaUsuario(String contrasenaUsuario) {
        this.contrasenaUsuario = contrasenaUsuario;
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

    public boolean isCuentaEliminada() {
        return cuentaEliminada;
    }

    public void setCuentaEliminada(boolean cuentaEliminada) {
        this.cuentaEliminada = cuentaEliminada;
    }

    public Lenguaje getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(Lenguaje lenguaje) {
        this.lenguaje = lenguaje;
    }

    public RolUsuarioSistema getRolUsuarioSistema() {
        return rolUsuarioSistema;
    }

    public void setRolUsuarioSistema(RolUsuarioSistema rolUsuarioSistema) {
        this.rolUsuarioSistema = rolUsuarioSistema;
    }

    @OneToMany(mappedBy="usuarioSeguidor", fetch = FetchType.LAZY)
    public Set<UsuarioSigueUsuario> getUsuariosQueSigue() {
        return usuariosQueSigue;
    }

    public void setUsuariosQueSigue(Set<UsuarioSigueUsuario> usuariosQueSigue) {
        this.usuariosQueSigue = usuariosQueSigue;
    }

    @OneToMany(mappedBy="usuarioSeguido", fetch = FetchType.LAZY)
    public Set<UsuarioSigueUsuario> getUsuariosQueLoSiguen() {
        return usuariosQueLoSiguen;
    }

    public void setUsuariosQueLoSiguen(Set<UsuarioSigueUsuario> usuariosQueLoSiguen) {
        this.usuariosQueLoSiguen = usuariosQueLoSiguen;
    }
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "UsuarioGustaCategoria",
            joinColumns = {@JoinColumn(name = "idUsuario")},
            inverseJoinColumns = {@JoinColumn(name = "idCategoria")}

    )
    public Set<CategoriaFotografica> getCategoriaFotograficasQueLeGustan() {
        return categoriaFotograficasQueLeGustan;
    }

    public void setCategoriaFotograficasQueLeGustan(Set<CategoriaFotografica> categoriaFotograficasQueLeGustan) {
        this.categoriaFotograficasQueLeGustan = categoriaFotograficasQueLeGustan;
    }

    @OneToMany(mappedBy = "usuario")
    public Set<Notificacion> getNotificacionesUsuario() {
        return notificacionesUsuario;
    }

    public void setNotificacionesUsuario(Set<Notificacion> notificacionesUsuario) {
        this.notificacionesUsuario = notificacionesUsuario;
    }

    @OneToMany(mappedBy = "usuario")
    public Set<UsuarioParticipaConcurso> getConcursosEnLosQueParticipa() {
        return concursosEnLosQueParticipa;
    }

    public void setConcursosEnLosQueParticipa(Set<UsuarioParticipaConcurso> concursosEnLosQueParticipa) {
        this.concursosEnLosQueParticipa = concursosEnLosQueParticipa;
    }
}