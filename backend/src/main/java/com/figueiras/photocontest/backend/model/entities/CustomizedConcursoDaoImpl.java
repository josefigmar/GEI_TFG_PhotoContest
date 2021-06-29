package com.figueiras.photocontest.backend.model.entities;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class CustomizedConcursoDaoImpl implements CustomizedConcursoDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Slice<Concurso> find(Integer estadoConcurso, Long idCategoria, String nombreConcurso, int page, int size) {
        String[] tokens = UtilidadesParaEntidades.separarPorEspacios(nombreConcurso);
        String queryString = "SELECT c FROM Concurso c";

        if (estadoConcurso != null || idCategoria != null || tokens.length > 0) {
            queryString += " WHERE ";
        }

        if (estadoConcurso != null) {
            queryString += "c.estadoConcurso = :estadoConcurso";
        }

        if (idCategoria != null) {
            queryString += " AND c.idCategoria = :idCategoria";
        }

        if (tokens.length != 0) {

            if (idCategoria != null || estadoConcurso != null) {
                queryString += " AND ";
            }

            for (int i = 0; i<tokens.length-1; i++) {
                queryString += "LOWER(c.nombreConcurso) LIKE LOWER(:token" + i + ") AND ";
            }

            queryString += "LOWER(c.nombreConcurso) LIKE LOWER(:token" + (tokens.length-1) + ")";

        }

        queryString += " ORDER BY c.nombreConcurso";

        Query query = entityManager.createQuery(queryString).setFirstResult(page*size).setMaxResults(size+1);

        if (estadoConcurso != null) {
            query.setParameter("estadoConcurso", estadoConcurso);
        }

        if (idCategoria != null) {
            query.setParameter("idCategoria", idCategoria);
        }

        if (tokens.length != 0) {
            for (int i = 0; i<tokens.length; i++) {
                query.setParameter("token" + i, '%' + tokens[i] + '%');
            }

        }

        List<Concurso> concursos = query.getResultList();
        boolean hasNext = concursos.size() == (size+1);

        if (hasNext) {
            concursos.remove(concursos.size()-1);
        }
        return new SliceImpl<>(concursos, PageRequest.of(page, size), hasNext);
    }

}