package com.webapp.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class MyServletContextListener implements ServletContextListener {

    Logger logger = LoggerFactory.getLogger(MyServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("Entered contextInitialized method");
        ServletContext context = sce.getServletContext();
        try {
            // Use DatabaseConnection class or directly establish connection
            Connection connection = DatabaseConnection.getConnection();
            context.setAttribute("databaseConnection", connection);
        } catch (SQLException | ClassNotFoundException e) {
            logger.error("Error in establishing database connection");
            e.printStackTrace();
        }
        logger.info("Exited contextInitialized method");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("Entered contextDestroyed method");
        ServletContext context = sce.getServletContext();
        Connection connection = (Connection) context.getAttribute("databaseConnection");
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error in closing database connection", e.getMessage());
                e.printStackTrace();
            }
        }
        logger.info("Exited contextDestroyed method");
    }
}

