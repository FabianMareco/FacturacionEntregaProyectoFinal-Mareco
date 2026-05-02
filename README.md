# 🧾 MUEVETE – API REST de Facturación con Spring Boot

API REST desarrollada en Java con Spring Boot para administrar ventas
de clases, packs y servicios de nutrición. Permite crear comprobantes
de venta con validaciones de stock y precio histórico.

## 🚀 Tecnologías

| Tecnología | Uso |
|---|---|
| Java 21 | Lenguaje principal |
| Spring Boot 3 | Framework web y DI |
| Spring Data JPA | Persistencia y repositorios |
| H2 Database | Base de datos en memoria |
| Maven | Build y empaquetado |
| WorldClockAPI | Fecha/hora externa con fallback |

## ✨ Funcionalidades

- 📋 CRUD completo de clientes y productos
- 🧾 Creación de comprobantes de venta con:
  - Validación de cliente y productos existentes
  - Validación y reducción automática de stock
  - Precio histórico (no se ve afectado por cambios posteriores al producto)
  - Fecha obtenida desde `worldclockapi.com` con fallback a `LocalDateTime.now()`
- 💾 Base de datos H2 inicializada automáticamente con `data.sql`
- 📦 Empaquetado como JAR ejecutable

## 📁 Estructura del proyecto
```
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
└── target/
```
## ⚙️ Requisitos

- Java 21+
- Maven 3.6+ *(solo si se compila desde fuente)*
- Postman u otro cliente REST para probar los endpoints

## 🔧 Cómo ejecutar

### Opción 1 — JAR precompilado

```bash
java -jar FacturacionEntregaProyectoFinalMareco-1.0.0.jar
```

### Opción 2 — Compilar con Maven

```bash
mvn clean install
mvn spring-boot:run
```

La app queda disponible en `http://localhost:8080`.

## 🧪 Endpoints

### Clientes

| Método | Endpoint | Body |
|---|---|---|
| GET | `/api/clientes` | – |
| GET | `/api/clientes/{id}` | – |
| POST | `/api/clientes` | `{"nombre":"Juan","email":"juan@mail.com"}` |
| PUT | `/api/clientes/{id}` | `{"nombre":"JuanP","email":"juan@mail.com"}` |

### Productos

| Método | Endpoint | Body |
|---|---|---|
| GET | `/api/productos` | – |
| GET | `/api/productos/{id}` | – |
| POST | `/api/productos` | `{"nombre":"Clase","precio":9999,"stock":10}` |
| PUT | `/api/productos/{id}` | `{"nombre":"Clase Jazz","precio":10500,"stock":8}` |

### Crear comprobante de venta

**`POST /api/comprobantes`**

```json
{
  "cliente": { "clienteId": 1 },
  "lineas": [
    { "cantidad": 2, "producto": { "productoId": 1 } },
    { "cantidad": 1, "producto": { "productoId": 3 } }
  ]
}
```

**Respuesta exitosa `201 Created`:**

```json
{
  "comprobanteId": 1,
  "fecha": "2026-03-30T04:13:47",
  "clienteNombre": "Fabian Mareco",
  "lineas": [...],
  "total": 59998.0,
  "cantidadTotalProductos": 3
}
```

**Errores posibles `409 Conflict`:**
- `"Cliente no existe"`
- `"Producto con ID X no existe"`
- `"Stock insuficiente para ..."`

## 📦 Generar el JAR

```bash
mvn clean package
```

El JAR se genera en `target/FacturacionEntregaProyectoFinalMareco-1.0.0.jar`.

Para renombrarlo si es necesario:

```bash
mv target/FacturacionEntregaProyectoFinalMareco-1.0.0.jar \
   target/FacturacionEntregaProyectoFinalMareco.jar
```

## 🗃️ Datos precargados

Al iniciar la aplicación se cargan automáticamente via `data.sql`:

**5 clientes** con nombre y email.

**14 clases de movimiento** a $9.999 c/u: Danza Jazz, Clásica, Contemporánea,
Yoga, Tango, Urbanos, Flexibilidad, Expresión Corporal, Folclóricas, Pilates,
Flamenco, Movilidad Articular, Danza para Niños, Improvisación.

**Packs de clases:** 4 clases ($30.000), 8 clases ($50.000),
Pase Mensual ($70.000), Pase Anual ($650.000).

**Nutrición:** 3 cursos sueltos ($20.000 c/u), Pack 3 cursos ($50.000),
Asesoría personalizada ($40.000), Pack Cursos + Asesoría ($75.000).

## 🧑‍💻 Autor

Fabian Mareco
