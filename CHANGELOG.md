# Changelog

## [Unreleased] - Junio 11, 2025
### Mejoras

- Aplicación de principios de Clean Code:
  - Extracción de constantes para todos los mensajes de usuario.
  - Separación de responsabilidades:
    - Clase `User` para datos de usuario.
    - Enum `ConversationState` para estados de la conversación.
  - Uso de `Logger` en lugar de `System.out` para registros.
  - Métodos pequeños y descriptivos para cada paso del flujo:
    - `processMainMenu`, `processNameInput`, `processEmailInput`, `processPaymentProof`.
  - Eliminación de código comentado e innecesario.
- Integración de base de datos SQLite para gestión de cuentas:
  - Dependencia `sqlite-jdbc` añadida en el `pom.xml`.
  - Clases `Account` y `DatabaseHelper` para CRUD de cuentas (listar, seleccionar y marcar como vendida).
  - Inicialización automática de la base de datos `data.db` con datos de prueba.
- Ampliación del flujo de conversación:
  - Nuevo estado `SALES_SELECTING_ACCOUNT` en `ConversationState`.
  - Métodos `buildAccountSelectionMessage` y `processAccountSelection` en `ChatBot`.
- Empaquetado ejecutable:
  - Configuración de `maven-shade-plugin` para generar un JAR con todas las dependencias.

---
*Este archivo documenta los cambios de la versión actual antes de su lanzamiento.*
