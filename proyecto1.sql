DROP DATABASE IF EXISTS bibliotecadb;

CREATE DATABASE bibliotecadb;
USE bibliotecadb;

CREATE TABLE usuario (
id INT NOT NULL,
nombre VARCHAR(60) NOT NULL,
username VARCHAR(60) NOT NULL,
password VARCHAR(60) NOT NULL,
email VARCHAR(60) NOT NULL,
CONSTRAINT PK_USUARIO PRIMARY KEY (id)
);

CREATE TABLE administrador (
id INT NOT NULL,
CONSTRAINT PK_ADMINISTRADOR PRIMARY KEY (id),
CONSTRAINT FK_ADMINISTRADOR_USUARIO_ID FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE transportista (
id INT NOT NULL,
CONSTRAINT PK_TRANSPORTISTA PRIMARY KEY (id),
CONSTRAINT FK_TRANSPORTISTA_USUARIO_ID FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE usuario_final (
id INT NOT NULL,
saldo DECIMAL(6 , 2 ) NOT NULL,
CONSTRAINT PK_USUARIO_FINAL PRIMARY KEY (id),
CONSTRAINT FK_USUARIO_FINAL_USUARIO_ID FOREIGN KEY (id) REFERENCES usuario(id)
);

CREATE TABLE biblioteca (
id INT NOT NULL,
nombre VARCHAR(60) NOT NULL,
direccion VARCHAR(60) NOT NULL,
CONSTRAINT PK_BIBLIOTECA PRIMARY KEY (id)
);

CREATE TABLE recepcionista (
id INT NOT NULL,
biblioteca_id INT NOT NULL,
CONSTRAINT PK_RECEPCIONISTA PRIMARY KEY (id),
CONSTRAINT FK_RECEPCIONISTA_USUARIO_ID FOREIGN KEY (id) REFERENCES usuario(id),
CONSTRAINT FK_RECEPCIONISTA_BIBLIOTECA_ID FOREIGN KEY (biblioteca_id) REFERENCES biblioteca(id)
);

CREATE TABLE categoria (
id INT NOT NULL,
nombre VARCHAR(60),
descripcion VARCHAR(60),
CONSTRAINT PK_CATEGORIA PRIMARY KEY (id)
);

CREATE TABLE libro (
isbn INT NOT NULL,
nombre VARCHAR(60) NOT NULL,
costo DECIMAL(6 , 2 ) NOT NULL,
autor VARCHAR(60) NOT NULL,
categoria_id INT NOT NULL,
CONSTRAINT PK_LIBRO PRIMARY KEY (isbn),
CONSTRAINT FK_LIBRO_CATEGORIA_ID FOREIGN KEY (categoria_id) REFERENCES categoria(id)
);

-- 'DISPONIBLE' 'NODISPONIBLE'
-- CREATE TABLE estado_libro (
-- id INT AUTO_INCREMENT NOT NULL,
-- nombre VARCHAR(60) NOT NULL,
-- CONSTRAINT PK_ESTADO_LIBRO PRIMARY KEY (id)
-- );

CREATE TABLE copia_libro (
id INT AUTO_INCREMENT NOT NULL,
-- estado_libro_id INT NOT NULL,
biblioteca_id INT NOT NULL,
libro_isbn INT NOT NULL,
CONSTRAINT PK_COPIA_LIBRO PRIMARY KEY (id),
-- CONSTRAINT FK_COPIA_LIBRO_ESTADO_LIBRO_ID FOREIGN KEY (estado_libro_id) REFERENCES estado_libro(id),
CONSTRAINT FK_COPIA_LIBRO_BIBLIOTECA_ID FOREIGN KEY (biblioteca_id) REFERENCES biblioteca(id),
CONSTRAINT FK_COPIA_LIBRO_LIBRO_ISBN FOREIGN KEY (libro_isbn) REFERENCES libro(isbn)
);

-- 'PENDIENTE' 'APROBADO'
CREATE TABLE estado_solicitud_prestamo (
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_SOLICITUD_PRESTAMO PRIMARY KEY (id)
);
INSERT INTO estado_solicitud_prestamo (nombre) VALUES ('PENDIENTE'); -- 1
INSERT INTO estado_solicitud_prestamo (nombre) VALUES ('APROBADO'); -- 2

-- 'DIMICILIO' 'PRESENCIAL'
CREATE TABLE tipo_entrega (
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_TIPO_ENTREGA PRIMARY KEY (id)
);
INSERT INTO tipo_entrega (nombre) VALUES ('DOMICILIO'); -- 1
INSERT INTO tipo_entrega (nombre) VALUES ('PRESENCIAL'); -- 2

CREATE TABLE solicitud_prestamo (
id INT NOT NULL,
fecha DATE NOT NULL,
estado_solicitud_prestamo_id INT NOT NULL,
tipo_entrega_id INT NOT NULL,
biblioteca_id INT NOT NULL,
recepcionista_id INT NOT NULL,
usuario_final_id INT NOT NULL,
libro_isbn INT NOT NULL,
transportista_id INT NOT NULL,
CONSTRAINT PK_SOLICITUD_PRESTAMO PRIMARY KEY (id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_ESTADO_SOLICITUD_ID FOREIGN KEY (estado_solicitud_prestamo_id) REFERENCES estado_solicitud_prestamo(id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_TIPO_ENTREGA_ID FOREIGN KEY (tipo_entrega_id) REFERENCES tipo_entrega(id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_BIBLIOTECA_ID FOREIGN KEY (biblioteca_id) REFERENCES biblioteca(id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_RECEPCIONISTA_ID FOREIGN KEY (recepcionista_id) REFERENCES recepcionista(id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_USUARIO_FINAL_ID FOREIGN KEY (usuario_final_id) REFERENCES usuario_final(id),
CONSTRAINT FK_SOLICITUD_PRESTAMO_LIBRO_ISBN FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
CONSTRAINT FK_SOLICITUD_PRESTAMO_TRANSPORTISTA_ID FOREIGN KEY (transportista_id) REFERENCES transportista(id)
);

-- 'ACTIVO' 'FINALIZADO'
CREATE TABLE estado_prestamo (
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_PRESTAMO PRIMARY KEY (id)
);
INSERT INTO estado_prestamo (nombre) VALUES ('ACTIVO'); -- 1
INSERT INTO estado_prestamo (nombre) VALUES ('FINALIZADO'); -- 2

CREATE TABLE prestamo (
id INT NOT NULL,
fecha DATE NOT NULL,
multa INT NOT NULL,
estado_prestamo_id INT NOT NULL,
biblioteca_id INT NOT NULL,
recepcionista_id INT NOT NULL,
usuario_final_id INT NOT NULL,
libro_isbn INT NOT NULL,
CONSTRAINT PK_PRESTAMO PRIMARY KEY (id), 
CONSTRAINT FK_PRESTAMO_ESTADO_PRESTAMO_ID FOREIGN KEY (estado_prestamo_id) REFERENCES estado_prestamo(id),
CONSTRAINT FK_PRESTAMO_BIBLIOTECA_ID FOREIGN KEY (biblioteca_id) REFERENCES biblioteca(id),
CONSTRAINT FK_PRESTAMO_RECEPCIONISTA_ID FOREIGN KEY (recepcionista_id) REFERENCES recepcionista(id),
CONSTRAINT FK_PRESTAMO_USUARIO_FINAL_ID FOREIGN KEY (usuario_final_id) REFERENCES usuario_final(id),
CONSTRAINT FK_PRESTAMO_LIBRO_ISBN FOREIGN KEY (libro_isbn) REFERENCES libro(isbn)
);

-- 'PENDIENTE' 'ENTREGADO'
CREATE TABLE estado_transporte_biblioteca(
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_TRANSPORTE_BIBLIOTECA PRIMARY KEY (id)
);
INSERT INTO estado_transporte_biblioteca (nombre) VALUES('PENDIENTE'); -- 1 
INSERT INTO estado_transporte_biblioteca (nombre) VALUES('ENTREGADO'); -- 2

CREATE TABLE transporte_biblioteca (
id INT NOT NULL,
fecha DATE NOT NULL,
estado_transporte_biblioteca_id INT NOT NULL,
biblioteca_origen_id INT NOT NULL,
biblioteca_destino_id INT NOT NULL,
recepcionista_id INT NOT NULL,
transportista_id INT NOT NULL,
CONSTRAINT PK_TRANSPORTE_BIBLIOTECA PRIMARY KEY (id),
CONSTRAINT FK_TRANSPORTE_BIBLIOTECA_ESTADO_TRANSPORTE_ID FOREIGN KEY (estado_transporte_biblioteca_id) REFERENCES estado_transporte_biblioteca(id),
CONSTRAINT FK_TRANSPORTE_BIBLIOTECA_BIBLIOTECA_ORIGEN_ID FOREIGN KEY (biblioteca_origen_id) REFERENCES biblioteca(id),
CONSTRAINT FK_TRANSPORTE_BIBLIOTECA_BIBLIOTECA_DESTINO_ID FOREIGN KEY (biblioteca_destino_id) REFERENCES biblioteca(id),
CONSTRAINT FK_TRANSPORTE_BIBLIOTECA_RECEPCIONISTA_ID FOREIGN KEY (recepcionista_id) REFERENCES recepcionista(id),
CONSTRAINT FK_TRANSPORTE_BIBLIOTECA_TRANSPORTISTA_ID FOREIGN KEY (transportista_id) REFERENCES transportista(id)
);

CREATE TABLE detalle_transporte_biblioteca (
id INT AUTO_INCREMENT NOT NULL,
libro_isbn INT NOT NULL,
transporte_biblioteca_id INT NOT NULL,
CONSTRAINT PK_DETALLE_TRANSPORTE_BIBLIOTECA PRIMARY KEY (id),
CONSTRAINT FK_DETALLE_TRANSPORTE_BIBLIOTECA_LIBRO_ISBN FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
CONSTRAINT FK_DETALLE_TRANSPORTE_BIBLIOTECA_TRANSPORTE_ID FOREIGN KEY (transporte_biblioteca_id) REFERENCES transporte_biblioteca(id)
);

-- 'PENDIENTE' 'ENTREGADO'
CREATE TABLE estado_transporte_usuario(
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_TRANSPORTE_USUARIO PRIMARY KEY (id)
);
INSERT INTO estado_transporte_usuario (nombre) VALUES ('PENDIENTE'); -- 1
INSERT INTO estado_transporte_usuario (nombre) VALUES ('ENTREGADO'); -- 2

CREATE TABLE transporte_usuario (
id INT NOT NULL,
fecha DATE NOT NULL,
estado_transporte_usuario_id INT NOT NULL,
libro_isbn INT NOT NULL,
biblioteca_id INT NOT NULL,
usuario_final_id INT NOT NULL,
transportista_id INT NOT NULL,
CONSTRAINT PK_TRANSPORTE_USUARIO PRIMARY KEY (id),
CONSTRAINT FK_TRANSPORTE_USUARIO_ESTADO_TRANSPORTE_ID FOREIGN KEY (estado_transporte_usuario_id) REFERENCES estado_transporte_usuario(id),
CONSTRAINT FK_TRANSPORTE_USUARIO_LIBRO_ISBN FOREIGN KEY (libro_isbn) REFERENCES libro(isbn),
CONSTRAINT FK_TRANSPORTE_USUARIO_BIBLIOTECA_ID FOREIGN KEY (biblioteca_id) REFERENCES biblioteca(id),
CONSTRAINT FK_TRANSPORTE_USUARIO_USUARIO_FINAL_ID FOREIGN KEY (usuario_final_id) REFERENCES usuario_final(id),
CONSTRAINT FK_TRANSPORTE_USUARIO_TRANSPORTISTA_ID FOREIGN KEY (transportista_id) REFERENCES transportista(id)
);

-- 'PERDIDA' 'DETERIORO'
CREATE TABLE tipo_incidencia (
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_INCIDENCIA PRIMARY KEY (id)
);
INSERT INTO tipo_incidencia (nombre) VALUES ('PERDIDA'); -- 1
INSERT INTO tipo_incidencia (nombre) VALUES ('DETERIORO'); -- 2

CREATE TABLE incidencia (
id INT NOT NULL,
pago INT NOT NULL,
tipo_incidencia_id INT NOT NULL,
prestamo_id INT NOT NULL,
CONSTRAINT PK_INCIDENCIA PRIMARY KEY (id),
CONSTRAINT FK_INCIDENCIA_TIPO_INCIDENCIA_ID FOREIGN KEY (tipo_incidencia_id) REFERENCES tipo_incidencia(id),
CONSTRAINT FK_INCIDENCIA_PRESTAMO_ID FOREIGN KEY (prestamo_id) REFERENCES prestamo(id)
);

-- 'PENDIENTE' 'APROBADA'
CREATE TABLE estado_revocacion (
id INT AUTO_INCREMENT NOT NULL,
nombre VARCHAR(60) NOT NULL,
CONSTRAINT PK_ESTADO_REVOCACION PRIMARY KEY (id)
);
INSERT INTO estado_revocacion (nombre) VALUES ('PENDIENTE'); -- 1
INSERT INTO estado_revocacion (nombre) VALUES ('APROBADA'); -- 2

CREATE TABLE solicitud_revocacion_suspension (
id INT NOT NULL,
descripcion VARCHAR(280),
estado_revocacion_id INT NOT NULL,
usuario_final_id INT NOT NULL,
CONSTRAINT PK_SOLICITUD_REVOCACION_SUSPENSION PRIMARY KEY (id),
CONSTRAINT FK_SOLICITUD_REVOCACION_SUSPENSION_ESTADO_ID FOREIGN KEY (estado_revocacion_id) REFERENCES estado_revocacion(id),
CONSTRAINT FK_SOLICITUD_REVOCACION_SUSPENSION_USUARIO_FINAL_ID FOREIGN KEY (usuario_final_id) REFERENCES usuario_final(id)
);



-- USUARIOS DE ADMINISTRACION
-- INSERT INTO usuario (id,nombre,username,password,email) VALUES ('1','Administrador 1','admin1','123','admin1@gmail.com');
-- INSERT INTO administrador (id) VALUES ('1');

-- USUARIOS DE RECEPCION
-- INSERT INTO usuario (id,nombre,username,password,email) VALUES ('2','Recepcion 1','recepcion1','123','recepcion1@gmail.com');
-- INSERT INTO recepcionista (id,biblioteca_id) VALUES ('2','1');

-- USUARIOS DE TRANSPORTE
-- INSERT INTO usuario (id,nombre,username,password,email) VALUES ('3','Transporte 1','transporte1','123','transporte1@gmail.com');
-- INSERT INTO transportista (id) VALUES ('3');

-- USUARIOS FINALES
-- INSERT INTO usuario (id,nombre,username,password,email) VALUES ('4','Usuario 1','user1','123','user1@gmail.com');
-- INSERT INTO usuario_final (id,saldo) VALUES ('4','250.25');

-- CATEGORIAS
-- INSERT INTO categoria (id,nombre,descripcion) VALUES ('4','Categoria 1','Descripcion categoria 1');

-- LIBROS
-- INSERT INTO libro (isbn,nombre,costo,categoria_id,autor) VALUES ('1','Libro 1','11.44','4','Autor 1');
-- INSERT INTO libro (isbn,nombre,costo,categoria_id,autor) VALUES ('2','Libro 2','12.44','4','Autor 2');
-- INSERT INTO libro (isbn,nombre,costo,categoria_id,autor) VALUES ('3','Libro 3','13.44','4','Autor 3');

-- BIBLIOTECAS
-- INSERT INTO biblioteca (id,nombre,direccion) VALUES ('1','Biblioteca 1', 'Direccion 1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','1');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','1');

-- INSERT INTO biblioteca (id,nombre,direccion) VALUES ('2','Biblioteca 2', 'Direccion 2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','2');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','2');
    
-- INSERT INTO biblioteca (id,nombre,direccion) VALUES ('3','Biblioteca 3', 'Direccion 3');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('1','3');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('2','3');
	-- INSERT INTO copia_libro (libro_isbn,biblioteca_id) VALUES ('3','3');


-- SOLICITUDES DE PRESTAMO
/*
INSERT INTO solicitud_prestamo (id, biblioteca_id, recepcionista_id, usuario_final_id, libro_isbn, fecha, estado_solicitud_prestamo_id, tipo_entrega_id, transportista_id)
						VALUES ('1','3','2','4','3','2023-08-07','1','1','3');
*/

-- PRESTAMO
/*
INSERT INTO prestamo (id, biblioteca_id, recepcionista_id, usuario_final_id, libro_isbn, fecha, estado_prestamo_id, multa)
				VALUES ('1','3','2','4','2','2023-08-07','1','0');
*/

-- TRANSPORTE BIBLIOTECA
/*
INSERT INTO transporte_biblioteca (id, biblioteca_origen_id, biblioteca_destino_id, recepcionista_id, transportista_id, fecha, estado_transporte_biblioteca_id)
							VALUES('1','1','2','2','3','2023-08-07','1');

INSERT INTO detalle_transporte_biblioteca (libro_isbn, transporte_biblioteca_id) VALUES ('1','1');
INSERT INTO detalle_transporte_biblioteca (libro_isbn, transporte_biblioteca_id) VALUES ('2','1');
INSERT INTO detalle_transporte_biblioteca (libro_isbn, transporte_biblioteca_id) VALUES ('3','1');
*/

-- TRANSPORTE USUARIO
/*
INSERT INTO transporte_usuario (id, biblioteca_id, usuario_final_id, transportista_id, fecha, estado_transporte_usuario_id, libro_isbn)
						VALUES ('1','3','4','3','2023-08-07','1','1');
*/

-- INCIDENCIA
-- INSERT INTO incidencia (id, prestamo_id, tipo_incidencia_id, pago) VALUES ('1','1','1','50.75');

-- SOLICITUD DE RENOVACION DE SUSPENSION
/*
INSERT INTO solicitud_revocacion_suspension (id, usuario_final_id, estado_revocacion_id, descripcion)
									VALUES ('1','4','1','Favor de revocar la suspension ya que cancele el libro dañado');
*/

