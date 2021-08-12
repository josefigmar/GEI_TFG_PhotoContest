package com.figueiras.photocontest.backend.model.services;

import com.figueiras.photocontest.backend.model.entities.*;
import com.figueiras.photocontest.backend.model.exceptions.*;
import com.figueiras.photocontest.backend.rest.dtos.*;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
    @Autowired
    private ServicioEmail servicioEmail;
    @Autowired
    private ServicioNotificacion servicioNotificacion;

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
    public Block<Fotografia> recuperarFotografiasModeracion(Long idConcurso, int page , int size) {

        Pageable pageable = PageRequest.of(page, size);

        Slice<Fotografia> fotografiaSlice = fotografiaDao.findByConcursoIdConcursoAndEstadoModeracion(
                idConcurso, EstadoModeracion.EN_ESPERA, pageable);

        Block<Fotografia> fotografiaBlock = new Block<>(fotografiaSlice.getContent(), fotografiaSlice.hasNext());

        return fotografiaBlock;
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
    public void participarConcurso(FotografiaDto datosFotografia)
            throws InstanceNotFoundException, DatosDeFotografiaNoValidosException, UsuarioNoPuedeParticiparException {

        Usuario usuario = servicioUsuario.recuperarUsuario(datosFotografia.getNombreUsuario());
        Optional<Concurso> concursoOptional = concursoDao.findById(datosFotografia.getIdConcurso());
        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.toString(), datosFotografia.getIdConcurso());
        }
        Concurso concurso = concursoOptional.get();
        validarParticipacion(datosFotografia, concurso, usuario);

        Fotografia fotografia = FotografiaConversor.toFotografia(datosFotografia, concurso, usuario);
        fotografiaDao.save(fotografia);

        Optional<UsuarioParticipaConcurso> usuarioParticipaConcursoOptional =
                usuarioParticipaConcursoDao.findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
                        usuario.getIdUsuario(),
                        datosFotografia.getIdConcurso(),
                        RolUsuarioConcurso.INSCRITO);
        // Si es la primera vez que participa
        if (usuarioParticipaConcursoOptional.isEmpty()) {
            UsuarioParticipaConcurso usuarioParticipaConcurso = new UsuarioParticipaConcurso();
            usuarioParticipaConcurso.setConcurso(concurso);
            usuarioParticipaConcurso.setUsuario(usuario);
            usuarioParticipaConcurso.setRolUsuarioConcurso(RolUsuarioConcurso.INSCRITO);
            if (!concurso.getModeracion()) {
                usuarioParticipaConcurso.setFechaInicioParticipacion(LocalDateTime.now());
            }
            // Se persisten los datos
            usuarioParticipaConcursoDao.save(usuarioParticipaConcurso);

            //// Se actualizan los concursos en el usuario
            //Set<UsuarioParticipaConcurso> nuevoConjuntoParaUsuario = usuario.getConcursosEnLosQueParticipa();
            //nuevoConjuntoParaUsuario.add(usuarioParticipaConcurso);
            //usuario.setConcursosEnLosQueParticipa(nuevoConjuntoParaUsuario);

            //// Se actualizan los usuarios en el concurso
            //Set<UsuarioParticipaConcurso> nuevoConjuntoParaConcurso = concurso.getUsuariosQueParticipan();
            //nuevoConjuntoParaConcurso.add(usuarioParticipaConcurso);
            //concurso.setUsuariosQueParticipan(nuevoConjuntoParaConcurso);

            //servicioUsuario.actualizarUsuario(usuario);
            //concursoDao.save(concurso);
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
    public boolean isRol(String nombreUsuario, long idConcurso, RolUsuarioConcurso rol) throws InstanceNotFoundException {

        boolean result = false;
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuario);
        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);

        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.toString(), idConcurso);
        }

        for (UsuarioParticipaConcurso upc : concursoOptional.get().getUsuariosQueParticipan()) {
            if (upc.getUsuario().equals(usuario) && upc.getRolUsuarioConcurso().equals(rol)) {
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
    public List<CategoriaFotografica> recuperarCategoriasConcurso(long idConcurso) throws InstanceNotFoundException {

        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if (concursoOptional.isEmpty()) {
            throw new InstanceNotFoundException(Concurso.class.toString(), idConcurso);
        }

        Concurso concurso = concursoOptional.get();
        return new ArrayList<>(concurso.getCategoriasPermitidas());
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

    @Override
    public Fotografia recuperarDatosFotografia(long idFotografia) throws InstanceNotFoundException {

        Optional<Fotografia> fotografiaOptional = fotografiaDao.findById(idFotografia);

        if(fotografiaOptional.isEmpty()){
            throw new InstanceNotFoundException(Fotografia.class.toString(), idFotografia);
        }

        return fotografiaOptional.get();
    }

    @Override
    public List<Fotografia> recuperarFotografiasDeConcurso(long idConcurso) {

        List<Fotografia> fotografiaList = fotografiaDao.recuperarFotografias(idConcurso);

        return fotografiaList;

    }

    @Override
    public void supervisarFotografia(long idFotografia, String nombreFotografia,
                                     long idConcurso, String nombreConcurso, String decision, String motivo,
                                     String nombreUsuarioAutor)
            throws InstanceNotFoundException {

        // Recuperacion de la fotografía
        Optional<Fotografia> fotografiaOptional = fotografiaDao.findById(idFotografia);
        Usuario usuario = servicioUsuario.recuperarUsuario(nombreUsuarioAutor);
        Optional<Concurso> concursoOptional = concursoDao.findById(idConcurso);
        if(fotografiaOptional.isEmpty() || concursoOptional.isEmpty()){
            throw new InstanceNotFoundException(Fotografia.class.toString(), idFotografia);
        }

        Fotografia fotografia = fotografiaOptional.get();
        Concurso concurso = concursoOptional.get();

        // Si el resultado de la moderación es DENEGADA, se procede a eliminar la participación
        // provisional del usuario y los datos de la foto en BBDD
        if(decision.equals(EstadoModeracion.DENEGADA.toString())){

            Optional<UsuarioParticipaConcurso> usuarioParticipaConcursoOptional =
                    usuarioParticipaConcursoDao.findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
                            usuario.getIdUsuario(),
                            idConcurso,
                            RolUsuarioConcurso.INSCRITO);
            if(usuarioParticipaConcursoOptional.isEmpty()){
                throw new InstanceNotFoundException(UsuarioParticipaConcurso.class.toString(), usuario.getIdUsuario());
            }
            UsuarioParticipaConcurso usuarioParticipaConcurso = usuarioParticipaConcursoOptional.get();
            //// Se elimina la participación del concurso
            //Set<UsuarioParticipaConcurso> nuevoConjuntoUsuarioParticipaConcurso =
            //        concurso.getUsuariosQueParticipan();
            //nuevoConjuntoUsuarioParticipaConcurso.remove(usuarioParticipaConcurso);
            //concurso.setUsuariosQueParticipan(nuevoConjuntoUsuarioParticipaConcurso);

            //// Se elimina la participación del usuario
            //Set<UsuarioParticipaConcurso> nuevoConjuntoUsuarioParticipaConcursoUsuario =
            //        usuario.getConcursosEnLosQueParticipa();
            //nuevoConjuntoUsuarioParticipaConcursoUsuario.remove(usuarioParticipaConcurso);
            //usuario.setConcursosEnLosQueParticipa(nuevoConjuntoUsuarioParticipaConcursoUsuario);

            //// Una vez se ha eliminado de las entidades en donde se usaba, se elimina la propia entidad
            usuarioParticipaConcursoDao.delete(usuarioParticipaConcurso);

            // Se eliminan los datos de la fotografía
            eliminarFotografia(fotografia);

            servicioNotificacion.crearNotificacion(
                    messageSource.getMessage("project.supervisePhotography.photographyDenied",
                            new Object[]{nombreFotografia, nombreConcurso},
                            new Locale(usuario.getLenguaje().toString())),
                    motivo,
                    usuario.getNombreUsuario());

            servicioEmail.enviarMailGmail(
                    usuario.getCorreoElectronicoUsuario(),
                    messageSource.getMessage("project.supervisePhotography.photographyDenied",
                            new Object[]{nombreFotografia, nombreConcurso},
                            new Locale(usuario.getLenguaje().toString())),
                    motivo
            );

            return;
        }
        // Si ha sido aprobada
        fotografia.setEstadoModeracion(EstadoModeracion.APROBADA);
        fotografia.setFechaInicioParticipacion(LocalDateTime.now());
        Optional<UsuarioParticipaConcurso> usuarioParticipaConcursoOptional =
                usuarioParticipaConcursoDao.findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
                        usuario.getIdUsuario(),
                        idConcurso,
                        RolUsuarioConcurso.INSCRITO);
        if(usuarioParticipaConcursoOptional.isEmpty()){
            throw new InstanceNotFoundException(UsuarioParticipaConcurso.class.toString(), usuario.getIdUsuario());
        }
        UsuarioParticipaConcurso usuarioParticipaConcurso = usuarioParticipaConcursoOptional.get();
        usuarioParticipaConcurso.setFechaInicioParticipacion(LocalDateTime.now());

        usuarioParticipaConcursoDao.save(usuarioParticipaConcurso);
        fotografiaDao.save(fotografia);

        servicioNotificacion.crearNotificacion(
                messageSource.getMessage("project.supervisePhotography.photographyApproved",
                        new Object[]{nombreFotografia, nombreConcurso},
                        new Locale(usuario.getLenguaje().toString())),
                motivo,
                usuario.getNombreUsuario());

        servicioEmail.enviarMailGmail(
                usuario.getCorreoElectronicoUsuario(),
                messageSource.getMessage("project.supervisePhotography.photographyApproved",
                        new Object[]{nombreFotografia, nombreConcurso},
                        new Locale(usuario.getLenguaje().toString())),
                motivo
        );

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

    private void validarParticipacion(FotografiaDto datosFotografia, Concurso concurso, Usuario usuario)
            throws UsuarioNoPuedeParticiparException, DatosDeFotografiaNoValidosException {

        boolean hayErrores = false;
        List<ErrorCampoDto> erroresCampoDtoList = new ArrayList<>();

        // El usuario tiene que haber aceptado las normas
        if(!datosFotografia.isAceptoLasNormas()){
            String errorMsg = messageSource.getMessage("project.exceptions.NoRulesAcceptance",
                    null,
                    new Locale(usuario.getLenguaje().toString()));
            ErroresDto erroresDto = new ErroresDto(errorMsg);
            throw new UsuarioNoPuedeParticiparException(erroresDto);
        }

        // El usuario no debe ser jurado del concurso
        Optional<UsuarioParticipaConcurso> usuarioParticipaJurado =
                usuarioParticipaConcursoDao.findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
                        usuario.getIdUsuario(),
                        concurso.getIdConcurso(),
                        RolUsuarioConcurso.JURADO);
        if (usuarioParticipaJurado.isPresent()) {
            String errorMsg = messageSource.getMessage("project.exceptions.OtherRole",
                    null,
                    new Locale(usuario.getLenguaje().toString()));
            ErroresDto erroresDto = new ErroresDto(errorMsg);
            throw new UsuarioNoPuedeParticiparException(erroresDto);

        }

        // El usuario no debe ser organizador del concurso
        Optional<UsuarioParticipaConcurso> usuarioParticipaOrganizando =
                usuarioParticipaConcursoDao.findByUsuarioIdUsuarioAndConcursoIdConcursoAndRolUsuarioConcurso(
                        usuario.getIdUsuario(),
                        concurso.getIdConcurso(),
                        RolUsuarioConcurso.ORGANIZADOR);
        if (usuarioParticipaOrganizando.isPresent()) {
            String errorMsg = messageSource.getMessage("project.exceptions.OtherRole",
                    null,
                    new Locale(usuario.getLenguaje().toString()));
            ErroresDto erroresDto = new ErroresDto(errorMsg);
            throw new UsuarioNoPuedeParticiparException(erroresDto);
        }

        // Se verifica que si el concurso es privado, el usuario está dentro de los inscritos
        if (concurso.getTipoAccesoConcurso().equals(TipoAcceso.PRIVADO)) {
            boolean estaInscrito = false;
            for (UsuarioParticipaConcurso upc : concurso.getUsuariosQueParticipan()) {
                if (upc.getRolUsuarioConcurso().equals(RolUsuarioConcurso.INSCRITO)) {
                    if (upc.getUsuario().getIdUsuario().equals(usuario.getIdUsuario())) {
                        estaInscrito = true;
                    }
                }
            }
            if (!estaInscrito) {
                String errorMsg = messageSource.getMessage("project.exceptions.UnregisteredUser",
                        null,
                        new Locale(usuario.getLenguaje().toString()));
                ErroresDto erroresDto = new ErroresDto(errorMsg);
                throw new UsuarioNoPuedeParticiparException(erroresDto);
            }
        }

        // Se recuperan todos los participantes del concurso para ver si el usuario puede participar.
        List<UsuarioParticipaConcurso> usuariosParticipaConcursoList =
                usuarioParticipaConcursoDao.findByConcursoIdConcursoAndRolUsuarioConcurso(concurso.getIdConcurso(),
                        RolUsuarioConcurso.INSCRITO);

        if (usuariosParticipaConcursoList.size() > concurso.getMaxFotos()) {
            String errorMsg = messageSource.getMessage("project.exceptions.MaxPhotos",
                    null,
                    new Locale(usuario.getLenguaje().toString()));
            ErroresDto erroresDto = new ErroresDto(errorMsg);
            throw new UsuarioNoPuedeParticiparException(erroresDto);
        }

        // Se recuperan todas las participaciones del usuario para ver si puede seguir participando
        List<Fotografia> fotografiasUsuario = fotografiaDao.recuperarFotografiasConcursoUsuario(
                concurso.getIdConcurso(), usuario.getIdUsuario());

        if (fotografiasUsuario.size() >= concurso.getMaxFotosUsuario()) {
            String errorMsg = messageSource.getMessage("project.exceptions.MaxUserPhotos",
                    null,
                    new Locale(usuario.getLenguaje().toString()));
            ErroresDto erroresDto = new ErroresDto(errorMsg);
            throw new UsuarioNoPuedeParticiparException(erroresDto);
        }

        // Validación de los datos del formulario

        if (datosFotografia.getDatosJpg().equals("")) {
            hayErrores = true;
            ErrorCampoDto errorTitulo = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.participate.jpgphoto",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.participate.jpgphoto",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorTitulo);
        }

        if (datosFotografia.getDatosRaw().equals("") && !concurso.getFormato().equals(FormatoFotografia.JPG)) {
            hayErrores = true;
            ErrorCampoDto errorTitulo = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.participate.rawphoto",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.participate.rawphoto",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorTitulo);
        }

        if (concurso.getTituloReq()) {
            String titulo = datosFotografia.getTituloFotografia();
            if (titulo.equals("") || titulo.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorTitulo = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.title",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.title",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorTitulo);
            }
        }

        if (concurso.getDescReq()) {
            String desc = datosFotografia.getDescripcionFotografia();
            if (desc.equals("") || desc.length() > 200) {
                hayErrores = true;
                ErrorCampoDto errorDesc = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.desc",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.desc",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorDesc);
            }
        }

        if (concurso.getDatosExifReq()) {
            String make = datosFotografia.getFabricanteCamara();
            if (make.equals("") || make.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String model = datosFotografia.getModeloCamara();
            if (model.equals("") || model.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String distFocal = datosFotografia.getDistanciaFocal();
            if (distFocal.equals("") || distFocal.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String apertura = datosFotografia.getAperturaDiafragma();
            if (apertura.equals("") || apertura.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String velObturacion = datosFotografia.getVelocidadObturacion();
            if (velObturacion.equals("") || velObturacion.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String ISO = datosFotografia.getIso();
            if (ISO.equals("") || ISO.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
            String resolucion = datosFotografia.getResolucion();
            if (resolucion.equals("") || resolucion.length() > 50) {
                hayErrores = true;
                ErrorCampoDto errorExif = new ErrorCampoDto(
                        messageSource.getMessage(
                                "project.fields.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString())
                        ),
                        messageSource.getMessage(
                                "project.exceptions.participate.exifdata",
                                null,
                                new Locale(usuario.getLenguaje().toString()))
                );
                erroresCampoDtoList.add(errorExif);
            }
        }

        // Si no viene la categoria
        if (datosFotografia.getIdCategoria() == 0) {
            hayErrores = true;
            ErrorCampoDto errorCategory = new ErrorCampoDto(
                    messageSource.getMessage(
                            "project.fields.participate.category",
                            null,
                            new Locale(usuario.getLenguaje().toString())
                    ),
                    messageSource.getMessage(
                            "project.exceptions.participate.category",
                            null,
                            new Locale(usuario.getLenguaje().toString()))
            );
            erroresCampoDtoList.add(errorCategory);
        }

        if (hayErrores) {
            ErroresDto erroresDto = new ErroresDto(erroresCampoDtoList);
            throw new DatosDeFotografiaNoValidosException(erroresDto);
        }
    }

    private void eliminarFotografia(Fotografia fotografia){

        fotografiaDao.delete(fotografia);
    }
}
