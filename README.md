# Sistema de procura de bienes

Backend del sistema desarrollado por Renzo Paull Castañeda Cabanillas para E.I.G.R. CONTRATISTAS S.R.L. Organiza acceso de usuarios, requerimientos, compras, cotizaciones y transacciones mediante servicios separados.

## Recorrido recomendado del código

- [Requerimientos](microservice-requerimientos-bienes/src/main/java/com/microservice/requerimientos/bienes): seguir `controllers` → `service` → `repository`; revisar `dto`, `mapper` y `entities`.
- [Compras y cotizaciones](microservice-comprasycotizaciones/src/main/java): coordinación de operaciones de procura.
- [Transacciones](microservice-transacciones/src/main/java): tratamiento de las operaciones asociadas.
- [Autenticación y usuarios](microservice-authusuarios/src/main/java): acceso, usuarios y seguridad.
- [Gateway](microservice-gateway/src/main/java) y [configuración de rutas](microservice-config/src/main/resources/configurations): entrada a los servicios.
- [Eureka](microservice-eureka/src/main/java): descubrimiento de servicios.

Para evaluar una funcionalidad, empieza en un controlador de requerimientos, sigue la llamada al servicio y al repositorio y revisa cómo los DTO y mapeadores separan la entrada/salida de las entidades. Después consulta los clientes de otros servicios.

## Herramientas y configuración

Java, Spring Boot, Spring Security, Spring Cloud, OpenFeign, Eureka y MySQL. Las versiones declaradas están en los `pom.xml` de cada módulo y en el POM raíz.

Los YAML de configuración usan referencias a variables de entorno para credenciales y direcciones de infraestructura. El historial original, los scripts SQL empresariales y la configuración operativa Docker no forman parte de esta copia. La estructura y el código Java de los servicios se conservan.

## Alcance de esta copia

Código del proyecto preparado para revisión técnica. Se conserva la estructura y la lógica del trabajo original; se retiraron credenciales, datos de contacto privados, archivos de operación e historial Git. No se incluye una base de datos con información real ni un entorno de producción. Esta entrega permite leer el código; no certifica un despliegue ni ejecución completa.

Las variables necesarias están enumeradas, sin valores, en [`.env.example`](.env.example). Los secretos se definen fuera del repositorio. Las variables con prefijo `NEXT_PUBLIC_` o `VITE_` son visibles en el navegador y no deben contener contraseñas ni claves privadas. Las cadenas ficticias de pruebas y desarrollo están identificadas como tales y no son credenciales de producción.

La ausencia de una licencia de código abierto no concede permiso general para reutilizar el producto. Esta copia conserva las atribuciones existentes; la publicación y una licencia de reutilización se deciden por separado.
