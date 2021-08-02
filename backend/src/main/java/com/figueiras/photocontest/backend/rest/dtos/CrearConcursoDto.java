package com.figueiras.photocontest.backend.rest.dtos;

public class CrearConcursoDto {

    private String nombreConcurso;
    private String descripcionConcurso;
    private String fotoConcurso;
    private String basesConcurso;
    private boolean categoriaUnica;
    private String[] listaCategorias;
    private String[] miembrosDeLaOrganizacion;
    private String fechaInicio;
    private String fechaInicioVotacion;
    private boolean participanteAbierto;
    private String[] participantes;
    private int numeroMaximoFotografias;
    private int numeroMaximoFotografiasParticipante;
    private String formatoRequerido;
    private boolean tituloRequerido;
    private boolean descripcionRequerida;
    private boolean datosExifRequeridos;
    private boolean localizacionRequerida;
    private boolean ocutarFotosHastaVotacion;
    private boolean ocultarResultadosHastaFinal;
    private boolean activarModeracion;
    private String tipoVotante;
    private String[] miembrosDelJurado;
    private String metodoVoto;
    private String descripcionVotacionJurado;
    private String fechaLimiteVotacion;
    private int numeroMaximoVotosPorUsuario;
    private int numeroMaximoDeFotografiasGanadoras;

    public CrearConcursoDto() {
    }

    public String getNombreConcurso() {
        return nombreConcurso;
    }

    public void setNombreConcurso(String nombreConcurso) {
        this.nombreConcurso = nombreConcurso;
    }

    public String getDescripcionConcurso() {
        return descripcionConcurso;
    }

    public void setDescripcionConcurso(String descripcionConcurso) {
        this.descripcionConcurso = descripcionConcurso;
    }

    public String getFotoConcurso() {
        return fotoConcurso;
    }

    public void setFotoConcurso(String fotoConcurso) {
        this.fotoConcurso = fotoConcurso;
    }

    public String getBasesConcurso() {
        return basesConcurso;
    }

    public void setBasesConcurso(String basesConcurso) {
        this.basesConcurso = basesConcurso;
    }

    public boolean isCategoriaUnica() {
        return categoriaUnica;
    }

    public void setCategoriaUnica(boolean categoriaUnica) {
        this.categoriaUnica = categoriaUnica;
    }

    public String[] getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(String[] listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaInicioVotacion() {
        return fechaInicioVotacion;
    }

    public void setFechaInicioVotacion(String fechaInicioVotacion) {
        this.fechaInicioVotacion = fechaInicioVotacion;
    }

    public boolean isParticipanteAbierto() {
        return participanteAbierto;
    }

    public void setParticipanteAbierto(boolean participanteAbierto) {
        this.participanteAbierto = participanteAbierto;
    }

    public int getNumeroMaximoFotografias() {
        return numeroMaximoFotografias;
    }

    public void setNumeroMaximoFotografias(int numeroMaximoFotografias) {
        this.numeroMaximoFotografias = numeroMaximoFotografias;
    }

    public int getNumeroMaximoFotografiasParticipante() {
        return numeroMaximoFotografiasParticipante;
    }

    public void setNumeroMaximoFotografiasParticipante(int numeroMaximoFotografiasParticipante) {
        this.numeroMaximoFotografiasParticipante = numeroMaximoFotografiasParticipante;
    }

    public String getFormatoRequerido() {
        return formatoRequerido;
    }

    public void setFormatoRequerido(String formatoRequerido) {
        this.formatoRequerido = formatoRequerido;
    }

    public boolean isTituloRequerido() {
        return tituloRequerido;
    }

    public void setTituloRequerido(boolean tituloRequerido) {
        this.tituloRequerido = tituloRequerido;
    }

    public boolean isDescripcionRequerida() {
        return descripcionRequerida;
    }

    public void setDescripcionRequerida(boolean descripcionRequerida) {
        this.descripcionRequerida = descripcionRequerida;
    }

    public boolean isDatosExifRequeridos() {
        return datosExifRequeridos;
    }

    public void setDatosExifRequeridos(boolean datosExifRequeridos) {
        this.datosExifRequeridos = datosExifRequeridos;
    }

    public boolean isLocalizacionRequerida() {
        return localizacionRequerida;
    }

    public void setLocalizacionRequerida(boolean localizacionRequerida) {
        this.localizacionRequerida = localizacionRequerida;
    }

    public boolean isOcutarFotosHastaVotacion() {
        return ocutarFotosHastaVotacion;
    }

    public void setOcutarFotosHastaVotacion(boolean ocutarFotosHastaVotacion) {
        this.ocutarFotosHastaVotacion = ocutarFotosHastaVotacion;
    }

    public boolean isOcultarResultadosHastaFinal() {
        return ocultarResultadosHastaFinal;
    }

    public void setOcultarResultadosHastaFinal(boolean ocultarResultadosHastaFinal) {
        this.ocultarResultadosHastaFinal = ocultarResultadosHastaFinal;
    }

    public String[] getMiembrosDeLaOrganizacion() {
        return miembrosDeLaOrganizacion;
    }

    public void setMiembrosDeLaOrganizacion(String[] miembrosDeLaOrganizacion) {
        this.miembrosDeLaOrganizacion = miembrosDeLaOrganizacion;
    }

    public String[] getParticipantes() {
        return participantes;
    }

    public void setParticipantes(String[] participantes) {
        this.participantes = participantes;
    }

    public String[] getMiembrosDelJurado() {
        return miembrosDelJurado;
    }

    public void setMiembrosDelJurado(String[] miembrosDelJurado) {
        this.miembrosDelJurado = miembrosDelJurado;
    }

    public boolean isActivarModeracion() {
        return activarModeracion;
    }

    public void setActivarModeracion(boolean activarModeracion) {
        this.activarModeracion = activarModeracion;
    }

    public String getTipoVotante() {
        return tipoVotante;
    }

    public void setTipoVotante(String tipoVotante) {
        this.tipoVotante = tipoVotante;
    }

    public String getMetodoVoto() {
        return metodoVoto;
    }

    public void setMetodoVoto(String metodoVoto) {
        this.metodoVoto = metodoVoto;
    }

    public String getDescripcionVotacionJurado() {
        return descripcionVotacionJurado;
    }

    public void setDescripcionVotacionJurado(String descripcionVotacionJurado) {
        this.descripcionVotacionJurado = descripcionVotacionJurado;
    }

    public String getFechaLimiteVotacion() {
        return fechaLimiteVotacion;
    }

    public void setFechaLimiteVotacion(String fechaLimiteVotacion) {
        this.fechaLimiteVotacion = fechaLimiteVotacion;
    }

    public int getNumeroMaximoVotosPorUsuario() {
        return numeroMaximoVotosPorUsuario;
    }

    public void setNumeroMaximoVotosPorUsuario(int numeroMaximoVotosPorUsuario) {
        this.numeroMaximoVotosPorUsuario = numeroMaximoVotosPorUsuario;
    }

    public int getNumeroMaximoDeFotografiasGanadoras() {
        return numeroMaximoDeFotografiasGanadoras;
    }

    public void setNumeroMaximoDeFotografiasGanadoras(int numeroMaximoDeFotografiasGanadoras) {
        this.numeroMaximoDeFotografiasGanadoras = numeroMaximoDeFotografiasGanadoras;
    }
}
