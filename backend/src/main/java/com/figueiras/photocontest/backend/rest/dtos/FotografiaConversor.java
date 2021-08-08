package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class FotografiaConversor {

    private static CategoriaFotograficaDao categoriaFotograficaDao;

    @Autowired
    public void setCategoriaFotograficaDao(CategoriaFotograficaDao categoriaFotograficaDao) {
        FotografiaConversor.categoriaFotograficaDao = categoriaFotograficaDao;
    }


    public static Fotografia toFotografia(FotografiaDto fotografiaDto, Concurso concurso, Usuario usuario) throws InstanceNotFoundException {

        Fotografia fotografia = new Fotografia();
        fotografia.setTituloFotografia(fotografiaDto.getTituloFotografia());
        fotografia.setDescripcionFotografia(fotografiaDto.getDescripcionFotografia());
        fotografia.setAperturaDiafragma(fotografiaDto.getAperturaDiafragma());
        fotografia.setFabricanteCamara(fotografiaDto.getFabricanteCamara());
        fotografia.setModeloCamara(fotografiaDto.getModeloCamara());
        fotografia.setDistanciaFocal(fotografiaDto.getDistanciaFocal());
        fotografia.setVelocidadObturacion(fotografiaDto.getVelocidadObturacion());
        fotografia.setIso(fotografiaDto.getIso());
        fotografia.setResolucion(fotografiaDto.getResolucion());
        fotografia.setDatosJpg(fotografiaDto.getDatosJpg());
        fotografia.setDatosRaw(fotografiaDto.getDatosRaw());
        // Si necesita moderación -> A esperar, si no, vía libre.
        if(concurso.getModeracion()){
            fotografia.setEstadoModeracion(EstadoModeracion.EN_ESPERA);
        } else {
            fotografia.setFechaInicioParticipacion(LocalDateTime.now());
            fotografia.setEstadoModeracion(EstadoModeracion.APROBADA);
        }

        Optional<CategoriaFotografica> categoriaFotograficaOptional =
                categoriaFotograficaDao.findById(fotografiaDto.getIdCategoria());
        if(categoriaFotograficaOptional.isEmpty()){
            throw new InstanceNotFoundException(CategoriaFotografica.class.toString(), fotografiaDto.getIdCategoria());
        }
        fotografia.setCategoriaFotografica(categoriaFotograficaOptional.get());
        fotografia.setUsuario(usuario);
        fotografia.setConcurso(concurso);

        return fotografia;
    }
}
