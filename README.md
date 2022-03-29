### Demo Exchange Rate API
`<repositorio>` : <https://github.com/miguel-armas-unmsm/demo-exchange-rate>

`<autor>`: Miguel Rodrigo Armas Abt

##Acerca de la funcionalidad
| Endpoint | Método | Descripción
| ------------- | ------------------------------ |---------- |
| `/tcs/v1/auth`| POST | Genera el token de autenticación. |
| `/tcs/v1/exchange-rates`| POST | Guarda un registro de tipo de cambio. |
| `/tcs/v1/exchange-rates`| GET | Recupera todos los registros de tipo de cambio. |
| `/tcs/v1/exchange-rates/{id}`| PUT | Actualiza un registro de tipo de cambio. |
| `/tcs/v1/exchange-rates/convert`| POST | Aplica el tipo de cambio a un monto. |

> Observación: Cada uno de los endpoints requieren el header Authorization.

##Prerrequisitos para instalación y despliegue
* Java 11
* Maven 3.8.1
* Habilitar plugin de Lombok
* Docker

##Despliegue con Docker
| Procedimiento | Comando docker
| ------------- | ------------------------------ |
| Crear imagen | `docker build -t exchange-rate-api .` |
| Ver imágenes | `docker images` |
| Ejecutar la aplicación | `docker run --name exchange-rate-api -p 8090:8090 exchange-rate-api:latest`  o `docker run -p 8090:8090 exchange-rate-api:latest`|

##Pruebas unitarias
> No aplica.