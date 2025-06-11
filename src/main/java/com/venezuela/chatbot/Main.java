package com.venezuela.chatbot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot bot = new ChatBot();

        // Mostrar el mensaje de bienvenida y el menú inicial
        System.out.println("ChatBot: " + bot.getInitialMessage());

        // Iniciar el bucle de conversación
        while (true) {
            System.out.print("Tú: ");
            String userInput = scanner.nextLine();

            if ("salir".equalsIgnoreCase(userInput.trim())) {
                System.out.println("ChatBot: ¡Hasta luego! Fue un placer ayudarte.");
                break;
            }
            
            // Si el usuario quiere volver al menú
            if ("menu".equalsIgnoreCase(userInput.trim())) {
                System.out.println("ChatBot: " + bot.getInitialMessage());
                continue;
            }

            String response = bot.getResponse(userInput);
            System.out.println("ChatBot: " + response);
        }

        scanner.close();
    }
}
