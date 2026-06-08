-- EMS Admin schema/data for PostgreSQL 11
-- IMPORTANT: Create the database with UTF8 encoding before running this script.
-- Example:
--   CREATE DATABASE ems_admin WITH ENCODING 'UTF8' TEMPLATE template0;

SET client_encoding = 'UTF8';

BEGIN;

-- Drop in dependency-safe order
DROP TABLE IF EXISTS sys_role_user CASCADE;
DROP TABLE IF EXISTS sys_role_menu CASCADE;
DROP TABLE IF EXISTS sys_user CASCADE;
DROP TABLE IF EXISTS sys_role CASCADE;
DROP TABLE IF EXISTS sys_menu CASCADE;
DROP TABLE IF EXISTS sys_log CASCADE;
DROP TABLE IF EXISTS hibernate_sequence CASCADE;

CREATE TABLE hibernate_sequence (
  next_val BIGINT
);

CREATE TABLE sys_log (
  id BIGSERIAL PRIMARY KEY,
  log_type VARCHAR(2),
  method VARCHAR(100),
  params VARCHAR(255),
  time BIGINT,
  ip VARCHAR(20),
  username VARCHAR(100),
  exception_detail TEXT,
  create_time TIMESTAMP,
  description VARCHAR(255)
);

CREATE TABLE sys_menu (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(40),
  parent_id BIGINT,
  path VARCHAR(100),
  type VARCHAR(2),
  sort INTEGER,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP,
  component VARCHAR(100),
  permission VARCHAR(100),
  icon VARCHAR(100)
);

INSERT INTO sys_menu (id, name, parent_id, path, type, sort, create_time, update_time, component, permission, icon) VALUES
(1, 'Administracion del sistema', 0, NULL, '1', 4, NULL, '2025-10-26 22:41:43', NULL, NULL, 'iconfont icon-setting'),
(2, 'Gestion de usuarios', 1, '/user', '2', 1, NULL, '2025-10-26 22:42:20', 'user/index.vue', NULL, 'iconfont icon-yonghuguanli_huaban'),
(3, 'Gestion de menus', 1, '/menu', '2', 2, '2021-08-18 21:26:01', '2025-10-26 22:42:10', 'menu/index.vue', NULL, 'iconfont icon-caidan'),
(11, 'Gestion de logs', 0, NULL, '1', 3, '2021-08-23 22:00:33', '2025-10-26 22:41:34', NULL, NULL, 'iconfont icon-rizhi'),
(12, 'Gestion de roles', 1, '/role', '2', 3, '2021-08-24 20:46:39', '2025-10-26 22:42:41', 'role/index.vue', NULL, 'iconfont icon-jiaoseguanli'),
(17, 'Registro de actividad', 11, '/log', '2', 1, '2021-11-23 09:32:38', '2025-10-26 22:43:00', 'log/index.vue', NULL, 'iconfont icon-rizhi'),
(20, 'Crear usuario', 2, '/sys/user/edit', '3', 1, '2022-01-19 21:57:32', '2025-05-12 17:41:07', '/sys/user/edit', 'user:add', NULL),
(21, 'Editar usuario', 2, '/sys/user/edit', '3', 2, '2022-01-19 22:04:41', NULL, '/sys/user/edit', 'user:edit', NULL),
(22, 'Eliminar usuario', 2, '/sys/user/del', '3', 3, '2022-01-19 22:05:17', NULL, '/sys/user/del', 'user:del', NULL),
(23, 'Crear menu', 3, '/sys/menu/edit', '3', 1, '2022-01-19 22:06:31', '2022-01-19 22:07:31', '/sys/menu/edit', 'menu:add', NULL),
(24, 'Editar menu', 3, '/sys/menu/edit', '3', 2, '2022-01-19 22:06:53', NULL, '/sys/menu/edit', 'menu:edit', NULL),
(25, 'Eliminar menu', 3, '/sys/menu/del', '3', 3, '2022-01-19 22:07:20', '2022-01-19 22:07:40', '/sys/menu/del', 'menu:del', NULL),
(26, 'Crear rol', 12, '/sys/role/edit', '3', 1, '2022-01-19 22:08:29', NULL, '/sys/role/edit', 'role:add', NULL),
(27, 'Editar rol', 12, '/sys/role/edit', '3', 2, '2022-01-19 22:09:06', NULL, '/sys/role/edit', 'role:edit', NULL),
(28, 'Autorizar rol', 12, 'sys/role/menu/edit', '3', 3, '2022-01-19 22:11:13', '2022-01-19 22:11:22', 'sys/role/menu/edit', 'role:authorize', NULL),
(29, 'Eliminar rol', 12, '/sys/role/del', '3', 4, '2022-01-19 22:11:52', NULL, '/sys/role/del', 'role:del', NULL),
(30, 'Lista de usuarios', 2, '/sys/user/table', '3', 4, '2022-01-20 22:35:59', NULL, '/sys/user/table', 'user:list', NULL),
(31, 'Lista de menus', 3, '/sys/menu/table', '3', 4, '2022-01-20 22:36:59', NULL, '/sys/menu/table', 'menu:list', NULL),
(32, 'Lista de roles', 12, '/sys/role/table', '3', 5, '2022-01-20 22:40:43', '2022-01-20 22:40:52', '/sys/role/table', 'role:list', NULL),
(34, 'Menu lateral', 33, 'sys/menu/tree', '3', 1, '2022-01-20 23:18:03', '2022-01-20 23:20:35', 'sys/menu/tree', 'menu:tree', NULL),
(36, 'Activar o desactivar usuario', 2, '/sys/user/enabled', '3', 5, '2022-10-06 12:22:58', NULL, '', 'user:enabled', NULL);

CREATE TABLE sys_role (
  id BIGSERIAL PRIMARY KEY,
  role_code VARCHAR(20),
  role_name VARCHAR(20),
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  description VARCHAR(200),
  update_time TIMESTAMP
);

INSERT INTO sys_role (id, role_code, role_name, create_time, description, update_time) VALUES
(1, 'ROLE_ADMIN', 'Superadministrador', '2021-08-25 22:55:45', 'Superadministrador con acceso total', NULL),
(2, 'ROLE_USER', 'Usuario estandar', '2021-11-24 22:47:44', 'Usuario estandar', NULL),
(3, 'ROLE_TEST', 'Usuario de prueba', '2021-11-24 22:48:04', 'Usuario de prueba', '2021-12-10 23:33:26'),
(6, '1', '1', '2025-05-06 17:00:50', '1', NULL),
(7, '1213', '12123', '2025-05-22 13:44:10', '13131313', NULL);

CREATE TABLE sys_role_menu (
  id BIGSERIAL PRIMARY KEY,
  role_id BIGINT,
  menu_id BIGINT,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP
);

INSERT INTO sys_role_menu (id, role_id, menu_id, create_time, update_time) VALUES
(36, 3, 32, '2022-11-08 23:00:11', NULL),
(37, 3, 17, '2022-11-08 23:00:11', NULL),
(38, 3, 11, '2022-11-08 23:00:11', NULL),
(46, 6, 32, '2025-05-06 17:01:03', NULL),
(47, 6, 1, '2025-05-06 17:01:03', NULL),
(48, 6, 2, '2025-05-06 17:01:04', NULL),
(49, 6, 3, '2025-05-06 17:01:04', NULL),
(50, 6, 36, '2025-05-06 17:01:04', NULL),
(51, 6, 11, '2025-05-06 17:01:04', NULL),
(52, 6, 12, '2025-05-06 17:01:04', NULL),
(53, 6, 17, '2025-05-06 17:01:04', NULL),
(54, 6, 20, '2025-05-06 17:01:04', NULL),
(55, 6, 21, '2025-05-06 17:01:04', NULL),
(56, 6, 22, '2025-05-06 17:01:04', NULL),
(57, 6, 23, '2025-05-06 17:01:04', NULL),
(58, 6, 24, '2025-05-06 17:01:04', NULL),
(59, 6, 25, '2025-05-06 17:01:04', NULL),
(60, 6, 26, '2025-05-06 17:01:04', NULL),
(61, 6, 27, '2025-05-06 17:01:04', NULL),
(62, 6, 28, '2025-05-06 17:01:04', NULL),
(63, 6, 29, '2025-05-06 17:01:04', NULL),
(64, 6, 30, '2025-05-06 17:01:04', NULL),
(65, 6, 31, '2025-05-06 17:01:05', NULL),
(142, 2, 32, '2025-08-21 10:29:25', NULL),
(143, 2, 3, '2025-08-21 10:29:25', NULL),
(144, 2, 11, '2025-08-21 10:29:25', NULL),
(145, 2, 12, '2025-08-21 10:29:25', NULL),
(146, 2, 17, '2025-08-21 10:29:25', NULL),
(147, 2, 21, '2025-08-21 10:29:25', NULL),
(148, 2, 22, '2025-08-21 10:29:25', NULL),
(149, 2, 23, '2025-08-21 10:29:25', NULL),
(150, 2, 24, '2025-08-21 10:29:25', NULL),
(151, 2, 25, '2025-08-21 10:29:25', NULL),
(152, 2, 26, '2025-08-21 10:29:25', NULL),
(153, 2, 27, '2025-08-21 10:29:25', NULL),
(154, 2, 28, '2025-08-21 10:29:25', NULL),
(155, 2, 29, '2025-08-21 10:29:25', NULL),
(156, 2, 30, '2025-08-21 10:29:25', NULL),
(157, 2, 31, '2025-08-21 10:29:25', NULL);

CREATE TABLE sys_role_user (
  id BIGSERIAL PRIMARY KEY,
  user_id BIGINT,
  role_id BIGINT,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP
);

INSERT INTO sys_role_user (id, user_id, role_id, create_time, update_time) VALUES
(1, 1, 1, '2021-11-17 23:00:35', NULL),
(28, 10, 3, '2022-11-08 22:40:46', NULL),
(29, 10, 2, '2022-11-08 22:40:46', NULL),
(30, 11, 2, '2025-03-08 11:24:45', NULL),
(33, 12, 2, '2025-03-26 22:03:03', NULL),
(35, 13, 2, '2025-05-12 17:45:05', NULL),
(37, 14, 3, '2025-06-11 22:51:12', NULL),
(40, 15, 3, '2025-07-10 16:39:31', NULL),
(41, 16, 3, '2025-08-16 16:45:13', NULL),
(42, 2, 3, '2025-10-14 19:30:55', NULL),
(43, 17, 2, '2025-10-14 19:34:45', NULL);

CREATE TABLE sys_user (
  id BIGSERIAL PRIMARY KEY,
  email VARCHAR(100),
  username VARCHAR(50),
  password VARCHAR(100),
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  enabled BOOLEAN,
  nick_name VARCHAR(50),
  update_time TIMESTAMP
);

INSERT INTO sys_user (id, email, username, password, create_time, enabled, nick_name, update_time) VALUES
(1, '123@qq.com', 'admin', '$2a$10$7mEsufHTedaqOJzDXm/ABe.c3Sx8/ethdyaoCOEyA0x8gGqR3f1sK', '2021-12-01 21:09:15', TRUE, 'Superadministrador', '2021-12-01 21:09:15'),
(2, NULL, 'user', '$2a$10$b5mQxndMAYD3cOnqVyENHOFCRu7lgOY0etmNT5JRtX9RGz.Bkh.jC', '2025-10-14 19:30:54', TRUE, 'Usuario estandar', '2025-10-14 19:30:55'),
(10, NULL, 'test2', '$2a$10$EjKoqaTyrPpPAL7GYHH27.4iccEb4/J3NhQylM.SbeRCw59a5odRq', '2025-03-11 22:15:59', TRUE, 'Usuario de prueba', '2025-03-11 22:15:59'),
(11, NULL, 'wys', '$2a$10$i397uFgzKO4lWO6XJNZreuaPRrM.Ccq23IFes6sx9FoyaHM9rUwCa', '2025-08-20 11:42:50', TRUE, 'Novato', '2025-08-20 11:42:51'),
(12, NULL, '1', '$2a$10$n66qLNKtsZ.bVbfIbN6vy.E3KQUQu5XAq4tUgN3202zUG1ztnaewi', '2025-08-20 11:42:49', FALSE, '1', '2025-08-20 11:42:49'),
(13, NULL, 'guanliyuan2', '$2a$10$7nRVpJQaiupLckcINmcUjeuvNmRsnMVTX13TTDmRLHACTVAvpcDUS', '2025-05-23 17:29:28', TRUE, 'Administrador2', '2025-05-23 17:29:29'),
(14, NULL, '111', '$2a$10$YsdnDekU3UfA7QxeXD.um.iLSaNCItULoX7S79Qd/4uZKqBRw4RMG', '2025-06-11 22:51:24', TRUE, '111', '2025-06-11 22:51:24'),
(15, NULL, '232', '$2a$10$dw6qNt2RD0ekSm1HU7eeNuG7nca1/IJVy66qG3QrNJBmubXeKZc9a', '2025-07-10 16:39:31', NULL, '2342', NULL),
(16, NULL, 'fei', '$2a$10$ewSVCSpsMLSzDMjn5DqCZObpK8uaCvQShqKzcQBwIYmu8F7FcI14O', '2025-08-16 16:45:13', NULL, 'fei', NULL),
(17, NULL, 'we', '$2a$10$Q/8SAnDSw0CEuQFCER2I6eKon1TdKKUvX2gIvrAiNm0GQaYFydeOi', '2025-10-14 19:34:45', NULL, '23', NULL);

-- Keep sequences aligned with explicit IDs
SELECT setval(pg_get_serial_sequence('sys_menu', 'id'), COALESCE((SELECT MAX(id) FROM sys_menu), 1), true);
SELECT setval(pg_get_serial_sequence('sys_role', 'id'), COALESCE((SELECT MAX(id) FROM sys_role), 1), true);
SELECT setval(pg_get_serial_sequence('sys_role_menu', 'id'), COALESCE((SELECT MAX(id) FROM sys_role_menu), 1), true);
SELECT setval(pg_get_serial_sequence('sys_role_user', 'id'), COALESCE((SELECT MAX(id) FROM sys_role_user), 1), true);
SELECT setval(pg_get_serial_sequence('sys_user', 'id'), COALESCE((SELECT MAX(id) FROM sys_user), 1), true);
SELECT setval(pg_get_serial_sequence('sys_log', 'id'), COALESCE((SELECT MAX(id) FROM sys_log), 1), true);

COMMIT;