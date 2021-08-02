package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

@Entity
public class Concurso {

    private Long idConcurso;
    @Size(max = 50)
    private String nombreConcurso;
    @Size(max = 500)
    private String descripcionConcurso;
    private EstadoConcurso estadoConcurso;
    private TipoAcceso tipoAccesoConcurso;
    private TipoVotante tipoVotanteConcurso;
    private TipoVoto tipoVotoConcurso;
    private String fotoConcurso;
    private Boolean categoriaUnica;
    private Integer maxFotos;
    private Integer maxFotosUsuario;
    private Integer numGanadores;
    private Integer maxVotosUsuario;
    private Boolean tituloReq;
    private Boolean descReq;
    private Boolean datosExifReq;
    private Boolean locReq;
    private Boolean ocultarFotos;
    private Boolean moderacion;
    private FormatoFotografia formato;
    private Boolean OcultarVotos;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaInicioConcurso;
    private LocalDateTime fechaInicioVotacion;
    private LocalDateTime fechaFinConcurso;
    @Size(max = 500)
    private String descVotacion;
    private String basesConcurso;
    private Set<CategoriaFotografica> categoriasPermitidas;
    private Set<UsuarioParticipaConcurso> usuariosQueParticipan;

    public Concurso() {

        this.estadoConcurso = EstadoConcurso.EN_PREPARACION;
        this.fechaCreacion = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getDescripcionConcurso() {
        return descripcionConcurso;
    }

    public void setDescripcionConcurso(String descripcionConcurso) {
        this.descripcionConcurso = descripcionConcurso;
    }

    public EstadoConcurso getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(EstadoConcurso estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }

    public TipoAcceso getTipoAccesoConcurso() {
        return tipoAccesoConcurso;
    }

    public void setTipoAccesoConcurso(TipoAcceso tipoAccesoConcurso) {
        this.tipoAccesoConcurso = tipoAccesoConcurso;
    }

    public TipoVotante getTipoVotanteConcurso() {
        return tipoVotanteConcurso;
    }

    public void setTipoVotanteConcurso(TipoVotante tipoVotanteConcurso) {
        this.tipoVotanteConcurso = tipoVotanteConcurso;
    }

    public TipoVoto getTipoVotoConcurso() {
        return tipoVotoConcurso;
    }

    public void setTipoVotoConcurso(TipoVoto tipoVotoConcurso) {
        this.tipoVotoConcurso = tipoVotoConcurso;
    }

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    public String getFotoConcurso() {
        return fotoConcurso;
    }

    public void setFotoConcurso(String fotoConcurso) {
        this.fotoConcurso = fotoConcurso;
    }

    public Boolean getCategoriaUnica() {
        return categoriaUnica;
    }

    public void setCategoriaUnica(Boolean categoriaUnica) {
        this.categoriaUnica = categoriaUnica;
    }

    public Integer getMaxFotos() {
        return maxFotos;
    }

    public void setMaxFotos(Integer maxFotos) {
        this.maxFotos = maxFotos;
    }

    public Integer getMaxFotosUsuario() {
        return maxFotosUsuario;
    }

    public void setMaxFotosUsuario(Integer maxFotosUsuario) {
        this.maxFotosUsuario = maxFotosUsuario;
    }

    public Integer getNumGanadores() {
        return numGanadores;
    }

    public void setNumGanadores(Integer numGanadores) {
        this.numGanadores = numGanadores;
    }

    public Integer getMaxVotosUsuario() {
        return maxVotosUsuario;
    }

    public void setMaxVotosUsuario(Integer maxVotosUsuario) {
        this.maxVotosUsuario = maxVotosUsuario;
    }

    public Boolean getTituloReq() {
        return tituloReq;
    }

    public void setTituloReq(Boolean tituloReq) {
        this.tituloReq = tituloReq;
    }

    public Boolean getDescReq() {
        return descReq;
    }

    public void setDescReq(Boolean descReq) {
        this.descReq = descReq;
    }

    public Boolean getDatosExifReq() {
        return datosExifReq;
    }

    public void setDatosExifReq(Boolean datosExifReq) {
        this.datosExifReq = datosExifReq;
    }

    public Boolean getLocReq() {
        return locReq;
    }

    public void setLocReq(Boolean locReq) {
        this.locReq = locReq;
    }

    public Boolean getOcultarFotos() {
        return ocultarFotos;
    }

    public void setOcultarFotos(Boolean ocultarFotos) {
        this.ocultarFotos = ocultarFotos;
    }

    public Boolean getModeracion() {
        return moderacion;
    }

    public void setModeracion(Boolean moderacion) {
        this.moderacion = moderacion;
    }

    public FormatoFotografia getFormato() {
        return formato;
    }

    public void setFormato(FormatoFotografia formato) {
        this.formato = formato;
    }

    public Boolean getOcultarVotos() {
        return OcultarVotos;
    }

    public void setOcultarVotos(Boolean ocultarVotos) {
        OcultarVotos = ocultarVotos;
    }

    public LocalDateTime getFechaInicioConcurso() {
        return fechaInicioConcurso;
    }

    public void setFechaInicioConcurso(LocalDateTime fechaInicioConcurso) {
        this.fechaInicioConcurso = fechaInicioConcurso;
    }

    public LocalDateTime getFechaInicioVotacion() {
        return fechaInicioVotacion;
    }

    public void setFechaInicioVotacion(LocalDateTime fechaInicioVotacion) {
        this.fechaInicioVotacion = fechaInicioVotacion;
    }

    public LocalDateTime getFechaFinConcurso() {
        return fechaFinConcurso;
    }

    public void setFechaFinConcurso(LocalDateTime fechaFinConcurso) {
        this.fechaFinConcurso = fechaFinConcurso;
    }

    public String getDescVotacion() {
        return descVotacion;
    }

    public void setDescVotacion(String descVotacion) {
        this.descVotacion = descVotacion;
    }

    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    public String getBasesConcurso() {
        return basesConcurso;
    }

    public void setBasesConcurso(String basesConcurso) {
        this.basesConcurso = basesConcurso;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ConcursoPermiteCategoria",
            joinColumns = {@JoinColumn(name = "idConcurso")},
            inverseJoinColumns = {@JoinColumn(name = "idCategoria")}

    )
    public Set<CategoriaFotografica> getCategoriasPermitidas() {
        return categoriasPermitidas;
    }

    public void setCategoriasPermitidas(Set<CategoriaFotografica> categoriasPermitidas) {
        this.categoriasPermitidas = categoriasPermitidas;
    }

    @OneToMany(mappedBy = "concurso")
    public Set<UsuarioParticipaConcurso> getUsuariosQueParticipan() {
        return usuariosQueParticipan;
    }

    public void setUsuariosQueParticipan(Set<UsuarioParticipaConcurso> usuariosQueParticipan) {
        this.usuariosQueParticipan = usuariosQueParticipan;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}