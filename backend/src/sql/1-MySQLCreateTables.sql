/******************************************************************************/
/*                                                                             *
/*          This script creates the tables needed for PhotoContest             *
/*                                                                             *
/******************************************************************************/

DROP TABLE Usuario_sigue_usuario;
DROP TABLE Usuario_participa_concurso;
DROP TABLE Usuario_vota_fotografia;
DROP TABLE Concurso_permite_categoria;
DROP TABLE Usuario_gusta_categoria;
DROP TABLE Notificacion;
DROP TABLE Fotografia;
DROP TABLE Categoria_fotografica;
DROP TABLE Usuario;
DROP TABLE Concurso;


CREATE TABLE Categoria_fotografica(
    id_categoria BIGINT NOT NULL AUTO_INCREMENT,
    nombre_categoria VARCHAR(50),
    descripcion_categoria VARCHAR(200),
    CONSTRAINT categoria_fotografica_pk PRIMARY KEY(id_categoria)
);

CREATE TABLE Usuario(
    id_usuario BIGINT NOT NULL AUTO_INCREMENT,
    nombre_usuario VARCHAR(50),
    nombre_pila_usuario VARCHAR(50),
    apellidos_usuario VARCHAR(100),
    biografia_usuario VARCHAR(500),
    correo_electronico_usuario VARCHAR(90),
    contrasena_usuairo VARCHAR(50),
    enlace_twitter VARCHAR(200),
    enlace_facebook VARCHAR(200),
    CONSTRAINT Usuario_pk PRIMARY KEY(id_usuario),
    CONSTRAINT Nombre_usuario_unique UNIQUE(nombre_usuario)
);

CREATE TABLE Notificacion(
    id_notificacion BIGINT NOT NULL AUTO_INCREMENT,
    nombre_notificacion VARCHAR(50),
    mensaje_notificacion VARCHAR(200),
    fecha_creacion datetime,
    id_usuario BIGINT NOT NULL,
    CONSTRAINT Notificacion_pk PRIMARY KEY(id_notificacion),
    CONSTRAINT Notificacion_id_usuario_fk FOREIGN KEY(id_usuario)
                         REFERENCES Usuario(id_usuario)
);

CREATE TABLE Fotografia(
    id_fotografia BIGINT NOT NULL AUTO_INCREMENT,
    titulo_fotografia VARCHAR(50),
    descripcion_fotografia VARCHAR(200),
    apertura_diafragma VARCHAR(50),
    fabricante_camara VARCHAR(50),
    modelo_camara VARCHAR(50),
    distancia_focal VARCHAR(50),
    velocidad_obturacion VARCHAR(50),
    iso VARCHAR(50),
    resolucion VARCHAR(50),
    datos_jpg MEDIUMBLOB,
    datos_raw MEDIUMBLOB,
    fecha_subida DATETIME,
    fecha_inicio_participacion DATETIME,
    estado_moderacion TINYINT,
    id_categoria BIGINT,
    CONSTRAINT Fotografia_pk PRIMARY KEY(id_fotografia),
    CONSTRAINT Fotografia_id_categoria_fk FOREIGN KEY(id_categoria)
                       REFERENCES Categoria_fotografica(id_categoria)
);

CREATE TABLE Concurso(
    id_concurso BIGINT NOT NULL AUTO_INCREMENT,
    nombre_concurso VARCHAR(50),
    descripcion_concurso VARCHAR(500),
    estado_concurso INT,
    tipo_acceso_concurso INT,
    tipo_votante_concurso INT,
    tipo_voto_concurso INT,
    velocidad_obturacion VARCHAR(50),
    tipo_participacion_concurso INT,
    foto_concurso MEDIUMBLOB,
    categoria_unica TINYINT,
    max_fotos_usuario INT,
    max_votos_usuario INT,
    num_ganadores INT,
    titulo_req TINYINT,
    desc_req TINYINT,
    datos_exif_req TINYINT,
    loc_req TINYINT,
    ocultar_fotos TINYINT,
    moderacion TINYINT,
    formato INT,
    ocultar_votos TINYINT,
    mostrar_ganadoras TINYINT,
    fecha_inicio_concurso DATETIME,
    fecha_inicio_votacion DATETIME,
    desc_votacion VARCHAR(500),
    fecha_fin_concurso DATETIME,
    bases_concurso MEDIUMBLOB,
    CONSTRAINT Concurso_fk PRIMARY KEY(id_concurso),
    CONSTRAINT Nombre_concurso_unique UNIQUE(nombre_concurso)
);

CREATE TABLE Usuario_gusta_categoria(
    id_usuario BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    CONSTRAINT Usuario_gusta_categoria_pk PRIMARY KEY(id_usuario, id_categoria),
    CONSTRAINT Usuario_gusta_categoria_id_usuario_fk
        FOREIGN KEY(id_usuario)
            REFERENCES Usuario(id_usuario),
    CONSTRAINT Usuario_gusta_categoria_id_categoria_fk
        FOREIGN KEY(id_categoria)
            REFERENCES Categoria_fotografica(id_categoria)
);

CREATE TABLE Usuario_sigue_usuario(
    id_usuario_seguidor BIGINT NOT NULL,
    id_usuario_seguido BIGINT NOT NULL,
    fecha_seguida DATETIME,
    CONSTRAINT Usuario_sigue_usuario_pk
        PRIMARY KEY(id_usuario_seguidor,id_usuario_seguido),
    CONSTRAINT Usuario_sigue_usuario_id_usuario_seguidor_fk
        FOREIGN KEY(id_usuario_seguidor)
            REFERENCES Usuario(id_usuario),
    CONSTRAINT Usuario_sigue_usuario_id_usuario_seguido_fk
        FOREIGN KEY(id_usuario_seguido)
            REFERENCES Usuario(id_usuario)
);

CREATE TABLE Concurso_permite_categoria(
    id_concurso BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    CONSTRAINT Concurso_permite_categoria_pk PRIMARY KEY(id_concurso, id_categoria),
    CONSTRAINT Concurso_permite_categoria_id_concurso_fk
        FOREIGN KEY(id_concurso)
            REFERENCES Concurso(id_concurso),
    CONSTRAINT Concurso_permite_categoria_id_categoria_fk
        FOREIGN KEY(id_categoria)
            REFERENCES Categoria_fotografica(id_categoria)
);

CREATE TABLE Usuario_participa_concurso(
    id_usuario BIGINT NOT NULL,
    id_concurso BIGINT NOT NULL,
    rol INT,
    fecha_inicio_participacion DATETIME,
    CONSTRAINT Usuario_participa_concurso_pk PRIMARY KEY(id_usuario, id_concurso),
    CONSTRAINT Usuario_participa_concurso_id_usuario_fk
        FOREIGN KEY(id_usuario)
            REFERENCES Usuario(id_usuario),
    CONSTRAINT Usuario_participa_concurso_id_concurso_fk
       FOREIGN KEY(id_concurso)
           REFERENCES Concurso(id_concurso)
);

CREATE TABLE Usuario_vota_fotografia(
   id_usuario BIGINT NOT NULL,
   id_fotografia BIGINT NOT NULL,
   puntuacion INT,
   fecha_voto DATETIME,
   CONSTRAINT Usuario_vota_fotografia_pk PRIMARY KEY(id_usuario, id_fotografia),
   CONSTRAINT Usuario_vota_fotografia_id_usuario_fk
       FOREIGN KEY(id_usuario)
           REFERENCES Usuario(id_usuario),
   CONSTRAINT Usuario_vota_fotografia_id_fotografia
       FOREIGN KEY(id_fotografia)
           REFERENCES Fotografia(id_fotografia)
);
