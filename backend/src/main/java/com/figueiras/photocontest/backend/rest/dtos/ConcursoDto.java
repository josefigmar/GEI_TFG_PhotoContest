package com.figueiras.photocontest.backend.rest.dtos;

import java.util.List;

public class ConcursoDto {

    private long idConcurso;
    private String nombreConcurso;
    private String descripcionConcurso;
    private String fotoConcurso;
    private String basesConcurso;
    private String estadoConcurso;
    private boolean categoriaUnica;
    private long idCategoria;
    private List<String> listaCategorias;
    private List<String> miembrosDeLaOrganizacion;
    private String fechaInicio;
    private String fechaInicioVotacion;
    private boolean participanteAbierto;
    private String tipoAcceso;
    private List<String> participantes;
    private int numeroMaximoFotografias;
    private int numeroMaximoFotografiasParticipante;
    private String formatoRequerido;
    private boolean tituloRequerido;
    private boolean descripcionRequerida;
    private boolean datosExifRequeridos;
    private boolean ocutarFotosHastaVotacion;
    private boolean ocultarResultadosHastaFinal;
    private boolean activarModeracion;
    private String tipoVotante;
    private List<String> miembrosDelJurado;
    private String metodoVoto;
    private String descripcionVotacionJurado;
    private String fechaLimiteVotacion;
    private int numeroMaximoVotosPorUsuario;
    private int numeroMaximoDeFotografiasGanadoras;
    private String resumenVotacion;

    public ConcursoDto() {
    }

    public long getIdConcurso() {
        return idConcurso;
    }

    public void setIdConcurso(long idConcurso) {
        this.idConcurso = idConcurso;
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

    public String getEstadoConcurso() {
        return estadoConcurso;
    }

    public void setEstadoConcurso(String estadoConcurso) {
        this.estadoConcurso = estadoConcurso;
    }

    public boolean isCategoriaUnica() {
        return categoriaUnica;
    }

    public long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public void setCategoriaUnica(boolean categoriaUnica) {
        this.categoriaUnica = categoriaUnica;
    }

    public List<String> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<String> listaCategorias) {
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

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public void setTipoAcceso(String tipoAcceso) {
        this.tipoAcceso = tipoAcceso;
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

    public List<String> getMiembrosDeLaOrganizacion() {
        return miembrosDeLaOrganizacion;
    }

    public void setMiembrosDeLaOrganizacion(List<String> miembrosDeLaOrganizacion) {
        this.miembrosDeLaOrganizacion = miembrosDeLaOrganizacion;
    }

    public List<String> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<String> participantes) {
        this.participantes = participantes;
    }

    public List<String> getMiembrosDelJurado() {
        return miembrosDelJurado;
    }

    public void setMiembrosDelJurado(List<String> miembrosDelJurado) {
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

    public String getResumenVotacion() {
        return resumenVotacion;
    }

    public void setResumenVotacion(String resumenVotacion) {
        this.resumenVotacion = resumenVotacion;
    }
}
