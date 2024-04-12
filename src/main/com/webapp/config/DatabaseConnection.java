package com.webapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    public static final Logger logger = LoggerFactory.getLogger(DatabaseConnection.class);

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        logger.info("Entered getConnection() method");
        Class.forName("com.mysql.cj.jdbc.Driver");
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_webappdb","root","password");
        }
        logger.info("Exited getConnection() method");
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}

