package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.ConcursoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class ServicioConcursoImpl implements ServicioConcurso{

    @Autowired
    private ConcursoDao concursoDao;

    @Override
    public Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                                      int page, int size) {
        if(nombre == null && estado == null && idCategoria == null) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Slice<Concurso> sliceConcursos = concursoDao.buscarConcursosOrdenarPorFechaCreacion(pageRequest);

            return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
        }
        Slice<Concurso> sliceConcursos = concursoDao.find(estado, idCategoria, nombre, page, size);

        return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
    }
}
