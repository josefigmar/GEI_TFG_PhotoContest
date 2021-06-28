package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class CategoriaFotografica {

    private Long idCategoria;
    @Size(min = 1, max = 50)
    private String nombreCategoria;
    @Size(min = 1, max = 200)
    private String descripcion;
    private Set<Usuario> usuariosALosQueLesGusta;
    private Set<Concurso> concursosEnDondeSeUsa;

    public CategoriaFotografica() {
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getIdCategoria() {
        return idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "UsuarioGustaCategoria",
            joinColumns = {@JoinColumn(name = "idCategoria")},
            inverseJoinColumns = {@JoinColumn(name = "idUsuario")}

    )
    public Set<Usuario> getUsuariosALosQueLesGusta() {
        return usuariosALosQueLesGusta;
    }

    public void setUsuariosALosQueLesGusta(Set<Usuario> usuariosALosQueLesGusta) {
        this.usuariosALosQueLesGusta = usuariosALosQueLesGusta;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ConcursoPermiteCategoria",
            joinColumns = {@JoinColumn(name = "idCategoria")},
            inverseJoinColumns = {@JoinColumn(name = "idConcurso")}

    )
    public Set<Concurso> getConcursosEnDondeSeUsa() {
        return concursosEnDondeSeUsa;
    }

    public void setConcursosEnDondeSeUsa(Set<Concurso> concursosEnDondeSeUsa) {
        this.concursosEnDondeSeUsa = concursosEnDondeSeUsa;
    }
}