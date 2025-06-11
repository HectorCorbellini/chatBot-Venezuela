package com.venezuela.chatbot;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ChatBot {
    private Map<String, String[]> responses;
    private Random random;

    public ChatBot() {
        this.responses = new HashMap<>();
        this.random = new Random();
        initializeResponses();
    }

    private void initializeResponses() {
        // Saludos
        responses.put("hola", new String[]{"¡Hola! ¿En qué puedo ayudarte hoy?", "¡Saludos! ¿Cómo estás?"});
        responses.put("buenos días", new String[]{"¡Buenos días! ¿En qué puedo ayudarte?", "¡Hola! ¿Qué tal tu día?"});
        
        // Preguntas comunes
        responses.put("cómo estás", new String[]{"¡Estoy funcionando perfectamente, gracias por preguntar!", "¡Todo bien por aquí! ¿Y tú?"});
        responses.put("quién eres", new String[]{"Soy un chatbot diseñado para ayudar con información sobre Venezuela", "Soy tu asistente virtual, ¿en qué te ayudo?"});
        
        // Despedidas
        responses.put("adiós", new String[]{"¡Hasta luego! Que tengas un excelente día.", "¡Nos vemos pronto!"});
        
        // Respuesta por defecto
        responses.put("default", new String[]{"No estoy seguro de entender. ¿Podrías reformular tu pregunta?", "Interesante, ¿puedes contarme más?"});
    }

    public String getResponse(String input) {
        input = input.toLowerCase().trim();
        
        // Buscar una respuesta coincidente
        for (Map.Entry<String, String[]> entry : responses.entrySet()) {
            if (input.contains(entry.getKey())) {
                String[] possibleResponses = entry.getValue();
                return possibleResponses[random.nextInt(possibleResponses.length)];
            }
        }
        
        // Si no hay coincidencia, devolver una respuesta por defecto
        return responses.get("default")[random.nextInt(responses.get("default").length)];
    }
}
