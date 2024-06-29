# ForumApp

ForumApp es una aplicación de foro desarrollada en Java utilizando Spring Boot. Permite registrar, listar, actualizar y eliminar tópicos, así como la autenticación de usuarios.

## Configuración

1. Clona este repositorio:
    ```sh
    git clone https://github.com/tu-usuario/forumapp.git
    cd forumapp
    ```

2. Configura la base de datos:
    - Crea una base de datos en MySQL.
    - Actualiza el archivo `src/main/resources/application.properties` con tus credenciales de MySQL:
      ```properties
      spring.datasource.url=jdbc:mysql://localhost:3306/tu_basededatos
      spring.datasource.username=tu_usuario
      spring.datasource.password=tu_contrasena
      ```

## API Endpoints

### Autenticación de Usuario

#### Login

- **URL:** `/login`
- **Método:** `POST`
- **Descripción:** Autentica a un usuario y devuelve un token JWT.
- **Cuerpo de la Solicitud:**
  ```json
  {
    "usuario": "nombre_de_usuario",
    "contrasena": "contrasena"
  }

- **Respuesta Exitosa:**
- **Código: 200 OK**
  ```json
  {
  "token": "jwt-token-generado"
  }



#### Registrar Tópico

- **URL:** `/topicos`
- **Método:** `POST`
- **Descripción:** Registra un nuevo tópico.
- **Autorización:** Requiere token JWT.
- **Cuerpo de la Solicitud:**
  ```json
  {
    "titulo": "crear titulo",
    "autor": "crear autor",
    "mensaje": "Crear mensaje",
    "status": "Asignar estatus",
    "cursoId": Asignar ID curso
  }
  
- **Respuesta Exitosa:**
- **Código: 201 Created**
  ```json
  {
    "id": 1,
    "titulo": "Título del Tópico",
    "mensaje": "Contenido del Tópico",
    "autor": "Autor del Tópico",
    "Fecha_creacion":"aaaa-mm-dd h 00:00:00"
    "curso": "Curso Asociado"
  }



#### Actualizar Tópico

- **URL:** `/topicos/idTopico`
- **Método:** `PUT`
- **Descripción:** Actualiza un tópico.
- **Autorización:** Requiere token JWT.
- **Cuerpo de la Solicitud:**
  ```json
  {
    "titulo": "modificar titulo",
    "mensaje": "Modificar mensaje",
    "status": "Modificar estatus",
  }
  
- **Respuesta Exitosa:**
- **Código: 200 ok**



#### Eliminar Tópico

- **URL:** `/topicos/idTopico`
- **Método:** `DELETE`
- **Descripción:** Eliminar un tópico.
- **Autorización:** Requiere token JWT.


- **Respuesta Exitosa:**
- **Código: 204 No Content**



#### Listar Tópico

- **URL:** `/topicos`
- **Método:** `GET`
- **Descripción:** Listar tópicos.
- **Autorización:** Requiere token JWT.
- **Parámetros de Consulta:**
  size (opcional): Tamaño de la página. Por defecto, 10.
  sort (opcional): Campo para ordenar. Por defecto, autor.


- **Respuesta Exitosa:**
- **Código: 200 ok**
  ```json
  {
    "totalElements": 1,
    "totalPages": 1,
    "first": true,
    "last": true,
    "size": 10,
    "content": [
        {
            "id": 2,
            "titulo": "Titulo",
            "mensaje": "Mensaje",
            "autor": "Autor",
            "status": "Status",
            "Fecha_creacion":"aaaa-mm-dd h 00:00:00"
            "tituloCurso": "Titulo curso"
        },
    ],
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "numberOfElements": 1,
    "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "empty": false
  }