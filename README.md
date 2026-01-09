# ATLAS · Ejemplo PFC · Biblioteca (MVP)

Este repositorio acompaña un ejemplo completo del método ATLAS (Fases 0–5).
En **Fase 3 (Iteración 0+1)** se prepara la base de datos y un proyecto Spring Boot base.

## Estructura
- `db/schema.sql` → Esquema MySQL mínimo del MVP
- `db/data.sql` → Datos de ejemplo (opcional)
- `backend/biblioteca/` → Proyecto Spring Boot (Maven)

## Requisitos
- Java 17
- Maven
- MySQL 8+

## Cómo preparar la BD
1. Ejecuta `db/schema.sql`
2. (Opcional) Ejecuta `db/data.sql`

## Cómo arrancar el backend
Desde `backend/biblioteca`:
```bash
mvn spring-boot:run
```

Comprobar salud:
- `http://localhost:8080/actuator/health`

> Nota: En iteraciones posteriores se añaden entidades, repositorios y endpoints del MVP.


## Iteración 2 (persistencia)
- Añadidas entidades JPA y repositorios.
- Endpoint `/api/ping` devuelve `{status:"ok", db:1}` si hay conexión a BD.

## Verificación rápida tras subir
Arranca: mvn spring-boot:run
Comprueba:
http://localhost:8080/actuator/health → UP
http://localhost:8080/api/ping → {"status":"ok","db":1}
