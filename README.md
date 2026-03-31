# 🌐 Red Social API — Backend

> API REST desarrollada con **Spring Boot 3** para una red social. Implementa autenticación con JWT, encriptación de contraseñas, control de acceso por roles y una arquitectura en capas desacoplada mediante DTOs.

---

## 🧠 Conceptos y Patrones Implementados

| Concepto | Detalle |
|---|---|
| **Arquitectura en capas** | Controller → Service → Repository → Model |
| **DTO Pattern** | Separación entre entidades JPA y objetos de transferencia (Request / Response) |
| **JWT Authentication** | Generación y validación de tokens para sesiones stateless |
| **BCrypt** | Encriptación unidireccional de contraseñas antes de persistirlas |
| **Role-Based Access Control** | Roles `ROLE_ADMIN` y `ROLE_USUARIO` con permisos diferenciados |
| **Data Seeder** | Inicialización automática de roles y usuarios de prueba al arrancar |
| **Spring Security** | Filtros de seguridad configurados con cadena de filtros personalizada |

---

## 🛠️ Stack Tecnológico

- **Java 17**
- **Spring Boot 3.5.9**
- **Spring Security** — BCrypt + filtro JWT personalizado
- **JJWT 0.11.5** — generación y validación de tokens
- **Spring Data JPA** (Hibernate)
- **MySQL** — `mysql-connector-j`
- **SpringDoc OpenAPI 2.5.0** — documentación Swagger UI
- **Lombok** — reducción de boilerplate
- **Maven**

---

## 🚀 Instalación y Ejecución Local

### 1. Crear la base de datos

Antes de levantar la aplicación, ejecutá este script en tu cliente MySQL (Workbench, DBeaver, terminal, etc.):

```sql
CREATE DATABASE red_social;
```

### 2. Configurar credenciales

Editá el archivo `src/main/resources/application.properties` con tus datos locales:

```properties
# Base de datos
spring.datasource.url=jdbc:mysql://localhost:3306/red_social?serverTimezone=UTC
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

# JPA / Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

### 3. Levantar la aplicación

Desde la raíz del proyecto:

```bash
mvn spring-boot:run
```

La API quedará disponible en `http://localhost:8080`.

---

## 🔐 Usuarios de Prueba (Seeder Automático)

Al iniciar por primera vez, el `DataInitializer` crea automáticamente los siguientes usuarios con contraseñas ya encriptadas con BCrypt:

| Email | Contraseña | Roles |
|---|---|---|
| `user@redsocial.com` | `user123` | `ROLE_USUARIO` |
| `admin@redsocial.com` | `admin123` | `ROLE_ADMIN` |

> El seeder verifica la existencia de cada entidad antes de insertarla, por lo que es seguro reiniciar la aplicación sin generar duplicados.

---

## 📌 Endpoints Disponibles

### 🔑 Autenticación (`/auth`) — Público

| Método | Endpoint | Descripción |
|---|---|---|
| `POST` | `/auth/login` | Autentica con email y contraseña, devuelve JWT |
| `POST` | `/auth/register` | Registra un nuevo usuario y devuelve JWT |

**Body de login:**
```json
{
  "email": "user@redsocial.com",
  "password": "user123"
}
```

---

### 👤 Usuarios (`/api/usuario`)

| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| `GET` | `/api/usuario/todos` | Lista todos los usuarios | `ADMIN` |
| `GET` | `/api/usuario/buscar/{id}` | Obtiene un usuario por ID | `ADMIN`, `USUARIO` |
| `POST` | `/api/usuario/crear` | Crea un nuevo usuario | `ADMIN` |
| `PUT` | `/api/usuario/editar/{id}` | Actualiza un usuario | `ADMIN`, `USUARIO` |
| `DELETE` | `/api/usuario/borrar/{id}` | Elimina un usuario | `ADMIN` |

---

### 📝 Publicaciones (`/api/publicacion`)

| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| `GET` | `/api/publicacion/todos` | Lista todas las publicaciones | `ADMIN` |
| `GET` | `/api/publicacion/todasporusuario/{id}` | Lista publicaciones de un usuario | `ADMIN`, `USUARIO` |
| `GET` | `/api/publicacion/buscar/{id}` | Obtiene una publicación por ID | `ADMIN` |
| `POST` | `/api/publicacion/crear` | Crea una publicación | `ADMIN`, `USUARIO` |
| `PUT` | `/api/publicacion/editar/{id}` | Edita una publicación | `ADMIN` |
| `DELETE` | `/api/publicacion/borrar/{id}` | Elimina una publicación | `ADMIN`, `USUARIO` |

---

### 💬 Comentarios (`/api/comentario`)

| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| `GET` | `/api/comentario/todos` | Lista todos los comentarios | `ADMIN` |
| `GET` | `/api/comentario/publicacion/{id}?page=0&size=10` | Comentarios de una publicación (paginado) | `ADMIN`, `USUARIO` |
| `GET` | `/api/comentario/buscar/{id}` | Obtiene un comentario por ID | `ADMIN` |
| `POST` | `/api/comentario/crear` | Crea un comentario | `ADMIN`, `USUARIO` |
| `PUT` | `/api/comentario/editar/{id}` | Edita un comentario | `ADMIN`, `USUARIO` |
| `DELETE` | `/api/comentario/borrar/{id}` | Elimina un comentario | `ADMIN`, `USUARIO` |

---

### 👍 Interacciones (`/api/interaccion`)

| Método | Endpoint | Descripción | Rol requerido |
|---|---|---|---|
| `GET` | `/api/interaccion/todos` | Lista todas las interacciones | `ADMIN` |
| `GET` | `/api/interaccion/buscar/{id}` | Obtiene una interacción por ID | `ADMIN` |
| `POST` | `/api/interaccion/crear` | Registra una interacción (like, etc.) | `ADMIN`, `USUARIO` |
| `PUT` | `/api/interaccion/editar/{id}` | Edita una interacción | `ADMIN` |
| `DELETE` | `/api/interaccion/borrar/{id}` | Elimina una interacción | `ADMIN`, `USUARIO` |

---

### 🔑 Roles (`/admin/rol`) — Solo ADMIN

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/admin/rol/todos` | Lista todos los roles |
| `GET` | `/admin/rol/buscar/{id}` | Obtiene un rol por ID |
| `POST` | `/admin/rol/crear` | Crea un nuevo rol |
| `PUT` | `/admin/rol/editar/{id}` | Edita un rol |
| `DELETE` | `/admin/rol/borrar/{id}` | Elimina un rol |

---

### 🔗 Asignación de Roles (`/api/usuariorol`) — Solo ADMIN

| Método | Endpoint | Descripción |
|---|---|---|
| `GET` | `/api/usuariorol/todos` | Lista todas las asignaciones |
| `GET` | `/api/usuariorol/buscar/{id}` | Obtiene una asignación por ID |
| `POST` | `/api/usuariorol/crear` | Asigna un rol a un usuario |
| `PUT` | `/api/usuariorol/editar/{id}` | Edita una asignación |
| `DELETE` | `/api/usuariorol/borrar/{id}` | Elimina una asignación |

---

## 📂 Estructura del Proyecto

```
src/
└── main/
    ├── java/com/redSocial/red/
    │   ├── config/
    │   │   ├── DataInitializer.java        # Seeder: carga roles y usuarios de prueba
    │   │   └── SwaggerConfig.java          # Configuración de documentación OpenAPI
    │   ├── controller/                     # Controladores REST
    │   ├── DTO/
    │   │   ├── Request/                    # Objetos de entrada (AuthRequest, UsuarioRequest, etc.)
    │   │   └── Response/                   # Objetos de salida (AuthResponse, UsuarioResponse, etc.)
    │   ├── model/                          # Entidades JPA
    │   ├── Repository/                     # Interfaces de Spring Data JPA
    │   ├── security/
    │   │   ├── JwtAuthFilter.java          # Filtro que intercepta y valida el token JWT
    │   │   ├── JwtUtil.java                # Generación y validación de tokens
    │   │   ├── PasswordConfig.java         # Bean de BCryptPasswordEncoder
    │   │   └── SecurityConfig.java         # Cadena de filtros y reglas de acceso
    │   ├── service/                        # Interfaces de la lógica de negocio
    │   ├── serviceImplementation/          # Implementaciones de los servicios
    │   └── RedApplication.java             # Clase principal (entry point)
    └── resources/
        ├── static/
        ├── templates/
        └── application.properties
```

---

## 📖 Documentación con Swagger

El proyecto incluye **Swagger UI** para explorar y probar los endpoints de forma interactiva.

Una vez levantada la aplicación, accedé desde el navegador a:

```
http://localhost:8080/swagger-ui/index.html
```

> Para los endpoints protegidos, hacé click en el botón **Authorize** 🔒 e ingresá el token JWT con el formato:
> ```
> eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyQHJlZHNvY2lhbC5jb20iLCJpYXQiOjE3MDAwMDAwMDAsImV4cCI6MTcwMDAwMzYwMH0.xQ9Tz2kL8pM3rVwYdNfA1sEoHjCbKuIX5gZnWmP6qRc
> ```

---

## 👤 Contacto

**Facundo Garnica**  
📧 facundogarnica1996@gmail.com