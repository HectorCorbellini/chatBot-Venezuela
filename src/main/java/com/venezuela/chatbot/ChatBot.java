package com.venezuela.chatbot;

import java.util.logging.Logger;
import java.util.logging.Level;
import com.venezuela.chatbot.DatabaseHelper;
import com.venezuela.chatbot.Account;
import java.util.List;

public class ChatBot {

    private static final Logger LOGGER = Logger.getLogger(ChatBot.class.getName());

    private static final String MENU_PROMPT = "Por favor, elige una de las siguientes opciones para continuar:\n"
            + "1. Ver métodos de pago\n"
            + "2. Iniciar una compra";

    private static final String GREETING_MESSAGE = "¡Hola! Soy tu asistente virtual de ventas. Estoy aquí para ayudarte.\n"
            + MENU_PROMPT;

    private static final String PAYMENT_METHODS_MESSAGE = "Aceptamos los siguientes métodos de pago:\n"
            + "- Transferencia Bancaria\n"
            + "- Pago Móvil\n"
            + "- Zelle";

    private static final String INVALID_OPTION_MESSAGE = "Opción no válida. Por favor, elige 1 o 2.";

    private static final String ASK_NAME_MESSAGE = "¡Excelente! Para comenzar con tu compra, por favor, indícame tu nombre completo.";

    private static final String ASK_EMAIL_MESSAGE = "Gracias, %s. Ahora, por favor, indícame tu correo electrónico.";

    private static final String PAYMENT_PROOF_REQUEST_MESSAGE = "Perfecto. Hemos guardado tus datos. Para completar la compra, realiza el pago y envía el comprobante respondiendo a este mensaje (escribe 'comprobante enviado').";

    private static final String THANKS_AND_HANDOFF_MESSAGE = "¡Gracias por tu compra! Hemos recibido tu comprobante. En breve, uno de nuestros asesores se pondrá en contacto contigo para finalizar los detalles.\n"
            + "Escribe 'menu' para volver al inicio.";

    private static final String REQUEST_PROOF_MESSAGE = "Por favor, envía tu comprobante para continuar. Escribe 'comprobante enviado' cuando lo hayas hecho.";

    private static final String PROOF_KEYWORD = "comprobante";

    private final User user;
    private ConversationState state;
    private Account selectedAccount;

    public ChatBot() {
        this.user = new User();
        this.state = ConversationState.GREETING;
    }

    public String getInitialMessage() {
        state = ConversationState.MAIN_MENU;
        return GREETING_MESSAGE;
    }

    public String getResponse(String input) {
        input = input.trim();
        switch (state) {
            case MAIN_MENU:
                return processMainMenu(input);
            case SALES_COLLECTING_NAME:
                return processNameInput(input);
            case SALES_COLLECTING_EMAIL:
                return processEmailInput(input);
            case SALES_SELECTING_ACCOUNT:
                return processAccountSelection(input);
            case SALES_AWAITING_PAYMENT_PROOF:
                return processPaymentProof(input);
            default:
                state = ConversationState.MAIN_MENU;
                return "No entiendo tu petición. Volvamos al menú principal.\n" + MENU_PROMPT;
        }
    }

    private String processMainMenu(String input) {
        switch (input) {
            case "1":
                state = ConversationState.MAIN_MENU;
                return PAYMENT_METHODS_MESSAGE + "\n\n¿Cómo más puedo ayudarte?\n" + MENU_PROMPT;
            case "2":
                state = ConversationState.SALES_COLLECTING_NAME;
                return ASK_NAME_MESSAGE;
            default:
                return INVALID_OPTION_MESSAGE;
        }
    }

    private String processNameInput(String name) {
        user.setName(name);
        state = ConversationState.SALES_COLLECTING_EMAIL;
        return String.format(ASK_EMAIL_MESSAGE, name);
    }

    private String processEmailInput(String email) {
        user.setEmail(email);
        state = ConversationState.SALES_SELECTING_ACCOUNT;
        LOGGER.log(Level.INFO, "Datos guardados: {0}", user);
        return buildAccountSelectionMessage();
    }

    private String buildAccountSelectionMessage() {
        List<Account> accounts = DatabaseHelper.getAvailableAccounts();
        if (accounts.isEmpty()) {
            return "Lo siento, no hay cuentas disponibles en este momento.";
        }
        StringBuilder sb = new StringBuilder("Estas son las cuentas disponibles:\n");
        for (Account acc : accounts) {
            sb.append(String.format("- ID: %d, Email: %s\n", acc.getId(), acc.getEmail()));
        }
        sb.append("Por favor, ingresa el ID de la cuenta que deseas adquirir.");
        return sb.toString();
    }

    private String processAccountSelection(String input) {
        try {
            int id = Integer.parseInt(input);
            Account acc = DatabaseHelper.getAccountById(id);
            if (acc == null) {
                return "ID no válido. Por favor selecciona un ID de la lista.";
            }
            selectedAccount = acc;
            DatabaseHelper.markAccountAsSold(id);
            state = ConversationState.SALES_AWAITING_PAYMENT_PROOF;
            LOGGER.log(Level.INFO, "Cuenta vendida: {0}", acc);
            return "Has seleccionado la cuenta:\n" + acc.toString()
                    + "\nAhora realiza el pago y escribe 'comprobante enviado' cuando lo hayas hecho.";
        } catch (NumberFormatException ex) {
            return "Por favor, ingresa un número de ID válido.";
        }
    }

    private String processPaymentProof(String input) {
        if (input.toLowerCase().contains(PROOF_KEYWORD)) {
            state = ConversationState.HANDOFF_TO_AGENT;
            return THANKS_AND_HANDOFF_MESSAGE;
        }
        return REQUEST_PROOF_MESSAGE;
    }
}
