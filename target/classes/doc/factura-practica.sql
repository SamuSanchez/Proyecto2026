-- Script de practica para el modulo de facturas.
-- Base de datos: PostgreSQL
-- Este script crea dos tablas: app_factura y app_factura_detalle.
-- Para una practica limpia, elimina los datos anteriores de estas tablas.

CREATE TABLE IF NOT EXISTS app_factura (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(120) NOT NULL,
    nit VARCHAR(30) NOT NULL,
    total NUMERIC(12, 2) NOT NULL DEFAULT 0,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP
);

CREATE TABLE IF NOT EXISTS app_factura_detalle (
    id BIGSERIAL PRIMARY KEY,
    factura_id BIGINT NOT NULL,
    producto VARCHAR(150) NOT NULL,
    precio NUMERIC(12, 2) NOT NULL,
    cantidad INTEGER NOT NULL,
    subtotal NUMERIC(12, 2) NOT NULL,
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    update_time TIMESTAMP,
    CONSTRAINT fk_app_factura_detalle_factura
        FOREIGN KEY (factura_id)
        REFERENCES app_factura(id)
        ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_app_factura_detalle_factura_id
ON app_factura_detalle(factura_id);

TRUNCATE TABLE app_factura_detalle, app_factura RESTART IDENTITY CASCADE;

INSERT INTO app_factura (id, nombre, nit, total, create_time) VALUES
(1, 'Juan Perez', '1234567', 114.50, CURRENT_TIMESTAMP),
(2, 'Maria Lopez', '7654321', 213.00, CURRENT_TIMESTAMP),
(3, 'Carlos Rojas', '4567890', 180.00, CURRENT_TIMESTAMP),
(4, 'Ana Gutierrez', '9876543', 324.50, CURRENT_TIMESTAMP),
(5, 'Luis Fernandez', '1122334', 151.00, CURRENT_TIMESTAMP),
(6, 'Gabriela Vargas', '5566778', 278.00, CURRENT_TIMESTAMP),
(7, 'Pedro Salazar', '9988776', 126.00, CURRENT_TIMESTAMP),
(8, 'Lucia Medina', '4433221', 367.50, CURRENT_TIMESTAMP),
(9, 'Miguel Arias', '3344556', 205.00, CURRENT_TIMESTAMP),
(10, 'Rosa Castro', '2211445', 302.50, CURRENT_TIMESTAMP);

INSERT INTO app_factura_detalle (factura_id, producto, precio, cantidad, subtotal, create_time) VALUES
-- Factura 1
(1, 'Arroz 1 kg', 8.50, 2, 17.00, CURRENT_TIMESTAMP),
(1, 'Aceite 1 L', 14.00, 1, 14.00, CURRENT_TIMESTAMP),
(1, 'Azucar 1 kg', 7.00, 3, 21.00, CURRENT_TIMESTAMP),
(1, 'Fideo 500 g', 6.50, 4, 26.00, CURRENT_TIMESTAMP),
(1, 'Leche 1 L', 9.00, 2, 18.00, CURRENT_TIMESTAMP),
(1, 'Pan integral', 18.50, 1, 18.50, CURRENT_TIMESTAMP),

-- Factura 2
(2, 'Carne de res 1 kg', 45.00, 2, 90.00, CURRENT_TIMESTAMP),
(2, 'Papa 1 kg', 5.00, 4, 20.00, CURRENT_TIMESTAMP),
(2, 'Tomate 1 kg', 8.00, 2, 16.00, CURRENT_TIMESTAMP),
(2, 'Cebolla 1 kg', 6.00, 3, 18.00, CURRENT_TIMESTAMP),
(2, 'Queso criollo', 24.00, 2, 48.00, CURRENT_TIMESTAMP),
(2, 'Refresco 2 L', 21.00, 1, 21.00, CURRENT_TIMESTAMP),

-- Factura 3
(3, 'Pollo entero', 38.00, 2, 76.00, CURRENT_TIMESTAMP),
(3, 'Lechuga', 4.00, 3, 12.00, CURRENT_TIMESTAMP),
(3, 'Zanahoria 1 kg', 7.00, 2, 14.00, CURRENT_TIMESTAMP),
(3, 'Mayonesa', 16.00, 1, 16.00, CURRENT_TIMESTAMP),
(3, 'Arveja 500 g', 12.00, 2, 24.00, CURRENT_TIMESTAMP),
(3, 'Jugo natural', 19.00, 2, 38.00, CURRENT_TIMESTAMP),

-- Factura 4
(4, 'Harina 1 kg', 7.50, 4, 30.00, CURRENT_TIMESTAMP),
(4, 'Huevos docena', 18.00, 3, 54.00, CURRENT_TIMESTAMP),
(4, 'Mantequilla', 22.50, 2, 45.00, CURRENT_TIMESTAMP),
(4, 'Chocolate barra', 16.00, 3, 48.00, CURRENT_TIMESTAMP),
(4, 'Crema de leche', 19.50, 5, 97.50, CURRENT_TIMESTAMP),
(4, 'Frutilla 1 kg', 25.00, 2, 50.00, CURRENT_TIMESTAMP),

-- Factura 5
(5, 'Detergente', 32.00, 1, 32.00, CURRENT_TIMESTAMP),
(5, 'Jabon liquido', 18.00, 2, 36.00, CURRENT_TIMESTAMP),
(5, 'Lavandina', 10.00, 2, 20.00, CURRENT_TIMESTAMP),
(5, 'Esponja', 4.50, 4, 18.00, CURRENT_TIMESTAMP),
(5, 'Papel higienico', 30.00, 1, 30.00, CURRENT_TIMESTAMP),
(5, 'Ambientador', 15.00, 1, 15.00, CURRENT_TIMESTAMP),

-- Factura 6
(6, 'Cafe molido', 28.00, 2, 56.00, CURRENT_TIMESTAMP),
(6, 'Te de manzanilla', 14.00, 3, 42.00, CURRENT_TIMESTAMP),
(6, 'Galletas', 12.00, 4, 48.00, CURRENT_TIMESTAMP),
(6, 'Mermelada', 19.00, 2, 38.00, CURRENT_TIMESTAMP),
(6, 'Yogurt 1 L', 17.00, 3, 51.00, CURRENT_TIMESTAMP),
(6, 'Cereal', 43.00, 1, 43.00, CURRENT_TIMESTAMP),

-- Factura 7
(7, 'Cuaderno universitario', 12.00, 3, 36.00, CURRENT_TIMESTAMP),
(7, 'Lapicero azul', 2.50, 6, 15.00, CURRENT_TIMESTAMP),
(7, 'Marcador', 6.00, 4, 24.00, CURRENT_TIMESTAMP),
(7, 'Regla', 5.00, 2, 10.00, CURRENT_TIMESTAMP),
(7, 'Carpeta', 8.00, 3, 24.00, CURRENT_TIMESTAMP),
(7, 'Pegamento', 8.50, 2, 17.00, CURRENT_TIMESTAMP),

-- Factura 8
(8, 'Hamburguesa simple', 18.00, 5, 90.00, CURRENT_TIMESTAMP),
(8, 'Papas fritas', 12.50, 6, 75.00, CURRENT_TIMESTAMP),
(8, 'Gaseosa personal', 8.00, 7, 56.00, CURRENT_TIMESTAMP),
(8, 'Helado', 10.00, 4, 40.00, CURRENT_TIMESTAMP),
(8, 'Ensalada', 16.00, 3, 48.00, CURRENT_TIMESTAMP),
(8, 'Salsa extra', 5.85, 10, 58.50, CURRENT_TIMESTAMP),

-- Factura 9
(9, 'USB 32 GB', 45.00, 2, 90.00, CURRENT_TIMESTAMP),
(9, 'Mouse optico', 35.00, 1, 35.00, CURRENT_TIMESTAMP),
(9, 'Cable HDMI', 28.00, 1, 28.00, CURRENT_TIMESTAMP),
(9, 'Limpiador pantalla', 22.00, 1, 22.00, CURRENT_TIMESTAMP),
(9, 'Adaptador USB', 15.00, 2, 30.00, CURRENT_TIMESTAMP),

-- Factura 10
(10, 'Almuerzo ejecutivo', 25.00, 6, 150.00, CURRENT_TIMESTAMP),
(10, 'Sopa especial', 15.00, 4, 60.00, CURRENT_TIMESTAMP),
(10, 'Jugo de naranja', 10.00, 5, 50.00, CURRENT_TIMESTAMP),
(10, 'Postre', 12.50, 2, 25.00, CURRENT_TIMESTAMP),
(10, 'Cafe', 7.00, 2, 14.00, CURRENT_TIMESTAMP),
(10, 'Agua mineral', 3.50, 1, 3.50, CURRENT_TIMESTAMP);

SELECT SETVAL('app_factura_id_seq', (SELECT MAX(id) FROM app_factura));
SELECT SETVAL('app_factura_detalle_id_seq', (SELECT MAX(id) FROM app_factura_detalle));
