package com.venezuela.chatbot;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("¡Bienvenido al ChatBot de Venezuela!");
        System.out.println("Escribe 'salir' para terminar la conversación.\n");
        
        Scanner scanner = new Scanner(System.in);
        ChatBot bot = new ChatBot();
        
        while (true) {
            System.out.print("Tú: ");
            String userInput = scanner.nextLine();
            
            if ("salir".equalsIgnoreCase(userInput.trim())) {
                System.out.println("ChatBot: ¡Hasta luego! Fue un placer ayudarte.");
                break;
            }
            
            String response = bot.getResponse(userInput);
            System.out.println("ChatBot: " + response);
        }
        
        scanner.close();
    }
}
