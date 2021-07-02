package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.Concurso;
import com.figueiras.photocontest.backend.model.entities.ConcursoDao;
import com.figueiras.photocontest.backend.model.entities.EstadoConcurso;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        Slice<Concurso> sliceConcursos = concursoDao.find(estado==null? null : EstadoConcurso.values()[estado], idCategoria, nombre, page, size);

        return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
    }

    @Override
    public Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if(!concursoOptional.isPresent()){
            throw new InstanceNotFoundException(Concurso.class.getName(), idConcurso);
        }
        return concursoOptional.get();
    }
}
