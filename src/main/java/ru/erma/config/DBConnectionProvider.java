package ru.erma.config;

import java.sql.Connection;
import java.sql.DriverManager;

public record DBConnectionProvider(String url, String username, String password) {
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}