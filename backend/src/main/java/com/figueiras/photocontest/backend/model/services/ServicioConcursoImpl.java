package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CategoriaDuplicadaException;
import com.figueiras.photocontest.backend.model.exceptions.DatosDeConcursoNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ServicioConcursoImpl implements ServicioConcurso{

    @Autowired
    private ConcursoDao concursoDao;

    @Autowired
    private UsuarioParticipaConcursoDao usuarioParticipaConcursoDao;

    @Autowired
    private ServicioUsuario servicioUsuario;

    @Autowired
    private CategoriaFotograficaDao categoriaFotograficaDao;

    @Autowired
    MessageSource messageSource;

    @Override
    public Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                              int page, int size) {
        if(nombre == null && estado == null && idCategoria == null) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Slice<Concurso> sliceConcursos = concursoDao.buscarConcursosOrdenarPorFechaCreacion(pageRequest);

            return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
        }
        EstadoConcurso[] estadoConcursos = EstadoConcurso.values();
        Optional<CategoriaFotografica> categoriaFotograficaOpt = categoriaFotograficaDao.findById(idCategoria);
        Slice<Concurso> sliceConcursos = concursoDao.find(estado==null? null : estadoConcursos[estado], categoriaFotograficaOpt.get(), nombre, page, size);

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

    @Override
    public void crearConcurso(CrearConcursoDto datosConcurso, String nombreUsuario)
            throws InstanceNotFoundException, DatosDeConcursoNoValidosException {

        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);
        validarConcurso(datosConcurso, usuario);

        Concurso nuevoConcurso = ConcursoConversor.toConcurso(datosConcurso, nombreUsuario);

        // Se persiste el nuevo concurso
        concursoDao.save(nuevoConcurso);

        // Se actualiza cada uno de los usuarios que participan en el nuevo concurso
        // con el nuevo concurso y su respectivo rol.
        for(UsuarioParticipaConcurso usuarioParticipante : nuevoConcurso.getUsuariosQueParticipan()){
            usuarioParticipaConcursoDao.save(usuarioParticipante);
            servicioUsuario.actualizarUsuario(usuarioParticipante.getUsuario());
        }

        // Se actualiza cada categoría participante añadiendo el concurso a su lista
        for(CategoriaFotografica c : nuevoConcurso.getCategoriasPermitidas()){
            Set<Concurso> concuros = c.getConcursosEnDondeSeUsa();
            concuros.add(nuevoConcurso);
            c.setConcursosEnDondeSeUsa(concuros);
            categoriaFotograficaDao.save(c);
        }
    }

    @Override
    public void crearCategoria(CategoriaFotograficaDto datosCategoria) throws CategoriaDuplicadaException {

        Optional<CategoriaFotografica> categoriaFotograficaOptional =
                categoriaFotograficaDao.findByNombreCategoria(datosCategoria.getNombreCategoria());

        // Se comprueba si la categoría ya existe
        if(categoriaFotograficaOptional.isPresent()){
            throw new CategoriaDuplicadaException();
        }
        CategoriaFotografica categoriaFotografica = new CategoriaFotografica();

        categoriaFotografica.setNombreCategoria(datosCategoria.getNombreCategoria());
        categoriaFotografica.setDescripcionCategoria(datosCategoria.getDescripcion());

        categoriaFotograficaDao.save(categoriaFotografica);
    }

    private void validarConcurso(CrearConcursoDto datosConcurso, Usuario usuario)
            throws DatosDeConcursoNoValidosException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        boolean hayErrores = false;

        List<ErrorCampoDto> erroresCampoDtoList = new ArrayList<>();

        // La fecha de inicio del concurso debe ser inferior a la fecha de inicio de votación
        if(LocalDateTime.parse(datosConcurso.getFechaInicio(), formatter).
                compareTo(LocalDateTime.parse(datosConcurso.getFechaInicioVotacion(), formatter)) >=0){
            hayErrores = true;
            ErrorCampoDto errorFechas1 = new ErrorCampoDto(
                    messageSource.getMessage(
                    "project.fields.createContest.contestStartData",
                    null,
                    new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.fechaInicioConcursoInferiorAInicioVotacion",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
                    );
            erroresCampoDtoList.add(errorFechas1);
        }

        // Lafecha de inicio de votación debe ser inferior a la fecha de fin de votación
        if(LocalDateTime.parse(datosConcurso.getFechaInicioVotacion(), formatter).
                compareTo(LocalDateTime.parse(datosConcurso.getFechaLimiteVotacion(), formatter)) >= 0){
            hayErrores = true;
            ErrorCampoDto errorFechas2 = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.createContest.votingStartData",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.fechaInicioVotacionInferiorAFinVotacion",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorFechas2);

        }

        // El número máximo de fotografías permitidas en un concurso es de 200
        if(datosConcurso.getNumeroMaximoFotografias() > 200){
            hayErrores = true;
            ErrorCampoDto errorMaxFotos = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.createContest.maxContestPhotos",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.MaxFotosPermitidas",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorMaxFotos);
        }

        // El número máximo de fotografías permitidas por usuario es 5
        if(datosConcurso.getNumeroMaximoFotografiasParticipante() > 5){
            hayErrores = true;
            ErrorCampoDto errorMaxFotosUsuario = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.createContest.maxUserPhotos",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.MaxFotosPermitidasParticipante",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorMaxFotosUsuario);
        }

        // El número máximo de fotografías ganadoras es de 10
        if(datosConcurso.getNumeroMaximoDeFotografiasGanadoras() > 10){
            hayErrores = true;
            ErrorCampoDto errorNumeroFotosGanadoras = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.exceptions.MaxFotosGanadoras",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.fields.createContest.maxWinningPhotos",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorNumeroFotosGanadoras);
        }

        if(hayErrores){
            ErroresDto erroresDto = new ErroresDto(erroresCampoDtoList);
            throw new DatosDeConcursoNoValidosException(erroresDto);
        }
    }
}
