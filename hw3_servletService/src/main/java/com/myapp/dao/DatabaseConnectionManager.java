package com.myapp.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnectionManager {
    private String url;
    private String username;
    private String password;

    public DatabaseConnectionManager() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("database.properties")) {
            if (input == null) {
                throw new FileNotFoundException("database.properties not found in the classpath");
            }
            Properties prop = new Properties();
            prop.load(input);

            this.url = prop.getProperty("url");
            this.username = prop.getProperty("username");
            this.password = prop.getProperty("password");
        } catch (FileNotFoundException e) {
            System.err.println("Error: database.properties not found");
            throw new RuntimeException("Error: database.properties not found", e);
        } catch (IOException e) {
            System.err.println("Error: failed to load database.properties");
            throw new RuntimeException("Error: failed to load database.properties", e);
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error: failed to close database connection");
                throw new RuntimeException("Error: failed to close database connection", e);
            }
        }
    }
}