package com.venezuela.chatbot;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper {
    private static final String DB_URL = "jdbc:sqlite:data.db";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("CREATE TABLE IF NOT EXISTS accounts (id INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT NOT NULL, password TEXT NOT NULL, sold INTEGER NOT NULL DEFAULT 0)");
            // Sembrar datos iniciales si la tabla está vacía
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM accounts");
            if (rs.next() && rs.getInt(1) == 0) {
                stmt.execute("INSERT INTO accounts(email,password) VALUES('user1@example.com','pass1')");
                stmt.execute("INSERT INTO accounts(email,password) VALUES('user2@example.com','pass2')");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Account> getAvailableAccounts() {
        List<Account> list = new ArrayList<>();
        String sql = "SELECT id, email, password FROM accounts WHERE sold=0";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Account(rs.getInt("id"), rs.getString("email"), rs.getString("password")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static Account getAccountById(int id) {
        String sql = "SELECT email, password FROM accounts WHERE id=? AND sold=0";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Account(id, rs.getString("email"), rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void markAccountAsSold(int id) {
        String sql = "UPDATE accounts SET sold=1 WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
