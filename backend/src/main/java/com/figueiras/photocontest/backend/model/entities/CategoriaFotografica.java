package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class
CategoriaFotografica {

    private Long idCategoria;
    @Size(min = 1, max = 50)
    private String nombreCategoria;
    @Size(min = 1, max = 200)
    private String descripcionCategoria;
    private Set<Concurso> concursosEnDondeSeUsa;

    public CategoriaFotografica() {
        this.setConcursosEnDondeSeUsa(new HashSet<>());
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

    public String getDescripcionCategoria() {
        return descripcionCategoria;
    }

    public void setDescripcionCategoria(String descripcion) {
        this.descripcionCategoria = descripcion;
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