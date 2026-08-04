# 📚 Library Management API

![Java](https://img.shields.io/badge/Java-21-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-JWT-6DB33F?logo=springsecurity&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)
![MapStruct](https://img.shields.io/badge/MapStruct-1.6-blue)

API REST para gestión de biblioteca, desarrollada con Spring Boot. Incluye relaciones JPA completas, autenticación con JWT, roles y permisos, y despliegue containerizado con Docker.

## 🛠️ Stack

- Java 21
- Spring Boot (Web, Data JPA, Security, Validation)
- PostgreSQL
- JWT (JJWT)
- MapStruct
- Lombok
- Docker & Docker Compose

## ✨ Funcionalidades

- CRUD completo de Libros, Categorías y Autores
- Relaciones: `@ManyToOne`/`@OneToMany` (Book–Category) y `@ManyToMany` (Book–Author)
- Consultas optimizadas con `@EntityGraph` para evitar el problema N+1
- Autenticación con JWT (registro y login)
- Autorización por roles (`MEMBER` / `LIBRARIAN`) con `@PreAuthorize`
- Manejo global de excepciones con respuestas de error consistentes

## 🚀 Cómo levantar el proyecto

### Requisitos

- Docker y Docker Compose instalados

### Pasos

1. Cloná el repositorio:
   ```bash
   git clone https://github.com/manucourtade/library-management-api.git
   cd library-management-api
   ```

2. Creá un archivo `.env` en la raíz del proyecto con estas variables:
   ```env
   POSTGRES_USER=postgres
   POSTGRES_PASSWORD=password
   JWT_SECRET=una-clave-secreta-de-al-menos-32-caracteres
   ```

3. Levantá todo con Docker Compose:
   ```bash
   docker compose up --build
   ```

4. La API va a estar disponible en `http://localhost:8080`.

## 📖 Endpoints principales

### Auth (públicos)

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/auth/register` | Registra un nuevo usuario (rol `MEMBER` por defecto) |
| POST | `/auth/login` | Login, devuelve un JWT |

### Books

| Método | Endpoint | Rol requerido |
|---|---|---|
| GET | `/test/books` | Autenticado |
| GET | `/test/books/{id}` | Autenticado |
| GET | `/test/books/isbn/{isbn}` | Autenticado |
| POST | `/test/books` | `LIBRARIAN` |
| PUT | `/test/books/{id}` | `LIBRARIAN` |
| PATCH | `/test/books/{id}` | `LIBRARIAN` |
| DELETE | `/test/books/{id}` | `LIBRARIAN` |

### Categories

| Método | Endpoint | Rol requerido |
|---|---|---|
| GET | `/categories` | Autenticado |
| GET | `/categories/{id}` | Autenticado |
| GET | `/categories/all-with-books` | Autenticado |
| POST | `/categories` | `LIBRARIAN` |
| PUT | `/categories/{id}` | `LIBRARIAN` |
| DELETE | `/categories/{id}` | `LIBRARIAN` |

### Authors

| Método | Endpoint | Rol requerido |
|---|---|---|
| GET | `/authors` | Autenticado |
| GET | `/authors/{id}` | Autenticado |
| GET | `/authors/name/{name}` | Autenticado |
| POST | `/authors` | `LIBRARIAN` |

## 🔑 Autenticación

Todos los endpoints, salvo `/auth/**`, requieren el header:

```
Authorization: Bearer <token>
```

El token se obtiene haciendo login en `/auth/login`. Para crear un usuario con rol `LIBRARIAN`, hay que actualizarlo manualmente en la base de datos (por diseño, no se puede autoasignar ese rol desde el registro):

```sql
UPDATE users SET role = 'LIBRARIAN' WHERE username = 'tu_usuario';
```

## 📂 Estructura del proyecto

```
src/main/java/.../
├── controller/     # Endpoints REST
├── service/        # Lógica de negocio
├── repository/     # Acceso a datos (Spring Data JPA)
├── model/          # Entidades JPA
├── dto/            # Request/Response DTOs
├── mapper/         # Mappers (MapStruct)
├── security/        # JWT, filtros, configuración de Spring Security
└── exception/       # Excepciones custom y manejo global
```

## 🐳 Docker

El proyecto usa un `Dockerfile` multi-stage (build con Maven + runtime liviano con JRE) y `docker-compose.yml` para levantar la API junto con PostgreSQL en contenedores separados, conectados por red interna.

## 📸 Capturas

Ejemplo de respuesta de `GET /authors` con libros anidados, autenticado con JWT:

![Ejemplo de endpoint autenticado](docs/authors-endpoint.png)

> Creá una carpeta `docs/` en la raíz del repo y subí ahí tus capturas (por ejemplo, esta respuesta de Postman, el flujo de login, o el `docker compose up` corriendo) para que se vean directo en el README.
