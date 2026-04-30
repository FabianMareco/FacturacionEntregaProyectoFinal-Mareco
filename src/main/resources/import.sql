-- Limpiar tablas
DELETE FROM lineas_comprobante;
DELETE FROM comprobantes;
DELETE FROM productos;
DELETE FROM clientes;

-- Reiniciar secuencias (H2)
ALTER TABLE clientes ALTER COLUMN cliente_id RESTART WITH 1;
ALTER TABLE productos ALTER COLUMN producto_id RESTART WITH 1;
ALTER TABLE comprobantes ALTER COLUMN comprobante_id RESTART WITH 1;
ALTER TABLE lineas_comprobante ALTER COLUMN linea_id RESTART WITH 1;

-- Insertar clientes
INSERT INTO clientes (nombre, email) VALUES ('Fabian Mareco', 'fabian@muevete.com');
INSERT INTO clientes (nombre, email) VALUES ('Ana López', 'ana@example.com');
INSERT INTO clientes (nombre, email) VALUES ('Carlos Ruiz', 'carlos.ruiz@mail.com');
INSERT INTO clientes (nombre, email) VALUES ('María González', 'maria.g@mail.com');
INSERT INTO clientes (nombre, email) VALUES ('Lucía Fernández', 'lucia.f@mail.com');

-- Insertar productos de clases (cada clase individual $9999)
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Danza Jazz', 9999.00, 50);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Danza Clásica', 9999.00, 40);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Danza Contemporánea', 9999.00, 45);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Yoga', 9999.00, 60);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Tango', 9999.00, 30);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Estilos Urbanos', 9999.00, 35);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Flexibilidad y Elongación', 9999.00, 55);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Expresión Corporal', 9999.00, 40);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Danzas Folclóricas del Mundo', 9999.00, 25);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Pilates Mat', 9999.00, 50);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Flamenco', 9999.00, 20);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Movilidad Articular', 9999.00, 70);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Danza para Niños', 9999.00, 30);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Improvisación', 9999.00, 25);
INSERT INTO productos (nombre, precio, stock) VALUES ('Clase de Nutrición para el Movimiento', 9999.00, 40);

-- Insertar packs de clases (ya existentes)
INSERT INTO productos (nombre, precio, stock) VALUES ('Pack 4 Clases', 30000.00, 20);
INSERT INTO productos (nombre, precio, stock) VALUES ('Pack 8 Clases', 50000.00, 15);
INSERT INTO productos (nombre, precio, stock) VALUES ('Pase Mensual', 70000.00, 10);
INSERT INTO productos (nombre, precio, stock) VALUES ('Pase Anual', 650000.00, 5);

-- Insertar cursos de nutrición y packs promocionales
INSERT INTO productos (nombre, precio, stock) VALUES ('Curso de Nutrición Deportiva', 20000.00, 30);
INSERT INTO productos (nombre, precio, stock) VALUES ('Curso de Alimentación Saludable', 20000.00, 30);
INSERT INTO productos (nombre, precio, stock) VALUES ('Curso de Suplementación Deportiva', 20000.00, 30);
INSERT INTO productos (nombre, precio, stock) VALUES ('Pack 3 Cursos de Nutrición', 50000.00, 20);
INSERT INTO productos (nombre, precio, stock) VALUES ('Asesoría Nutricional Personalizada', 40000.00, 15);
INSERT INTO productos (nombre, precio, stock) VALUES ('Pack Cursos + Asesoría', 75000.00, 10);