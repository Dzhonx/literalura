# Literalura - Proyecto Spring Boot

Literalura es una aplicación de consola desarrollada con **Java + Spring
Boot** que permite buscar libros desde una API externa, guardarlos en
una base de datos y consultar información de autores y libros
almacenados.

Este proyecto fue creado como práctica para aprender **Spring Boot, JPA,
consumo de APIs REST y conexión a PostgreSQL**.

------------------------------------------------------------------------

## Tecnologías utilizadas

-   Java 17+
-   Spring Boot
-   Spring Data JPA
-   Hibernate
-   PostgreSQL
-   Maven
-   API Gutendex (para búsqueda de libros)

------------------------------------------------------------------------

## Configuración del proyecto

### 1️ Clonar el repositorio

``` bash
git clone https://github.com/TU-USUARIO/literalura.git
cd literalura
```

### 2️ Configurar la base de datos PostgreSQL

Crear la base de datos:

``` sql
CREATE DATABASE literalura;
```

### 3️ Configurar `application.properties`

Ubicado en:

    src/main/resources/application.properties

Ejemplo de configuración:

``` properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=postgres
spring.datasource.password=TU_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

------------------------------------------------------------------------

## Estructura del proyecto

    literalura
    │── model
    │   ├── Autor.java
    │   └── Libro.java
    │
    │── repository
    │   ├── AutorRepository.java
    │   └── LibroRepository.java
    │
    │── service
    │   ├── ConsumoAPIService.java
    │   └── LibroService.java
    │
    │── principal
    │   └── Principal.java
    │
    └── LiteraluraApplication.java

------------------------------------------------------------------------

## Cómo ejecutar la aplicación

Desde el IDE (IntelliJ o Eclipse):

Ejecutar la clase:

    LiteraluraApplication.java

O desde consola:

``` bash
mvn spring-boot:run
```

------------------------------------------------------------------------

## Funcionalidades del menú

Cuando la aplicación inicia, se muestra un menú interactivo en consola:

  Opción   Función
  -------- ---------------------------------------------------------
  1        Buscar libro por título y guardarlo en la base de datos
  2        Listar libros registrados
  3        Listar autores registrados
  4        Listar autores vivos en un determinado año
  5        Listar libros por idioma
  0        Salir

------------------------------------------------------------------------

## Ejemplo de uso

1.  Elegir la opción **1**
2.  Escribir el nombre de un libro (ejemplo: `Don Quijote`)
3.  El sistema consulta la API, obtiene la información y guarda el libro
    y su autor en la base de datos

------------------------------------------------------------------------

## Aprendizajes clave

✔️ Inyección de dependencias con Spring\
✔️ Mapear entidades con JPA\
✔️ Relaciones @ManyToOne y @OneToMany\
✔️ Consumo de APIs REST con `HttpClient`\
✔️ Persistencia en PostgreSQL\
✔️ Consultas personalizadas con Spring Data JPA

------------------------------------------------------------------------

## Autor

Proyecto desarrollado como práctica de backend con **Spring Boot**.
