package com.myapp.util;

import javax.servlet.http.HttpServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection extends HttpServlet {

    private Connection connection;

    public Connection conn() {
        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/pharmacy",
                    "postgres",
                    "suBa4net");

            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            log("Database identification error", e);
            throw new RuntimeException(e);
        }
    }
}