package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioVotaFotografiaPK implements Serializable {

    private Long idUsuario;
    private Long idFotografia;

    public UsuarioVotaFotografiaPK() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdFotografia() {
        return idFotografia;
    }

    public void setIdFotografia(Long idFotografia) {
        this.idFotografia = idFotografia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioVotaFotografiaPK that = (UsuarioVotaFotografiaPK) o;
        return idUsuario.equals(that.idUsuario) &&
                idFotografia.equals(that.idFotografia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idFotografia);
    }
}