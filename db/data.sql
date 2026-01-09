-- Datos de ejemplo (opcional) para pruebas manuales del MVP
USE atlas_biblioteca;

INSERT INTO libro (titulo, autor, isbn, anio_publicacion) VALUES
('El Quijote', 'Miguel de Cervantes', '9788491050294', 1605),
('Cien años de soledad', 'Gabriel García Márquez', '9780307474728', 1967);

INSERT INTO socio (nombre, apellidos, email) VALUES
('Ana', 'Pérez', 'ana.perez@example.com'),
('Luis', 'Gómez', 'luis.gomez@example.com');

-- préstamo activo: fecha_devolucion NULL
INSERT INTO prestamo (libro_id, socio_id, fecha_prestamo, fecha_devolucion) VALUES
(1, 1, CURDATE(), NULL);
