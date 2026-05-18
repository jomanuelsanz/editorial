# Editorial - CRUD con Spring Boot

Proyecto CRUD desarrollado con Spring Boot para la gestión de Revistas y Artículos.
Incluye API REST y interfaz web con Thymeleaf.

**Alumno:** José Manuel Sanz Ranz  
**Tema:** Editorial (Revista → Artículo)

---

## Modelos

### Revista
| Campo | Tipo |
|---|---|
| id | Long |
| nombre | String |
| tematica | String |
| periodicidad | String |
| issn | String |

### Artículo
| Campo | Tipo |
|---|---|
| id | Long |
| titulo | String |
| autor | String |
| numeroPaginas | Integer |
| fechaPublicacion | LocalDate |
| openAccess | Boolean |
| revista | Revista (FK) |

---

## Requisitos previos

- Java 21+
- Maven
- Docker y Docker Compose

---

## Configuración y ejecución

### 1. Clonar el repositorio

```bash
git clone https://github.com/jomanuelsanz/editorial.git
cd editorial
```

### 2. Levantar la base de datos con Docker

```bash
docker-compose up mysql -d
```

Esto arranca un contenedor MySQL en el puerto **3306** con:
- Base de datos: `editorial_db`
- Usuario: `root`
- Contraseña: `root`

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación arranca en el puerto **8080**.

---

## Acceso

### Interfaz web
| URL | Descripción |
|---|---|
| http://localhost:8080/web/revistas | Listado de revistas |
| http://localhost:8080/web/revistas/nueva | Crear revista |
| http://localhost:8080/web/articulos | Listado de artículos |
| http://localhost:8080/web/articulos/nuevo | Crear artículo |

### API REST
| Método | URL | Descripción |
|---|---|---|
| GET | /api/revistas | Listar revistas |
| GET | /api/revistas?nombre= | Filtrar por nombre |
| GET | /api/revistas/{id} | Obtener revista |
| POST | /api/revistas | Crear revista |
| PUT | /api/revistas/{id} | Actualizar revista |
| DELETE | /api/revistas/{id} | Borrar revista |
| GET | /api/articulos | Listar artículos |
| GET | /api/articulos?autor= | Filtrar por autor |
| GET | /api/articulos?openAccess= | Filtrar por open access |
| GET | /api/articulos?anio= | Filtrar por año |
| GET | /api/articulos/{id} | Obtener artículo |
| POST | /api/articulos | Crear artículo |
| PUT | /api/articulos/{id} | Actualizar artículo |
| DELETE | /api/articulos/{id} | Borrar artículo |

---

## Tecnologías

- Java 26
- Spring Boot 4.0.6
- Spring Data JPA + Hibernate
- MySQL 8.0
- Thymeleaf + Bootstrap 5
- Lombok
- Docker + Docker Compose
- Maven