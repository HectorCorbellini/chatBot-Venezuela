package com.venezuela.chatbot;

public class Account {
    private int id;
    private String email;
    private String password;

    public Account(int id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.format("ID: %d - Email: %s\nContraseña: %s", id, email, password);
    }
}
