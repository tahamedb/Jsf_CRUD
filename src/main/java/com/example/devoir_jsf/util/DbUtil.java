package com.example.devoir_jsf.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtil {

    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        } else {
            try {
                // PostgreSQL JDBC driver class
                Class.forName("org.postgresql.Driver");

                // Connection details
                String host = "cc3engiv0mo271.cluster-czrs8kj4isg7.us-east-1.rds.amazonaws.com";
                String database = "d31fehukfeqjil";
                String user = "ubi35vedhn7665";
                String port = "5432";
                String password = "pacaa553e6aea08be29de407ab72061b8a8a2e2504cc7c0a8bcea18ff8e2f4896";
                String dbUrl = "jdbc:postgresql://" + host + ':' + port + "/" + database;

                // Establishing the connection
                connection = DriverManager.getConnection(dbUrl, user, password);

            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException("Error connecting to the database", e);
            }
            return connection;
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                // Handle closing error
                e.printStackTrace();
            }
        }
    }
}
