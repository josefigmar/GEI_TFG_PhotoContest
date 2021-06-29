package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Usuario {

    private Long idUsuario;
    @Size(min = 1, max = 50)
    private String nombreUsuario;
    @Size(min = 1, max = 50)
    private String nombrePilaUsuario;
    @Size(min = 1, max = 100)
    private String apellidosUsuario;
    @Size(max = 500)
    private String biografiaUsuario;
    @Size(max = 90)
    @Email
    private String correoElectronicoUsuario;
    @Size(min = 4, max = 50)
    private String contrasenaUsuario;
    @Size(max = 200)
    private String enlaceTwitterUsuario;
    @Size(max = 200)
    private String enlaceFacebookUsuario;
    private Set<UsuarioSigueUsuario> usuariosQueSigue;
    private Set<UsuarioSigueUsuario> usuariosQueLoSiguen;
    private Set<CategoriaFotografica> categoriaFotograficasQueLeGustan;
    private Set<Notificacion> notificacionesUsuario;
    private Set<UsuarioParticipaConcurso> concursosEnLosQueParticipa;

    public Usuario() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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