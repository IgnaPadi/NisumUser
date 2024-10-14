# User Nisum 

Proyecto solicitado como prueba en Nisum Latam.

## Requisitos Previos

- Java JDK (versión 11)
- Gradle (versión 6.8+)

## Configuración del Proyecto

1. Clona este repositorio
2. Abre el proyecto en tu IDE favorito.

## Ejecución

Para ejecutar el proyecto localmente, sigue estos pasos:

1. Abre una terminal en la raíz del proyecto.
2. Ejecuta el siguiente comando para hacer el build del proyecto:

```
gradle build
```   
3. Ejecuta el siguiente comando para ejecutar el proyecto en ambiente local:

```
gradle bootRun
```
4. Se pueden probar los servicios desde un cliente Postman, desde Swagger (http://localhost:8080/swagger-ui/index.html) o con una sentencia curl como la del siguiente ejemplo:
```
curl --location --request POST 'http://localhost:8080/user/register' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=1BF55EBBF9823D72629F36554E55A73A' \
--data-raw '{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.cl",
    "password": "a2asfGfdfdf4"
    
   ,"phones": [
        {
            "number": 87650009,
            "citycode": 7,
            "countrycode": "25"
        }
    ]

    
}'
```
