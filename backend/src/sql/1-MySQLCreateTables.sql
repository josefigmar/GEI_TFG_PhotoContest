/******************************************************************************/
/*                                                                             *
/*          This script creates the tables needed for PhotoContest             *
/*                                                                             *
/******************************************************************************/

DROP TABLE UsuarioSigueUsuario;
DROP TABLE UsuarioParticipaConcurso;
DROP TABLE UsuarioVotaFotografia;
DROP TABLE ConcursoPermiteCategoria;
DROP TABLE UsuarioGustaCategoria;
DROP TABLE Notificacion;
DROP TABLE Fotografia;
DROP TABLE CategoriaFotografica;
DROP TABLE Usuario;
DROP TABLE Concurso;


CREATE TABLE CategoriaFotografica(
    idCategoria BIGINT NOT NULL AUTO_INCREMENT,
    nombreCategoria VARCHAR(50),
    descripcionCategoria VARCHAR(200),
    CONSTRAINT categoria_fotografica_pk PRIMARY KEY(idCategoria)
);

CREATE TABLE Usuario(
    idUsuario BIGINT NOT NULL AUTO_INCREMENT,
    nombreUsuario VARCHAR(50),
    nombrePilaUsuario VARCHAR(50),
    apellidosUsuario VARCHAR(100),
    biografiaUsuario VARCHAR(500),
    correoElectronico_usuario VARCHAR(90),
    contrasenaUsuairo VARCHAR(50),
    enlaceTwitter VARCHAR(200),
    enlaceFacebook VARCHAR(200),
    CONSTRAINT Usuario_pk PRIMARY KEY(idUsuario),
    CONSTRAINT Nombre_usuario_unique UNIQUE(nombreUsuario)
);

CREATE TABLE Notificacion(
    idNotificacion BIGINT NOT NULL AUTO_INCREMENT,
    nombreNotificacion VARCHAR(50),
    mensajeNotificacion VARCHAR(200),
    fechaCreacion datetime,
    idUsuario BIGINT NOT NULL,
    CONSTRAINT Notificacion_pk PRIMARY KEY(idNotificacion),
    CONSTRAINT Notificacion_id_usuario_fk FOREIGN KEY(idUsuario)
                         REFERENCES Usuario(idUsuario)
);

CREATE TABLE Fotografia(
    idFotografia BIGINT NOT NULL AUTO_INCREMENT,
    tituloFotografia VARCHAR(50),
    descripcionFotografia VARCHAR(200),
    aperturaDiafragma VARCHAR(50),
    fabricanteCamara VARCHAR(50),
    modeloCamara VARCHAR(50),
    distanciaFocal VARCHAR(50),
    velocidadObturacion VARCHAR(50),
    iso VARCHAR(50),
    resolucion VARCHAR(50),
    datosJpg MEDIUMBLOB,
    datosRaw MEDIUMBLOB,
    fechaSubida DATETIME,
    fechaInicioParticipacion DATETIME,
    estadoModeracion TINYINT,
    idCategoria BIGINT,
    CONSTRAINT Fotografia_pk PRIMARY KEY(idFotografia),
    CONSTRAINT Fotografia_id_categoria_fk FOREIGN KEY(idCategoria)
                       REFERENCES CategoriaFotografica(idCategoria)
);

CREATE TABLE Concurso(
    idConcurso BIGINT NOT NULL AUTO_INCREMENT,
    nombreConcurso VARCHAR(50),
    descripcionConcurso VARCHAR(500),
    estadoConcurso INT,
    tipoAccesoConcurso INT,
    tipoVotanteConcurso INT,
    tipoVotoConcurso INT,
    velocidadObturacion VARCHAR(50),
    fotoConcurso MEDIUMBLOB,
    categoriaUnica TINYINT,
    maxFotosUsuario INT,
    maxVotosUsuario INT,
    numGanadores INT,
    tituloReq TINYINT,
    descReq TINYINT,
    datosExifReq TINYINT,
    locReq TINYINT,
    ocultarFotos TINYINT,
    moderacion TINYINT,
    formato INT,
    ocultarVotos TINYINT,
    mostrarGanadoras TINYINT,
    fechaInicioConcurso DATETIME,
    fechaInicioVotacion DATETIME,
    descVotacion VARCHAR(500),
    fechaFinConcurso DATETIME,
    basesConcurso MEDIUMBLOB,
    CONSTRAINT Concurso_fk PRIMARY KEY(idConcurso),
    CONSTRAINT Nombre_concurso_unique UNIQUE(nombreConcurso)
);

CREATE TABLE UsuarioGustaCategoria(
    idUsuarioGustaCategoria BIGINT NOT NULL AUTO_INCREMENT,
    idUsuario BIGINT NOT NULL,
    idCategoria BIGINT NOT NULL,
    CONSTRAINT Usuario_gusta_categoria_pk PRIMARY KEY(idUsuarioGustaCategoria),
    CONSTRAINT Usuario_gusta_categoria_id_usuario_fk
        FOREIGN KEY(idUsuario)
            REFERENCES Usuario(idUsuario),
    CONSTRAINT Usuario_gusta_categoria_id_categoria_fk
        FOREIGN KEY(idCategoria)
            REFERENCES CategoriaFotografica(idCategoria)
);

CREATE TABLE UsuarioSigueUsuario(
    idUsuarioSigueUsuario BIGINT NOT NULL AUTO_INCREMENT,
    idUsuarioSeguidor BIGINT NOT NULL,
    idUsuarioSeguido BIGINT NOT NULL,
    fechaSeguida DATETIME,
    CONSTRAINT Usuario_sigue_usuario_pk PRIMARY KEY(idUsuarioSigueUsuario),
    CONSTRAINT Usuario_sigue_usuario_id_usuario_seguidor_fk
        FOREIGN KEY(idUsuarioSeguidor)
            REFERENCES Usuario(idUsuario),
    CONSTRAINT Usuario_sigue_usuario_id_usuario_seguido_fk
        FOREIGN KEY(idUsuarioSeguido)
            REFERENCES Usuario(idUsuario)
);

CREATE TABLE ConcursoPermiteCategoria(
    idConcurso BIGINT NOT NULL,
    idCategoria BIGINT NOT NULL,
    CONSTRAINT Concurso_permite_categoria_pk
        PRIMARY KEY(idConcurso, idCategoria),
    CONSTRAINT Concurso_permite_categoria_id_concurso_fk
        FOREIGN KEY(idConcurso)
            REFERENCES Concurso(idConcurso),
    CONSTRAINT Concurso_permite_categoria_id_categoria_fk
        FOREIGN KEY(idCategoria)
            REFERENCES CategoriaFotografica(idCategoria)
);

CREATE TABLE UsuarioParticipaConcurso(
    idUsuario BIGINT NOT NULL,
    idConcurso BIGINT NOT NULL,
    rol INT,
    fechaInicioParticipacion DATETIME,
    CONSTRAINT Usuario_participa_concurso_pk
        PRIMARY KEY(idUsuario, idConcurso),
    CONSTRAINT Usuario_participa_concurso_id_usuario_fk
        FOREIGN KEY(idUsuario)
            REFERENCES Usuario(idUsuario),
    CONSTRAINT Usuario_participa_concurso_id_concurso_fk
       FOREIGN KEY(idConcurso)
           REFERENCES Concurso(idConcurso)
);

CREATE TABLE UsuarioVotaFotografia(
   idUsuario BIGINT NOT NULL,
   idFotografia BIGINT NOT NULL,
   puntuacion INT,
   fechaVoto DATETIME,
   CONSTRAINT Usuario_vota_fotografia_pk PRIMARY KEY(idUsuario, idFotografia),
   CONSTRAINT Usuario_vota_fotografia_id_usuario_fk
       FOREIGN KEY(idUsuario)
           REFERENCES Usuario(idUsuario),
   CONSTRAINT Usuario_vota_fotografia_id_fotografia
       FOREIGN KEY(idFotografia)
           REFERENCES Fotografia(idFotografia)
);