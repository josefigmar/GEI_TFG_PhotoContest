package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static List<FotografiaDto> toFotografiasDto(List<Fotografia> fotografias){
        List<FotografiaDto> fotografiaDtoList =
                fotografias.stream().map(f -> toFotografiaDto(f)).collect(Collectors.toList());

        return fotografiaDtoList;
    }

    public static FotografiaDto toFotografiaDto(Fotografia datosFotografia){

        FotografiaDto fotografiaDto = new FotografiaDto();

        fotografiaDto.setIdFotografia(datosFotografia.getIdFotografia());
        fotografiaDto.setTituloFotografia(datosFotografia.getTituloFotografia());
        fotografiaDto.setDescripcionFotografia(datosFotografia.getDescripcionFotografia());
        fotografiaDto.setAperturaDiafragma(datosFotografia.getAperturaDiafragma());
        fotografiaDto.setFabricanteCamara(datosFotografia.getFabricanteCamara());
        fotografiaDto.setModeloCamara(datosFotografia.getModeloCamara());
        fotografiaDto.setDistanciaFocal(datosFotografia.getDistanciaFocal());
        fotografiaDto.setVelocidadObturacion(datosFotografia.getVelocidadObturacion());
        fotografiaDto.setIso(datosFotografia.getIso());
        fotografiaDto.setResolucion(datosFotografia.getResolucion());
        fotografiaDto.setDatosJpg(datosFotografia.getDatosJpg());
        fotografiaDto.setDatosRaw(datosFotografia.getDatosRaw());
        fotografiaDto.setNombreCategoria(datosFotografia.getCategoriaFotografica().getNombreCategoria());
        fotografiaDto.setNombreUsuario(datosFotografia.getUsuario().getNombreUsuario());
        fotografiaDto.setIdConcurso(datosFotografia.getConcurso().getIdConcurso());

        return fotografiaDto;
    }
}
