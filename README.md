# ems-admin-vue3

## Introduccion
`ems-admin` (easy manage system) es un sistema de administracion backend minimalista.
Construido con Spring Boot, Spring Security, MyBatis-Plus, MySQL, JWT, Vue 3, Vite y Element Plus.
Incluye autenticacion, control de permisos y gestion de logs como base para crear modulos de negocio.

## Funciones
- Inicio de sesion (`admin/123456`)
- Gestion de usuarios: crear, editar, eliminar, activar/desactivar y buscar
- Gestion de menus: crear, editar, eliminar y buscar
- Gestion de roles: crear, editar, eliminar, autorizar y buscar
- Gestion de logs: buscar y ver detalle

## Arquitectura
- Autenticacion y autorizacion con Spring Security + JWT
- Permisos por modelo Usuario -> Rol -> Permiso
- Control de permisos hasta nivel boton en frontend
- Captcha con cache local

## Enlaces
- Repositorio: `https://github.com/ems-admin`
- Cambios: `CHANGELOG.md`