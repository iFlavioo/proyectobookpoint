# proyectobookpoint
# Sistema de Gestión de Microservicios — BookPoint Chile

## Integrantes del Equipo
* **Estudiante 1:** Sebastian Escalona
* **Estudiante 2:** Cristobal Olivares
* **Estudiante 3:** Flavio Bustos

**Número de Equipo Asignado:** [Ejemplo: Equipo N° 02]  
**Nombre de la Aplicación:** BookPoint Chile 

## Descripción del Proyecto
BookPoint Chile es una plataforma web y de tiendas físicas modernizada mediante una arquitectura basada en microservicios independientes. El sistema mitiga los problemas de latencia, desacoplamiento, stock desactualizado y cuellos de botella del antiguo sistema monolítico, garantizando alta disponibilidad y escalabilidad horizontal para las operaciones de las sucursales de Concepción, Temuco y La Serena, además de robustecer el canal de ventas online.


## Arquitectura y Tecnologías
La solución está compuesta por servicios desacoplados que se comunican mediante APIs REST:
* **Backend:** Node.js (Express) / Java (Spring Boot) / Python (FastAPI) *[Mantener solo el que uses]*
* **Bases de Datos:** PostgreSQL / MongoDB (Bases de datos independientes por microservicio para cumplir con el patrón arquitectónico).
* **Orquestación:** Docker y Docker Compose para el entorno local.

## Funcionalidades Implementadas por Microservicio

### 1. Microservicio de Autenticación y Usuarios
* **Administrador:** Gestión completa de usuarios de la plataforma, asignación y modificación de permisos según el rol institucional.
* **Clientes:** Creación de cuenta en la plataforma web, inicio de sesión seguro (JWT) y administración de perfiles/direcciones.

### 2. Microservicio de Inventario y Bodega 
* **Encargado de Bodega:** Administración del stock central, registro de ingresos de productos desde proveedores editoriales, control de stock mínimo con alertas de reposición automática y gestión de ubicaciones internas.
* **Jefe de Sucursal:** Ajustes de inventario físico, solicitudes de reposición a la bodega central y consultas de disponibilidad en tiempo real en otras sucursales.

### 3. Microservicio de Ventas y POS
* **Asistente de Ventas:** Registro de transacciones presenciales en caja, aplicación de cupones, promociones o convenios estudiantiles y emisión automática de boletas/facturas electrónicas.
* **Clientes (Web):** Navegación del catálogo multimedia con filtros (autor, editorial, género, precio), carrito de compras integrado, procesamiento de pagos online y registro de reseñas/calificaciones.

### 4. Microservicio de Logística y Despachos 
* **Área de Logística:** Gestión y asignación de envíos a domicilio, optimización de rutas de distribución, coordinación y seguimiento de traslados/transferencias de stock entre sucursales y actualización del estado de los envíos internos y externos.

