package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.CategoriaDuplicadaException;
import com.figueiras.photocontest.backend.model.exceptions.DatosDeConcursoNoValidosException;
import com.figueiras.photocontest.backend.model.exceptions.InstanceNotFoundException;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ServicioConcursoImpl implements ServicioConcurso {

    @Autowired
    MessageSource messageSource;
    @Autowired
    private ConcursoDao concursoDao;
    @Autowired
    private UsuarioParticipaConcursoDao usuarioParticipaConcursoDao;
    @Autowired
    private ServicioUsuario servicioUsuario;
    @Autowired
    private CategoriaFotograficaDao categoriaFotograficaDao;
    @Autowired
    private FotografiaDao fotografiaDao;

    @Override
    public Block<Concurso> recuperarConcursos(Long idCategoria, Integer estado, String nombre,
                                              int page, int size) {
        if (nombre == null && estado == null && idCategoria == null) {
            PageRequest pageRequest = PageRequest.of(page, size);
            Slice<Concurso> sliceConcursos = concursoDao.buscarConcursosOrdenarPorFechaCreacion(pageRequest);

            return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
        }
        EstadoConcurso[] estadoConcursos = EstadoConcurso.values();
        Optional<CategoriaFotografica> categoriaFotograficaOpt = categoriaFotograficaDao.findById(idCategoria);
        Slice<Concurso> sliceConcursos = concursoDao.find(estado == null ? null : estadoConcursos[estado], categoriaFotograficaOpt.get(), nombre, page, size);

        return new Block<>(sliceConcursos.getContent(), sliceConcursos.hasNext());
    }

    @Override
    public Concurso recuperarConcurso(Long idConcurso) throws InstanceNotFoundException {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.getName(), idConcurso);
        }
        return concursoOptional.get();
    }

    @Override
    public ConcursoDto recuperarDatosConcurso(Long idConcurso) throws InstanceNotFoundException {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.getName(), idConcurso);
        }
        ConcursoDto resultado = ConcursoConversor.toConcursoDto(concursoOptional.get());

        return resultado;
    }

    @Override
    public void crearConcurso(ConcursoDto datosConcurso, String nombreUsuario)
            throws InstanceNotFoundException, DatosDeConcursoNoValidosException {

        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);
        validarConcurso(datosConcurso, usuario);


        // Si el usuario está editando un concurso
        LocalDateTime fechaCreacion = null;
        if ((Long) datosConcurso.getIdConcurso() != 0) {
            Optional<Concurso> concursoOptional = concursoDao.findById(datosConcurso.getIdConcurso());
            fechaCreacion = concursoOptional.get().getFechaCreacion();
            eliminarConcurso(datosConcurso.getIdConcurso());
        }

        Concurso nuevoConcurso = ConcursoConversor.toConcurso(datosConcurso, nombreUsuario, fechaCreacion);

        // Se persiste el nuevo concurso
        concursoDao.save(nuevoConcurso);

        // Se actualiza cada uno de los usuarios que participan en el nuevo concurso
        // con el nuevo concurso y su respectivo rol.
        for (UsuarioParticipaConcurso usuarioParticipante : nuevoConcurso.getUsuariosQueParticipan()) {
            usuarioParticipaConcursoDao.save(usuarioParticipante);
            servicioUsuario.actualizarUsuario(usuarioParticipante.getUsuario());
        }

        // Se actualiza cada categoría participante añadiendo el concurso a su lista
        for (CategoriaFotografica c : nuevoConcurso.getCategoriasPermitidas()) {
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
        if (categoriaFotograficaOptional.isPresent()) {
            throw new CategoriaDuplicadaException();
        }
        CategoriaFotografica categoriaFotografica = new CategoriaFotografica();

        categoriaFotografica.setNombreCategoria(datosCategoria.getNombreCategoria());
        categoriaFotografica.setDescripcionCategoria(datosCategoria.getDescripcion());

        categoriaFotograficaDao.save(categoriaFotografica);
    }

    @Override
    public int getNumeroDeParticipantes(long idConcurso) {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);

        if (concursoOptional.isEmpty()) {
            return 0;
        }

        int numeroDeParticipantes = 0;

        for (UsuarioParticipaConcurso upc : concursoOptional.get().getUsuariosQueParticipan()) {
            if (upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.INSCRITO)) {
                numeroDeParticipantes++;
            }
        }
        return numeroDeParticipantes;
    }

    @Override
    public boolean isOrganizador(String nombreUsuario, long idConcurso) throws InstanceNotFoundException {

        boolean result = false;
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);
        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);

        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.toString(), idConcurso);
        }

        for (UsuarioParticipaConcurso upc : concursoOptional.get().getUsuariosQueParticipan()) {
            if (upc.getUsuario().equals(usuario) && upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.ORGANIZADOR)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public Block<Usuario> recuperarOrganizadores(long idConcurso, int page, int size) throws InstanceNotFoundException {

        Pageable pageable = PageRequest.of(page, size);
        Slice<UsuarioParticipaConcurso> organizadoresSlice =
                usuarioParticipaConcursoDao.findOrganizadores(idConcurso, pageable);

        Block<Usuario> organizadores = new Block<Usuario>(
                organizadoresSlice.getContent().stream().map(upc -> upc.getUsuario()).collect(Collectors.toList()),
                organizadoresSlice.hasNext());

        return organizadores;
    }

    @Override
    public Block<Usuario> recuperarParticipantes(long idConcurso, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Slice<UsuarioParticipaConcurso> participantesSlice =
                usuarioParticipaConcursoDao.findInscritos(idConcurso, pageable);

        Block<Usuario> participantes = new Block<Usuario>(
                participantesSlice.getContent().stream().map(upc -> upc.getUsuario()).collect(Collectors.toList()),
                participantesSlice.hasNext());

        return participantes;
    }

    @Override
    public Block<Usuario> recuperarJurado(long idConcurso, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);
        Slice<UsuarioParticipaConcurso> juradoSlice =
                usuarioParticipaConcursoDao.findJurado(idConcurso, pageable);

        Block<Usuario> jurado = new Block<Usuario>(
                juradoSlice.getContent().stream().map(upc -> upc.getUsuario()).collect(Collectors.toList()),
                juradoSlice.hasNext());

        return jurado;
    }

    @Override
    public void eliminarConcurso(long idConcurso) throws InstanceNotFoundException {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.toString(), idConcurso);
        }

        Concurso concurso = concursoOptional.get();
        vaciarParticipantesConcurso(concurso);
        vaciarFotografiasConcurso(concurso);
        concursoDao.delete(concurso);
    }

    private void validarConcurso(ConcursoDto datosConcurso, Usuario usuario)
            throws DatosDeConcursoNoValidosException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        boolean hayErrores = false;

        List<ErrorCampoDto> erroresCampoDtoList = new ArrayList<>();

        // La fecha de inicio del concurso debe ser inferior a la fecha de inicio de votación
        if (LocalDateTime.parse(datosConcurso.getFechaInicio(), formatter).
                compareTo(LocalDateTime.parse(datosConcurso.getFechaInicioVotacion(), formatter)) >= 0) {
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
        if (LocalDateTime.parse(datosConcurso.getFechaInicioVotacion(), formatter).
                compareTo(LocalDateTime.parse(datosConcurso.getFechaLimiteVotacion(), formatter)) >= 0) {
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
        if (datosConcurso.getNumeroMaximoFotografias() > 200) {
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
        if (datosConcurso.getNumeroMaximoFotografiasParticipante() > 5) {
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
        if (datosConcurso.getNumeroMaximoDeFotografiasGanadoras() > 10) {
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

        if (hayErrores) {
            ErroresDto erroresDto = new ErroresDto(erroresCampoDtoList);
            throw new DatosDeConcursoNoValidosException(erroresDto);
        }
    }

    private void vaciarParticipantesConcurso(Concurso concurso) {
        Set<UsuarioParticipaConcurso> usuariosQueParticipan = new HashSet<>(concurso.getUsuariosQueParticipan());
        for (UsuarioParticipaConcurso upc : usuariosQueParticipan) {
            Usuario usuario = upc.getUsuario();
            Set<UsuarioParticipaConcurso> concursosEnLosQueParticipa = usuario.getConcursosEnLosQueParticipa();
            concursosEnLosQueParticipa.remove(upc);
            Set<UsuarioParticipaConcurso> usuarioParticipaConcursos = concurso.getUsuariosQueParticipan();
            usuarioParticipaConcursos.remove(upc);
            servicioUsuario.actualizarUsuario(usuario);
            concursoDao.save(concurso);
            usuarioParticipaConcursoDao.delete(upc);
        }
    }

    private void vaciarFotografiasConcurso(Concurso concurso) {
        List<Fotografia> fotografiasDeConcurso = fotografiaDao.recuperarFotografias(concurso.getIdConcurso());
        for (Fotografia f : fotografiasDeConcurso) {
            fotografiaDao.delete(f);
        }
    }
}
