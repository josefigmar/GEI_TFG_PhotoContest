package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Fotografia {

    private Long idFotografia;
    @Size(max=50)
    private String tituloFotografia;
    @Size(max=200)
    private String descripcionFotografia;
    @Size(max=50)
    private String aperturaDiafragma;
    @Size(max=50)
    private String fabricanteCamara;
    @Size(max=50)
    private String modeloCamara;
    @Size(max=50)
    private String distanciaFocal;
    @Size(max=50)
    private String velocidadObturacion;
    @Size(max=50)
    private String iso;
    @Size(max=50)
    private String resolucion;
    private String datosJpg;
    private String datosRaw;
    private LocalDateTime fechaInicioParticipacion;
    private EstadoModeracion estadoModeracion;
    private CategoriaFotografica categoriaFotografica;
    private Usuario usuario;
    private Concurso concurso;

    public Fotografia() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Long idFotografia) {
        this.idFotografia = idFotografia;
    }

    public String getTituloFotografia() {
        return tituloFotografia;
    }

    public void setTituloFotografia(String tituloFotografia) {
        this.tituloFotografia = tituloFotografia;
    }

    public String getDescripcionFotografia() {
        return descripcionFotografia;
    }

    public void setDescripcionFotografia(String descripcionFotografia) {
        this.descripcionFotografia = descripcionFotografia;
    }

    public String getAperturaDiafragma() {
        return aperturaDiafragma;
    }

    public void setAperturaDiafragma(String apreturaDiafragma) {
        this.aperturaDiafragma = apreturaDiafragma;
    }

    public String getFabricanteCamara() {
        return fabricanteCamara;
    }

    public void setFabricanteCamara(String fabricanteCamara) {
        this.fabricanteCamara = fabricanteCamara;
    }

    public String getModeloCamara() {
        return modeloCamara;
    }

    public void setModeloCamara(String modeloCamara) {
        this.modeloCamara = modeloCamara;
    }

    public String getDistanciaFocal() {
        return distanciaFocal;
    }

    public void setDistanciaFocal(String distanciaFocal) {
        this.distanciaFocal = distanciaFocal;
    }

    public String getVelocidadObturacion() {
        return velocidadObturacion;
    }

    public void setVelocidadObturacion(String velocidadObturacion) {
        this.velocidadObturacion = velocidadObturacion;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public String getResolucion() {
        return resolucion;
    }

    public void setResolucion(String resolucion) {
        this.resolucion = resolucion;
    }

    public String getDatosJpg() {
        return datosJpg;
    }

    public void setDatosJpg(String datosJpg) {
        this.datosJpg = datosJpg;
    }

    public String getDatosRaw() {
        return datosRaw;
    }

    public void setDatosRaw(String datosRaw) {
        this.datosRaw = datosRaw;
    }

    public LocalDateTime getFechaInicioParticipacion() {
        return fechaInicioParticipacion;
    }

    public void setFechaInicioParticipacion(LocalDateTime fechaInicioParticipacion) {
        this.fechaInicioParticipacion = fechaInicioParticipacion;
    }

    public EstadoModeracion getEstadoModeracion() {
        return estadoModeracion;
    }

    public void setEstadoModeracion(EstadoModeracion estadoModeracion) {
        this.estadoModeracion = estadoModeracion;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCategoria")
    public CategoriaFotografica getCategoriaFotografica() {
        return categoriaFotografica;
    }

    public void setCategoriaFotografica(CategoriaFotografica categoriaFotografica) {
        this.categoriaFotografica = categoriaFotografica;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idConcurso")
    public Concurso getConcurso() {
        return concurso;
    }

    public void setConcurso(Concurso concurso) {
        this.concurso = concurso;
    }
}