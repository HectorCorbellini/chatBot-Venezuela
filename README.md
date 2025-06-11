# ChatBot Venezuela

Un simple chatbot en Java que guía al usuario a través de un flujo de ventas interactivo en línea de comandos.

## Características

- Interfaz de línea de comandos con menú de opciones
- Flujos de conversación: bienvenida, métodos de pago, proceso de compra y entrega a asesor
- Clean Code: constantes, User, ConversationState y Logger
- Fácil de extender con nuevas funcionalidades

## Requisitos

- Java 11 o superior
- Maven 3.6 o superior

## Cómo ejecutar

1. Clona el repositorio:
   ```bash
   git clone https://github.com/HectorCorbellini/chatBot-Venezuela.git
   ```
2. Navega al directorio del proyecto:
   ```bash
   cd chatBot-Venezuela
   ```
3. Genera el paquete ejecutable con Maven:
   ```bash
   mvn clean package
   ```
4. Ejecuta el JAR generado:
   ```bash
   java -jar target/chatbot-1.0-SNAPSHOT-shaded.jar
   ```
5. Demo completo con script:
   ```bash
   chmod +x run.sh
   ./run.sh
   ```

## Flujos de conversación

- **Menu principal**: al iniciar, el bot muestra:
  1. Ver métodos de pago
  2. Iniciar una compra
- **Métodos de pago**: escribe `1` para ver opciones de pago y regresa al menú.
- **Iniciar compra**: escribe `2` y sigue estos pasos:
  1. Proporciona tu nombre completo.
  2. Proporciona tu correo electrónico.
  3. Realiza el pago y escribe `comprobante enviado` para enviar comprobante.
- **Volver al menú**: en cualquier momento, escribe `menu`.
- **Salir**: escribe `salir` para terminar la conversación.

## Cómo contribuir

1. Haz un fork del proyecto
2. Crea una rama para tu característica (`git checkout -b feature/nueva-caracteristica`)
3. Haz commit de tus cambios (`git commit -am 'Añade nueva característica'`)
4. Haz push a la rama (`git push origin feature/nueva-caracteristica`)
5. Crea un nuevo Pull Request

## Licencia

Este proyecto está bajo la Licencia MIT.
