package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class ConcursoConversor {

    private static CategoriaFotograficaDao categoriaFotograficaDao;
    private static ServicioUsuario servicioUsuario;

    public static List<ConcursoTablaDto> toConcursosTablaDto(List<Concurso> concursos) {
        List<ConcursoTablaDto> concursoTablaDtos =
                concursos.stream().map(i -> toConcursoTablaDto(i)).collect(Collectors.toList());

        return concursoTablaDtos;
    }

    public static ConcursoTablaDto toConcursoTablaDto(Concurso concurso) {
        return new ConcursoTablaDto(concurso.getIdConcurso(), concurso.getFotoConcurso(), concurso.getNombreConcurso(),
                concurso.getEstadoConcurso().toString(), Utilidades.toMillis(concurso.getFechaInicioConcurso()),
                Utilidades.toMillis(concurso.getFechaFinConcurso()));
    }

    public static Concurso toConcurso(ConcursoDto datosConcurso, String nombreUsuarioCreador,
                                      LocalDateTime fechaCreacion)
            throws InstanceNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        Concurso nuevoConcurso = new Concurso();

        // Si se está editando un concurso, se mantiene la fecha de creación que tenía para
        // que aparezca en la misma posicion de la lista de concursos
        if (fechaCreacion != null) {
            nuevoConcurso.setFechaCreacion(fechaCreacion);
        }

        nuevoConcurso.setNombreConcurso(datosConcurso.getNombreConcurso());
        nuevoConcurso.setDescripcionConcurso(datosConcurso.getDescripcionConcurso());
        nuevoConcurso.setFotoConcurso(datosConcurso.getFotoConcurso());
        nuevoConcurso.setBasesConcurso(datosConcurso.getBasesConcurso());
        nuevoConcurso.setCategoriaUnica(datosConcurso.isCategoriaUnica());

        Set<CategoriaFotografica> categoriaFotograficaSet = new HashSet<>();

        // Se comprueba si viene una categoría única o varias, pues se procesan de forma
        // diferente.
        if (datosConcurso.isCategoriaUnica()) {

            Optional<CategoriaFotografica> categoriaOpt =
                    categoriaFotograficaDao.findById(datosConcurso.getIdCategoria());

            if (!categoriaOpt.isPresent()) {
                throw new InstanceNotFoundException(CategoriaFotografica.class.toString(),
                        datosConcurso.getIdCategoria());
            }

            CategoriaFotografica categoria = categoriaOpt.get();
            categoriaFotograficaSet.add(categoria);

        } else {
            // Seteo de categorías, válido para cuando son varias categorías
            for (String categoriaString : datosConcurso.getListaCategorias()) {

                try {
                    Optional<CategoriaFotografica> categoriaOpt =
                            categoriaFotograficaDao.findByNombreCategoria(categoriaString);

                    if (!categoriaOpt.isPresent()) {
                        throw new InstanceNotFoundException(CategoriaFotografica.class.toString(), categoriaString);
                    }
                    CategoriaFotografica categoria = categoriaOpt.get();

                    categoriaFotograficaSet.add(categoria);

                } catch (InstanceNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        nuevoConcurso.setCategoriasPermitidas(categoriaFotograficaSet);

        Set<UsuarioParticipaConcurso> usuarioParticipaConcursoSet = new HashSet<>();
        // Seteo de miembros de la organización, válido para una o varias

        for (String organizador : datosConcurso.getMiembrosDeLaOrganizacion()) {
            try {
                Usuario usuarioOrganizador = servicioUsuario.recuperarUsuario(organizador);

                UsuarioParticipaConcurso usuarioOrganizadorParticipa = new UsuarioParticipaConcurso();
                usuarioOrganizadorParticipa.setUsuario(usuarioOrganizador);
                usuarioOrganizadorParticipa.setConcurso(nuevoConcurso);
                usuarioOrganizadorParticipa.setRolUsuarioConcurso(RolUsuarioConcurso.ORGANIZADOR);
                usuarioOrganizadorParticipa.setFechaInicioParticipacion(LocalDateTime.now());

                usuarioParticipaConcursoSet.add(usuarioOrganizadorParticipa);

            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Seteo de participantes, válido para una o varias
        for (String participante : datosConcurso.getParticipantes()) {

            try {
                Usuario usuarioParticipante = servicioUsuario.recuperarUsuario(participante);

                UsuarioParticipaConcurso usuarioParticipanteConcurso = new UsuarioParticipaConcurso();
                usuarioParticipanteConcurso.setUsuario(usuarioParticipante);
                usuarioParticipanteConcurso.setConcurso(nuevoConcurso);
                usuarioParticipanteConcurso.setRolUsuarioConcurso(RolUsuarioConcurso.INSCRITO);
                usuarioParticipanteConcurso.setFechaInicioParticipacion(LocalDateTime.now());

                usuarioParticipaConcursoSet.add(usuarioParticipanteConcurso);

            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Seteo de jurado de la organización, válido para una o varias
        for (String juez : datosConcurso.getMiembrosDelJurado()) {

            try {
                Usuario usuario = servicioUsuario.recuperarUsuario(juez);

                UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();
                usuarioParticipaConcurso.setUsuario(usuario);
                usuarioParticipaConcurso.setConcurso(nuevoConcurso);
                usuarioParticipaConcurso.setRolUsuarioConcurso(RolUsuarioConcurso.JURADO);
                usuarioParticipaConcurso.setFechaInicioParticipacion(LocalDateTime.now());

                usuarioParticipaConcursoSet.add(usuarioParticipaConcurso);

            } catch (InstanceNotFoundException e) {
                e.printStackTrace();
            }
        }

        // Seteo de todos los usuarios
        nuevoConcurso.setUsuariosQueParticipan(usuarioParticipaConcursoSet);

        nuevoConcurso.setFechaInicioConcurso(LocalDateTime.parse(datosConcurso.getFechaInicio(), formatter));
        nuevoConcurso.setFechaInicioVotacion(LocalDateTime.parse(datosConcurso.getFechaInicioVotacion(), formatter));
        nuevoConcurso.setTipoAccesoConcurso(
                datosConcurso.isParticipanteAbierto() ? TipoAcceso.PUBLICO : TipoAcceso.PRIVADO);
        nuevoConcurso.setMaxFotos(datosConcurso.getNumeroMaximoFotografias());
        nuevoConcurso.setMaxFotosUsuario(datosConcurso.getNumeroMaximoFotografiasParticipante());
        nuevoConcurso.setFormato(FormatoFotografia.valueOf(datosConcurso.getFormatoRequerido()));
        nuevoConcurso.setTituloReq(datosConcurso.isTituloRequerido());
        nuevoConcurso.setDescReq(datosConcurso.isDescripcionRequerida());
        nuevoConcurso.setDatosExifReq(datosConcurso.isDatosExifRequeridos());
        nuevoConcurso.setOcultarFotos(datosConcurso.isOcutarFotosHastaVotacion());
        nuevoConcurso.setOcultarVotos(datosConcurso.isOcultarResultadosHastaFinal());
        nuevoConcurso.setModeracion(datosConcurso.isActivarModeracion());
        nuevoConcurso.setTipoVotanteConcurso(TipoVotante.valueOf(datosConcurso.getTipoVotante()));
        nuevoConcurso.setTipoVotoConcurso(TipoVoto.valueOf(datosConcurso.getMetodoVoto()));
        nuevoConcurso.setDescVotacion(datosConcurso.getDescripcionVotacionJurado());
        nuevoConcurso.setFechaFinConcurso(LocalDateTime.parse(datosConcurso.getFechaLimiteVotacion(), formatter));
        nuevoConcurso.setNumGanadores(datosConcurso.getNumeroMaximoDeFotografiasGanadoras());
        nuevoConcurso.setMaxVotosUsuario(datosConcurso.getNumeroMaximoVotosPorUsuario());

        return nuevoConcurso;
    }

    public static ConcursoDto toConcursoDto(Concurso concurso) {
        ConcursoDto concursoDto = new ConcursoDto();

        concursoDto.setIdConcurso(concurso.getIdConcurso());
        concursoDto.setNombreConcurso(concurso.getNombreConcurso());
        concursoDto.setDescripcionConcurso(concurso.getDescripcionConcurso());
        concursoDto.setFotoConcurso(concurso.getFotoConcurso());
        concursoDto.setBasesConcurso(concurso.getBasesConcurso());
        concursoDto.setEstadoConcurso(concurso.getEstadoConcurso().toString());
        concursoDto.setCategoriaUnica(concurso.getCategoriaUnica());
        // Seteo de la lista de categorías
        if (!concursoDto.isCategoriaUnica()) {
            List<String> listaCategorias = new ArrayList<>();
            for (CategoriaFotografica c : concurso.getCategoriasPermitidas()) {
                listaCategorias.add(c.getNombreCategoria());
            }
            concursoDto.setListaCategorias(listaCategorias);
        } else {
            for (CategoriaFotografica c : concurso.getCategoriasPermitidas())
                concursoDto.setIdCategoria(c.getIdCategoria());
        }
        // Fechas
        concursoDto.setFechaInicio(concurso.getFechaInicioConcurso().toString());
        concursoDto.setFechaInicioVotacion(concurso.getFechaInicioVotacion().toString());
        concursoDto.setFechaLimiteVotacion(concurso.getFechaFinConcurso().toString());
        concursoDto.setTipoAcceso(concurso.getTipoAccesoConcurso().toString());
        concursoDto.setTipoVotante(concurso.getTipoVotanteConcurso().toString());
        concursoDto.setFormatoRequerido(concurso.getFormato().toString());
        concursoDto.setTituloRequerido(concurso.getTituloReq());
        concursoDto.setDescripcionRequerida(concurso.getDescReq());
        concursoDto.setDatosExifRequeridos(concurso.getDatosExifReq());
        concursoDto.setOcutarFotosHastaVotacion(concurso.getOcultarFotos());
        concursoDto.setOcultarResultadosHastaFinal(concurso.getOcultarVotos());
        concursoDto.setActivarModeracion(concurso.getModeracion());
        concursoDto.setMetodoVoto(concurso.getTipoVotoConcurso().toString());
        concursoDto.setDescripcionVotacionJurado(concurso.getDescVotacion());
        concursoDto.setParticipanteAbierto(concurso.getTipoAccesoConcurso().equals(TipoAcceso.PUBLICO));
        concursoDto.setNumeroMaximoFotografias(concurso.getMaxFotos());
        concursoDto.setNumeroMaximoFotografiasParticipante(concurso.getMaxFotosUsuario());
        concursoDto.setFormatoRequerido(concurso.getFormato().toString());
        concursoDto.setTipoVotante(concurso.getTipoVotanteConcurso().toString());
        concursoDto.setMetodoVoto(concurso.getTipoVotoConcurso().toString());
        concursoDto.setNumeroMaximoVotosPorUsuario(concurso.getMaxVotosUsuario());
        concursoDto.setNumeroMaximoDeFotografiasGanadoras(concurso.getNumGanadores());
        // Seteo de la lista de organizadores
        List<String> organizadores = new ArrayList<>();
        for (UsuarioParticipaConcurso upc : concurso.getUsuariosQueParticipan()) {
            if (upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.ORGANIZADOR)) {
                organizadores.add(upc.getUsuario().getNombreUsuario());
            }
        }
        concursoDto.setMiembrosDeLaOrganizacion(organizadores);

        // Seteo de la lista de participantes
        List<String> participantes = new ArrayList<>();
        if (concurso.getTipoAccesoConcurso().equals(TipoAcceso.PRIVADO)) {
            for (UsuarioParticipaConcurso upc : concurso.getUsuariosQueParticipan()) {
                if (upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.INSCRITO)) {
                    participantes.add(upc.getUsuario().getNombreUsuario());
                }
            }
        }
        concursoDto.setParticipantes(participantes);

        // Seteo de la lista de jurado
        List<String> miembrosJurado = new ArrayList<>();
        if (concurso.getTipoVotanteConcurso().equals(TipoVotante.JURADO)) {
            for (UsuarioParticipaConcurso upc : concurso.getUsuariosQueParticipan()) {
                if (upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.JURADO)) {
                    miembrosJurado.add(upc.getUsuario().getNombreUsuario());
                }
            }
        }
        concursoDto.setMiembrosDelJurado(miembrosJurado);

        return concursoDto;
    }

    @Autowired
    public void setCategoriaFotograficaDao(CategoriaFotograficaDao categoriaFotograficaDao) {
        ConcursoConversor.categoriaFotograficaDao = categoriaFotograficaDao;
    }

    @Autowired
    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        ConcursoConversor.servicioUsuario = servicioUsuario;
    }
}