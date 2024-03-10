package com.example.devoir_jsf.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {
//    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee?serverTimezone=UTC";
//    private static final String USER = "taha";
//    private static final String PASS = "password";

    private static Connection connection = null;

    public DbUtil() {
    }

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }
        try {
            String databaseUrl = System.getenv("DATABASE_URL");
            URI dbUri = new URI(databaseUrl);
            String username = dbUri.getUserInfo().split(":")[0];
            String password = dbUri.getUserInfo().split(":")[1];
            String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?sslmode=require";

            return DriverManager.getConnection(dbUrl, username, password);
        } catch (URISyntaxException | SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    // You can also add a closeConnection method if you want to close the connection
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                // Handle closing error
            }
        }
    }
}
