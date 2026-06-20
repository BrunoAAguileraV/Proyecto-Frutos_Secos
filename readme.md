# Sistema de Gestión Frutos Secos Velásquez
Evaluación 3 (EA3) - Arquitectura de Microservicios

Este repositorio contiene la implementación backend del sistema de gestión para Frutos Velásquez. La solución consiste en una arquitectura distribuida basada en microservicios, asegurada mediante JWT y centralizada a través de un API Gateway.

## Herramientas de instalación y entorno
Para compilar y ejecutar este proyecto en un entorno local, se requiere la siguiente configuración:

- Java Development Kit (JDK) 17 o superior.
- Maven (Gestor de dependencias y compilación).
- MySQL Server 8.0 o superior.
- Entorno de desarrollo (IDE) recomendado: Visual Studio Code, IntelliJ IDEA o Eclipse.
- Cliente REST para pruebas: Postman o Insomnia.
- Git para el control de versiones.

## Bibliotecas y tecnologías utilizadas
El desarrollo se realizó sobre el framework Spring Boot (versión 3.x) y hace uso de las siguientes dependencias principales:

- spring-boot-starter-web: Para la exposición de servicios REST.
- spring-boot-starter-data-jpa: Para el mapeo objeto-relacional (ORM) y persistencia de datos.
- mysql-connector-j: Controlador oficial para la conexión a bases de datos MySQL.
- spring-cloud-starter-gateway: Implementación del API Gateway para enrutamiento de peticiones.
- spring-boot-starter-security: Para el manejo de autenticación y control de accesos.
- jjwt-api / jjwt-impl (io.jsonwebtoken): Para la creación, firma y validación de tokens JWT (Stateless).
- springdoc-openapi-starter-webmvc-ui (y webflux-ui): Para la auto-generación de la documentación de la API.

## Ejemplos de rutas para ejecución de API REST
Todas las peticiones del cliente deben ser dirigidas al API Gateway (Puerto 9090), el cual se encargará de enrutar la solicitud al microservicio correspondiente en la red interna.

1. Obtener Token de Acceso (POST)
- Ruta: http://localhost:9090/auth/login
- Descripción: Endpoint público que valida credenciales y retorna el Bearer Token JWT.

2. Obtener lista de productos del Catálogo (GET)
- Ruta: http://localhost:9090/api/v1/productos
- Headers requeridos: Authorization: Bearer <token_jwt>
- Descripción: Retorna un arreglo JSON con los productos disponibles.

3. Crear un registro de Despacho (POST)
- Ruta: http://localhost:9090/api/v1/shippings
- Headers requeridos: Authorization: Bearer <token_jwt>
- Body (JSON):
{
  "ventaId": 1,
  "direccionDespacho": "Av. Macul 1234, Macul",
  "estadoDespacho": "PREPARANDO"
}

4. Actualizar un despacho por ID (PUT)
- Ruta: http://localhost:9090/api/v1/shippings/1
- Headers requeridos: Authorization: Bearer <token_jwt>
- Body (JSON): Contiene el objeto actualizado con los nuevos parámetros logísticos.

## Ejemplos de ruta para ejecución de Swagger
La documentación técnica de la API fue unificada mediante Springdoc. El API Gateway recolecta las definiciones de los microservicios y las expone en una sola vista.

1. Interfaz Gráfica Centralizada (Swagger UI)
- Ruta: http://localhost:9090/swagger-ui/index.html
- Uso: Permite visualizar y probar los endpoints. Se debe utilizar el menú desplegable superior derecho ("Select a definition") para alternar entre la documentación de los distintos microservicios (Ventas, Compras, Despachos, etc.).

2. Accesos directos a los contratos JSON (OpenAPI 3)
Si se requiere visualizar el archivo de especificación crudo de un microservicio específico a través del Gateway:
- Ventas: http://localhost:9090/api/v1/ventas/v3/api-docs
- Compras: http://localhost:9090/api/v1/compras/v3/api-docs
- Despachos: http://localhost:9090/api/v1/shippings/v3/api-docs
