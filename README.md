# Boxing Gym Manager

**Aplicación web para la gestión de clientes de un gimnasio de boxeo**

> Proyecto desarrollado como portfolio para practicar Java EE, JavaScript, bases de datos y desarrollo web.

---

## 📋 Tabla de contenidos

- [Descripción](#descripción)
- [Características](#características)
- [Tipos de usuarios](#tipos-de-usuarios)
- [Tecnologías utilizadas](#tecnologías-utilizadas)
- [Arquitectura](#arquitectura)
- [Licencia](#licencia)

---

## 📖 Descripción

**GymBox Manager** es una aplicación web desarrollada para facilitar la gestión interna de un gimnasio de boxeo.  
Permite administrar clientes, gestionar peticiones y visualizar estadísticas, además de ofrecer una página pública con formulario de contacto.

El proyecto está orientado a la práctica de tecnologías Java para aplicaciones web y a servir como proyecto de portfolio.

---

## ✨ Características

- Página principal pública.
- Formulario de contacto con envío de correos.
- Gestión completa de clientes:
  - Alta
  - Baja
  - Modificación
  - Visualización
- Visualización de estadísticas mediante gráficos.
- Gestión de peticiones/mensajes.
- Interfaz responsive con Bootstrap.
- Comunicación asíncrona mediante AJAX.

---

## 👥 Tipos de usuarios

### Usuarios no registrados
- Acceso a la página principal.
- Envío de mensajes mediante formulario de contacto (correo).

### Empleados del gimnasio
- Gestión de clientes (CRUD).
- Visualización de estadísticas.
- Recepción y gestión de peticiones.

---

## 🛠️ Tecnologías utilizadas

### Backend
- Java EE 7
- Servlets
- JSP + JSTL
- Hibernate (JPA)
- Jakarta Mail

### Frontend
- HTML5
- JavaScript
- AJAX
- Chart.js (para la visualización de gráficos en las estadísticas de los clientes)
- CSS
- Bootstrap

### Base de datos
- MySQL

### Herramientas
- Maven
- Git / GitHub

---

## 🧱 Arquitectura

La aplicación sigue una arquitectura en capas:

- **Presentación:** HTML, JSP, JSTL, JavaScript, AJAX.
- **Lógica de negocio:** Servlets y servicios Java.
- **Persistencia:** Hibernate (JPA) con MySQL.  Se accede a esta capa mediante los objetos DAO. 

## 📄Licencia

Este proyecto se distribuye bajo la licencia MIT.
Consulta el archivo LICENSE para más información.

