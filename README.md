📄 README – Proyecto Java: API REST de Facturación
🧩 Alcance del proyecto
Este proyecto corresponde a la parte de Java con Spring Boot del trabajo final. Desarrolla una API REST para administrar ventas de un comercio de clases y servicios. Incluye:

Entidades JPA: Cliente, Producto, Comprobante, LineaComprobante.

Arquitectura de 3 capas: Controller, Service, Repository.

Endpoints CRUD para clientes y productos.

Endpoint POST /api/comprobantes para crear una venta con validaciones:

Cliente existente.

Productos existentes.

Stock suficiente.

Reducción automática del stock.

Precio histórico en el comprobante (no se modifica si el producto cambia después).

Obtención de la fecha desde http://worldclockapi.com con fallback a LocalDateTime.now().

Respuestas con códigos de estado HTTP (200, 201, 404, 409).

Base de datos H2 en memoria.

Inicialización de datos con data.sql (clientes, productos, packs, cursos).

Generación de JAR ejecutable con Maven.

📁 Estructura de archivos del proyecto Java
text
FacturacionEntregaProyectoFinalMareco/
├── pom.xml
├── README.md
├── src/
│   ├── main/
│   │   ├── java/com/facturacion/
│   │   │   ├── FacturacionApplication.java
│   │   │   ├── controller/
│   │   │   ├── service/
│   │   │   ├── repository/
│   │   │   ├── entity/
│   │   │   ├── dto/
│   │   │   └── util/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── data.sql
│   └── test/
└── target/ (se genera al compilar)
⚙️ Requisitos previos
Java 21 (o superior) instalado.

Maven 3.6+ (opcional, si se usa el JAR ya compilado no es necesario).

Postman (o cualquier cliente REST) para probar los endpoints.

🚀 Instrucciones de ejecución
Opción 1: Ejecutar el JAR directamente (recomendado para entrega)
bash
java -jar FacturacionEntregaProyectoFinalMareco-1.0.0.jar
La aplicación se levantará en http://localhost:8080.

Opción 2: Compilar y ejecutar con Maven
bash
mvn clean install
mvn spring-boot:run
🧪 Endpoints para probar con Postman
Clientes
Método	Endpoint	Ejemplo de body
GET	/api/clientes	–
GET	/api/clientes/1	–
POST	/api/clientes	{"nombre":"Juan","email":"juan@mail.com"}
PUT	/api/clientes/1	{"nombre":"JuanP","email":"juan@mail.com"}
Productos
Método	Endpoint	Ejemplo
GET	/api/productos	–
GET	/api/productos/1	–
POST	/api/productos	{"nombre":"Clase","precio":9999,"stock":10}
PUT	/api/productos/1	{"nombre":"Clase Jazz","precio":10500,"stock":8}
Crear comprobante de venta (el más importante)
URL: POST http://localhost:8080/api/comprobantes

Body (JSON):

json
{
"cliente": { "clienteId": 1 },
"lineas": [
{ "cantidad": 2, "producto": { "productoId": 1 } },
{ "cantidad": 1, "producto": { "productoId": 3 } }
]
}
Respuesta exitosa (201 Created):

json
{
"comprobanteId": 1,
"fecha": "2026-03-30T04:13:47",
"clienteNombre": "Fabian Mareco",
"lineas": [...],
"total": 59998.0,
"cantidadTotalProductos": 3
}
Errores posibles (409 Conflict):

"Cliente no existe"

"Producto con ID X no existe"

"Stock insuficiente para ..."

📦 Generación del JAR final
bash
mvn clean package
El JAR se generará en target/FacturacionEntregaProyectoFinalMareco-1.0.0.jar.
Si la consigna pide un nombre exacto sin versión, renombrarlo:

bash
mv target/FacturacionEntregaProyectoFinalMareco-1.0.0.jar target/FacturacionEntregaProyectoFinalMareco.jar
🗃️ Datos precargados (data.sql)
Al iniciar la aplicación, se crean automáticamente:

5 clientes (con nombres y emails).

14 clases de movimiento a $9.999 c/u (Danza Jazz, Clásica, Contemporánea, Yoga, Tango, Urbanos, Flexibilidad, Expresión Corporal, Folclóricas, Pilates, Flamenco, Movilidad Articular, Danza para Niños, Improvisación).

Packs de clases: 4 clases (
30.000
)
,
8
c
l
a
s
e
s
(
30.000),8clases(50.000), Pase Mensual (
70.000
)
,
P
a
s
e
A
n
u
a
l
(
70.000),PaseAnual(650.000).

Productos de nutrición: 3 cursos sueltos (
20.000
c
/
u
)
,
P
a
c
k
3
c
u
r
s
o
s
(
20.000c/u),Pack3cursos(50.000), Asesoría personalizada (
40.000
)
,
P
a
c
k
C
u
r
s
o
s
+
A
s
e
s
o
r
ı
ˊ
a
(
40.000),PackCursos+Asesor
ı
ˊ
a(75.000).

✅ Cumplimiento de consignas del proyecto final Java
Entidades, repositorios JPA, servicios y controladores.

Validaciones al crear comprobante (cliente, productos, stock).

Reducción de stock.

Precio histórico en comprobante.

Consumo de servicio externo para fecha con fallback.

Respuestas con códigos HTTP adecuados.

Script de inicialización de datos.

Proyecto empaquetado como JAR ejecutable.

Documentación con README.

🧑‍💻 Autor
Fabian Mareco – Proyecto final para diplomatura Full Stack.