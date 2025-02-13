#  **NAVES 🚀**

![Nave espacial](https://media2.giphy.com/media/v1.Y2lkPTc5MGI3NjExZWdsM3B2YnhjYmF5ZzU0YWY0cnNhcnd1NGl1dXA1dGUzZzNpN3A3YSZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9cw/sKeCondWadIiyKR4Hq/giphy.gif)

# Prueba Técnica Backend - W2M

Este proyecto es una API RESTful desarrollada en **Spring Boot** que permite realizar un mantenimiento CRUD de naves espaciales de series y películas. La aplicación cumple con los requisitos del challenge técnico proporcionado por W2M.

## Índice 📌
1. [Puntos que se validan para el test 🏆](#puntos-que-se-validan-para-el-test-)
    - [Alcance del proyecto 📃](#alcance-del-proyecto)
    - [Puntos a tener en cuenta 🖋️](#puntos-a-tener-en-cuenta-)
2. [Requisitos 👷‍♂️](#requisitos-)
3. [Correr la app 🚀](#correr-la-app-)
    - [Inicia la app en Docker](#inicia-la-app-en-docker-)
    - [Puedes visualizar los endpoints en Swagger](#puedes-visualizar-los-endpoints-en-swagger)
    - [Tienes acceso a H2 Console](#tienes-acceso-a-h2-console)
4. [Login para usar los endpoints 🔒](#login-para-usar-los-endpoints-)
5. [PLUS 🍷](#plus-)
6. [Tecnologías Utilizadas 🛠️](#tecnologías-utilizadas-)


## Puntos que se validan para el test 🏆
> Los puntos en ✅ son los que la app cumple

> Los puntos en ✖️ so los que la app no cumple

### **Alcance del proyecto📃**

- Consultar todas las naves utilizando paginación. ✅
- Consultar una única nave por id. ✅
- Consultar todas las naves que contienen, en su nombre, el valor de un parámetro enviado en la petición. Por ejemplo, si enviamos “wing” devolverá “x-wing”. ✅
- Crear una nueva nave. ✅
- Modificar una nave. ✅
- Eliminar una nave. ✅
- Test unitario de como mínimo de una clase. ✖️
- Desarrollar un @Aspect que añada una línea de log cuando nos piden una nave con un id negativo. ✅
- Gestión centralizada de excepciones. ✅
- Utilizar cachés de algún tipo. ✖️

---

### **Puntos a tener en cuenta 🖋️**

- Las naves se deben guardar en una base de datos. Puede ser, por ejemplo, H2 en memoria. ✅
- La prueba se debe presentar en un repositorio de Git. ✅
- Utilizar alguna librería que facilite el mantenimiento de los scripts DDL de base de datos. ✅
- Test de integración. ✖️
- Presentar la aplicación dockerizada. ✅
- Documentación de la API. ✅
- Seguridad del API. ✅
- Implementar algún consumer/producer para algún broker (Rabbit, Kafka, etc). ✖️
---

## **Requisitos 👷‍♂️**

Para ejecutar este proyecto, necesitarás lo siguiente:

- **Docker**
- **Git** para clonar el repositorio.

---

## **Correr la app 🚀**

### Inicia la app en docker: 

```bash
docker-compose up --build
```

### Puedes visualizar los endpoints en Swagger

```bash
http://localhost:8080/swagger-ui/index.html
```


### Tienes acceso a H2 Console


```bash
http://localhost:8080/h2-console
```

#### Usuario de prueba para H2 Console
> user: w2m-user

> password: w2m-password

---

## Login para usar los endpoints 🔒

El uso de los endpoint estan asegurados con Spring Security y el uso de JWT.
Para poder utilizarlos primero hay que iniciar sesion en el endpoint :
> http://localhost:8080/api/v1/auth/login

El siguiente body tiene el usuario de prueba **w2m-user** que servirá para consumir los endpoints de naves.
Debe enviar este body con el metodo POST a **/auth/login**

```json
{
  "username": "w2m-user",
  "password": "w2m-password"
}
```

Esto le brindará una respuesta con el token 

```json
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ3Mm0tdXNlciIsImV4cCI6MTczOTUwMTEzNCwiaWF0IjoxNzM5NDgzMTM0fQ.XjY7_VdUolO1md1RUUnBl5Tefb-Y0P4TPQrfV6nfD1-Wp2k4Uekigxy4ChMD2Vy_xbi-gXTlGznAToThlAMPww"
}
```
Luego para el uso de los endpoints de naves enviar el modo Authorization como "Bearer {token}"
Podrá utilizarlo por 5 horas. Luego deberás volver a iniciar sesión.

---
## **PLUS 🍷**
En la raiz del proyecto se encuentra un archivo .json
Si utiliza Postman he creado algunos endpoints para probar el funcionamiento
Puede importar esta collection y probar mas facilmente.
El archivo se llama: 

```sh
w2m_prueba_tecnica_backend.postman_collection.json
```

## **Tecnologías Utilizadas 🛠️**

- **Java 21**: Lenguaje principal de desarrollo.
- **Spring Boot 3.x**: Framework para construir la API RESTful.
- **Spring Data JPA**: Para interactuar con la base de datos.
- **H2 Database**: Base de datos en memoria para desarrollo.
- **Liquibase**: Gestión de scripts DDL.
- **Swagger OpenAPI**: Documentación interactiva de la API.
- **Spring AOP**: Implementación de aspectos para logs.
- **Docker**: Para contenerizar la aplicación.

---

