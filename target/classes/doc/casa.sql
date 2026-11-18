-- ==========================================================
-- PostgreSQL 16
-- Ejemplo relación UNO A MUCHOS
-- Una casa tiene muchos cuartos
-- Esquema: public
-- ==========================================================

-- Limpiar tablas si existen
DROP TABLE IF EXISTS public.cuartos;
DROP TABLE IF EXISTS public.casas;


-- ==========================================================
-- TABLA MAESTRA: CASAS
-- ==========================================================

CREATE TABLE public.casas
(
    id_casa SERIAL PRIMARY KEY,
    nombre_casa VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    cantidad_pisos INTEGER NOT NULL,
    fecha_construccion DATE
);


-- ==========================================================
-- TABLA DETALLE: CUARTOS
-- ==========================================================

CREATE TABLE public.cuartos
(
    id_cuarto SERIAL PRIMARY KEY,
    id_casa INTEGER NOT NULL,
    nombre_cuarto VARCHAR(100) NOT NULL,
    tipo_cuarto VARCHAR(50) NOT NULL,
    area_m2 NUMERIC(8,2),
    tiene_ventana BOOLEAN DEFAULT TRUE,

    CONSTRAINT fk_casa_cuarto
        FOREIGN KEY (id_casa)
        REFERENCES public.casas(id_casa)
        ON DELETE CASCADE
);


-- ==========================================================
-- INSERTAR 10 CASAS (TABLA MAESTRA)
-- ==========================================================

INSERT INTO public.casas
(nombre_casa, direccion, ciudad, cantidad_pisos, fecha_construccion)
VALUES
('Casa Los Pinos', 'Av. Central 101', 'Tarija', 2, '2010-05-15'),
('Casa El Valle', 'Calle Norte 202', 'Tarija', 1, '2012-08-20'),
('Casa La Colina', 'Barrio Alto 303', 'Tarija', 3, '2015-02-10'),
('Casa Primavera', 'Av. Flores 404', 'Tarija', 2, '2018-11-25'),
('Casa El Bosque', 'Calle Verde 505', 'Tarija', 1, '2008-06-30'),
('Casa Moderna', 'Av. Libertad 606', 'Tarija', 2, '2020-01-12'),
('Casa Familiar', 'Calle Sol 707', 'Tarija', 2, '2016-09-18'),
('Casa Colonial', 'Centro Histórico 808', 'Tarija', 1, '1995-03-22'),
('Casa Horizonte', 'Zona Sur 909', 'Tarija', 3, '2022-07-05'),
('Casa Jardín', 'Av. Verde 1001', 'Tarija', 2, '2019-12-01');


-- ==========================================================
-- INSERTAR 100 CUARTOS (10 POR CADA CASA)
-- ==========================================================

INSERT INTO public.cuartos
(id_casa, nombre_cuarto, tipo_cuarto, area_m2, tiene_ventana)
VALUES

-- Casa 1
(1,'Sala Principal','Sala',25.50,true),
(1,'Cocina','Cocina',15.20,true),
(1,'Dormitorio Principal','Dormitorio',22.00,true),
(1,'Dormitorio Niño','Dormitorio',14.50,true),
(1,'Baño Principal','Baño',6.50,false),
(1,'Baño Visitas','Baño',4.00,false),
(1,'Garaje','Garaje',30.00,false),
(1,'Estudio','Estudio',12.00,true),
(1,'Comedor','Comedor',18.00,true),
(1,'Lavandería','Lavandería',8.00,false),

-- Casa 2
(2,'Sala Principal','Sala',24.00,true),
(2,'Cocina','Cocina',14.00,true),
(2,'Dormitorio Principal','Dormitorio',20.00,true),
(2,'Dormitorio Secundario','Dormitorio',16.00,true),
(2,'Baño Principal','Baño',6.00,false),
(2,'Baño Visitas','Baño',4.00,false),
(2,'Garaje','Garaje',28.00,false),
(2,'Oficina','Estudio',10.00,true),
(2,'Comedor','Comedor',17.00,true),
(2,'Patio Interno','Patio',20.00,true),

-- Casa 3
(3,'Sala Principal','Sala',30.00,true),
(3,'Cocina','Cocina',18.00,true),
(3,'Dormitorio 1','Dormitorio',25.00,true),
(3,'Dormitorio 2','Dormitorio',18.00,true),
(3,'Baño 1','Baño',7.00,false),
(3,'Baño 2','Baño',7.00,false),
(3,'Garaje','Garaje',35.00,false),
(3,'Biblioteca','Estudio',15.00,true),
(3,'Comedor','Comedor',22.00,true),
(3,'Terraza','Terraza',30.00,true),

-- Casa 4
(4,'Sala','Sala',22.00,true),
(4,'Cocina','Cocina',12.00,true),
(4,'Dormitorio Matrimonial','Dormitorio',20.00,true),
(4,'Dormitorio 2','Dormitorio',15.00,true),
(4,'Baño Principal','Baño',6.00,false),
(4,'Baño Social','Baño',4.00,false),
(4,'Garaje','Garaje',25.00,false),
(4,'Sala TV','Sala',16.00,true),
(4,'Comedor','Comedor',18.00,true),
(4,'Lavandería','Lavandería',7.00,false),

-- Casa 5
(5,'Sala','Sala',20.00,true),
(5,'Cocina','Cocina',13.00,true),
(5,'Dormitorio','Dormitorio',18.00,true),
(5,'Dormitorio Invitados','Dormitorio',14.00,true),
(5,'Baño','Baño',5.50,false),
(5,'Garaje','Garaje',25.00,false),
(5,'Comedor','Comedor',15.00,true),
(5,'Patio','Patio',25.00,true),
(5,'Lavandería','Lavandería',6.00,false),
(5,'Depósito','Depósito',8.00,false),

-- Casa 6
(6,'Sala','Sala',26.00,true),
(6,'Cocina','Cocina',16.00,true),
(6,'Dormitorio Principal','Dormitorio',23.00,true),
(6,'Dormitorio Niño','Dormitorio',14.00,true),
(6,'Baño Principal','Baño',6.00,false),
(6,'Baño Social','Baño',4.00,false),
(6,'Garaje','Garaje',32.00,false),
(6,'Oficina','Estudio',12.00,true),
(6,'Comedor','Comedor',20.00,true),
(6,'Terraza','Terraza',22.00,true),

-- Casa 7
(7,'Sala','Sala',24.00,true),
(7,'Cocina','Cocina',15.00,true),
(7,'Dormitorio 1','Dormitorio',21.00,true),
(7,'Dormitorio 2','Dormitorio',16.00,true),
(7,'Baño 1','Baño',6.00,false),
(7,'Baño 2','Baño',5.00,false),
(7,'Garaje','Garaje',30.00,false),
(7,'Estudio','Estudio',11.00,true),
(7,'Comedor','Comedor',18.00,true),
(7,'Patio','Patio',20.00,true),

-- Casa 8
(8,'Sala Colonial','Sala',28.00,true),
(8,'Cocina','Cocina',14.00,true),
(8,'Dormitorio Principal','Dormitorio',22.00,true),
(8,'Dormitorio Antiguo','Dormitorio',17.00,true),
(8,'Baño','Baño',6.00,false),
(8,'Garaje','Garaje',25.00,false),
(8,'Biblioteca','Estudio',14.00,true),
(8,'Comedor','Comedor',20.00,true),
(8,'Patio','Patio',30.00,true),
(8,'Depósito','Depósito',9.00,false),

-- Casa 9
(9,'Sala Moderna','Sala',32.00,true),
(9,'Cocina Integral','Cocina',20.00,true),
(9,'Dormitorio Principal','Dormitorio',26.00,true),
(9,'Dormitorio 2','Dormitorio',18.00,true),
(9,'Baño Principal','Baño',8.00,false),
(9,'Baño Visitas','Baño',5.00,false),
(9,'Garaje Doble','Garaje',40.00,false),
(9,'Oficina','Estudio',15.00,true),
(9,'Comedor','Comedor',25.00,true),
(9,'Terraza','Terraza',35.00,true),

-- Casa 10
(10,'Sala Jardín','Sala',27.00,true),
(10,'Cocina','Cocina',17.00,true),
(10,'Dormitorio Principal','Dormitorio',24.00,true),
(10,'Dormitorio Niño','Dormitorio',15.00,true),
(10,'Baño Principal','Baño',6.50,false),
(10,'Baño Social','Baño',4.00,false),
(10,'Garaje','Garaje',28.00,false),
(10,'Estudio','Estudio',13.00,true),
(10,'Comedor','Comedor',19.00,true),
(10,'Jardín Interno','Jardín',35.00,true);


-- ==========================================================
-- CONSULTA DE PRUEBA: VER RELACION UNO A MUCHOS
-- ==========================================================

SELECT
    c.id_casa,
    c.nombre_casa,
    cu.id_cuarto,
    cu.nombre_cuarto,
    cu.tipo_cuarto
FROM public.casas c
INNER JOIN public.cuartos cu
ON c.id_casa = cu.id_casa
ORDER BY c.id_casa, cu.id_cuarto;


-- Contar cuartos por casa
SELECT
    c.nombre_casa,
    COUNT(cu.id_cuarto) AS cantidad_cuartos
FROM public.casas c
LEFT JOIN public.cuartos cu
ON c.id_casa = cu.id_casa
GROUP BY c.nombre_casa
ORDER BY c.id_casa;