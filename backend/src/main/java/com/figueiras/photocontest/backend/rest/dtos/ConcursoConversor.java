package com.figueiras.photocontest.backend.rest.dtos;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.model.services.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public static Concurso toConcurso(CrearConcursoDto datosConcurso, String nombreUsuarioCreador)
            throws InstanceNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        Concurso nuevoConcurso = new Concurso();

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
        nuevoConcurso.setLocReq(datosConcurso.isLocalizacionRequerida());
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

    @Autowired
    public void setCategoriaFotograficaDao(CategoriaFotograficaDao categoriaFotograficaDao) {
        ConcursoConversor.categoriaFotograficaDao = categoriaFotograficaDao;
    }

    @Autowired
    public void setServicioUsuario(ServicioUsuario servicioUsuario) {
        ConcursoConversor.servicioUsuario = servicioUsuario;
    }
}