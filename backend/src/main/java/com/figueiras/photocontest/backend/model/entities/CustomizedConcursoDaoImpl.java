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
    public Slice<Concurso> find(EstadoConcurso estadoConcurso, CategoriaFotografica categoriaFotografica, String nombreConcurso, int page, int size) {
        String[] tokens = UtilidadesParaEntidades.separarPorEspacios(nombreConcurso);
        String queryString = "SELECT c FROM Concurso c";

        if (estadoConcurso != null || categoriaFotografica != null || tokens.length > 0) {
            queryString += " WHERE ";
        }

        if (categoriaFotografica != null) {
            queryString += ":categoriaFotografica MEMBER OF c.categoriasPermitidas";
        }

        if(categoriaFotografica != null && estadoConcurso!= null){
            queryString += " AND ";
        }

        if (estadoConcurso != null) {
            queryString += "c.estadoConcurso = :estadoConcurso";
        }

        if (tokens.length != 0) {

            if (categoriaFotografica != null || estadoConcurso != null) {
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

        if (categoriaFotografica != null) {
            query.setParameter("categoriaFotografica", categoriaFotografica);
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