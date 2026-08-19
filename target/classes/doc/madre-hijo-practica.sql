-- ============================================================
-- Script de practica: Madre - Hijo
-- Relacion: una madre tiene muchos hijos
-- Base de datos: PostgreSQL
-- ============================================================

DROP TABLE IF EXISTS app_hijo;
DROP TABLE IF EXISTS app_madre;

CREATE TABLE app_madre (
    cim VARCHAR(20) PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    ap VARCHAR(100) NOT NULL,
    am VARCHAR(100) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE app_hijo (
    cih VARCHAR(20) PRIMARY KEY,
    cim VARCHAR(20) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    ap VARCHAR(100) NOT NULL,
    am VARCHAR(100) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_app_hijo_madre
        FOREIGN KEY (cim)
        REFERENCES app_madre(cim)
        ON DELETE CASCADE
);

CREATE INDEX idx_app_hijo_cim ON app_hijo(cim);

-- ============================================================
-- Datos de ejemplo: 10 madres
-- ============================================================

INSERT INTO app_madre (cim, nombre, ap, am) VALUES
('7001001', 'Maria', 'Lopez', 'Vargas'),
('7001002', 'Ana', 'Rojas', 'Mamani'),
('7001003', 'Carmen', 'Torrez', 'Quispe'),
('7001004', 'Rosa', 'Fernandez', 'Arias'),
('7001005', 'Patricia', 'Gutierrez', 'Flores'),
('7001006', 'Claudia', 'Morales', 'Castro'),
('7001007', 'Veronica', 'Salazar', 'Mendez'),
('7001008', 'Gabriela', 'Rivera', 'Soto'),
('7001009', 'Elena', 'Aguilar', 'Paz'),
('7001010', 'Lucia', 'Camacho', 'Ramos');

-- ============================================================
-- Datos de ejemplo: hijos relacionados por cim
-- ============================================================

INSERT INTO app_hijo (cih, cim, nombre, ap, am) VALUES
('8001001', '7001001', 'Carlos', 'Lopez', 'Vargas'),
('8001002', '7001001', 'Daniela', 'Lopez', 'Vargas'),
('8001003', '7001001', 'Luis', 'Lopez', 'Vargas'),

('8001004', '7001002', 'Fernando', 'Rojas', 'Mamani'),
('8001005', '7001002', 'Camila', 'Rojas', 'Mamani'),

('8001006', '7001003', 'Jorge', 'Torrez', 'Quispe'),
('8001007', '7001003', 'Andrea', 'Torrez', 'Quispe'),
('8001008', '7001003', 'Mateo', 'Torrez', 'Quispe'),

('8001009', '7001004', 'Sofia', 'Fernandez', 'Arias'),
('8001010', '7001004', 'Miguel', 'Fernandez', 'Arias'),

('8001011', '7001005', 'Diego', 'Gutierrez', 'Flores'),
('8001012', '7001005', 'Valeria', 'Gutierrez', 'Flores'),
('8001013', '7001005', 'Nicolas', 'Gutierrez', 'Flores'),

('8001014', '7001006', 'Paola', 'Morales', 'Castro'),
('8001015', '7001006', 'Samuel', 'Morales', 'Castro'),

('8001016', '7001007', 'Martin', 'Salazar', 'Mendez'),
('8001017', '7001007', 'Alejandra', 'Salazar', 'Mendez'),
('8001018', '7001007', 'Rodrigo', 'Salazar', 'Mendez'),

('8001019', '7001008', 'Sebastian', 'Rivera', 'Soto'),
('8001020', '7001008', 'Luciana', 'Rivera', 'Soto'),

('8001021', '7001009', 'Emilia', 'Aguilar', 'Paz'),
('8001022', '7001009', 'Tomas', 'Aguilar', 'Paz'),
('8001023', '7001009', 'Rafael', 'Aguilar', 'Paz'),

('8001024', '7001010', 'Natalia', 'Camacho', 'Ramos'),
('8001025', '7001010', 'Lucas', 'Camacho', 'Ramos');
