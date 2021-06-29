package com.figueiras.photocontest.backend.model.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UsuarioParticipaConcursoPK implements Serializable {

    private Long idUsuario;
    private Long idConcurso;

    public UsuarioParticipaConcursoPK() {
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(Long idConcurso) {
        this.idConcurso = idConcurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioParticipaConcursoPK that = (UsuarioParticipaConcursoPK) o;
        return idUsuario.equals(that.idUsuario) &&
                idConcurso.equals(that.idConcurso);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, idConcurso);
    }
}