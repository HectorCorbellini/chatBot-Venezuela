#!/usr/bin/env bash
set -e

# Demo script: compila y ejecuta un flujo completo de ChatBot

echo "Construyendo el proyecto..."
mvn clean package

echo "Ejecutando ChatBot demo..."
java -jar target/chatbot-1.0-SNAPSHOT.jar <<EOF
2
Usuario Demo
demo@ejemplo.com
1
comprobante enviado
salir
EOF
