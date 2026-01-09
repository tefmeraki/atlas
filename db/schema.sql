-- ATLAS · Biblioteca · Iteración 0 (BD)
-- Script compatible con MySQL 8+
-- Crea esquema mínimo para el MVP: libro, socio, prestamo

CREATE DATABASE IF NOT EXISTS atlas_biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE atlas_biblioteca;

-- Tabla: libro
CREATE TABLE IF NOT EXISTS libro (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(255) NOT NULL,
  autor VARCHAR(255) NOT NULL,
  isbn VARCHAR(20) NULL,
  anio_publicacion INT NULL,
  CONSTRAINT uk_libro_isbn UNIQUE (isbn)
) ENGINE=InnoDB;

-- Tabla: socio
CREATE TABLE IF NOT EXISTS socio (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(120) NOT NULL,
  apellidos VARCHAR(200) NOT NULL,
  email VARCHAR(255) NOT NULL,
  CONSTRAINT uk_socio_email UNIQUE (email)
) ENGINE=InnoDB;

-- Tabla: prestamo
-- Regla de negocio (a implementar en servicio): un libro no puede tener 2 préstamos activos (fecha_devolucion IS NULL).
CREATE TABLE IF NOT EXISTS prestamo (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  libro_id BIGINT NOT NULL,
  socio_id BIGINT NOT NULL,
  fecha_prestamo DATE NOT NULL,
  fecha_devolucion DATE NULL,
  CONSTRAINT fk_prestamo_libro FOREIGN KEY (libro_id) REFERENCES libro(id),
  CONSTRAINT fk_prestamo_socio FOREIGN KEY (socio_id) REFERENCES socio(id),
  INDEX idx_prestamo_libro (libro_id),
  INDEX idx_prestamo_socio (socio_id),
  INDEX idx_prestamo_activo (libro_id, fecha_devolucion)
) ENGINE=InnoDB;
