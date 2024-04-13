package com.webapp.service;

import com.webapp.beans.Student;
import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class StudentService {

    public static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(){
        logger.info("StudentService object created");
    }

    public static Student isValidStudent(String studentMail, ServletContext servletContext) throws SQLException {
        logger.info("Entered isValidStudent method");

        Connection connection = (Connection) servletContext.getAttribute("databaseConnection");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE student_mail = ?");
            preparedStatement.setString(1, studentMail);
            ResultSet resultSet = preparedStatement.executeQuery();
            logger.info("Query executed");

            if (resultSet.next()) {
                return new Student(resultSet.getInt("id"),
                        resultSet.getString("student_name"),
                        resultSet.getString("student_mail"),
                        resultSet.getString("password"));
            } else {
                logger.info("No student found with email: " + studentMail);
                return null;
            }
        } catch (SQLException e) {
            logger.error("Error occurred while executing query:", e);
            throw e;
        }
    }

    public static void updateStudent(String studentMail, String studentName, ServletContext servletContext) throws SQLException {
        logger.info("Entered updateStudent method");

        Connection connection = (Connection) servletContext.getAttribute("databaseConnection");

        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET student_mail = ? WHERE student_name = ?");
        preparedStatement.setString(1, studentMail);
        preparedStatement.setString(2, studentName);
        preparedStatement.executeUpdate();
        logger.info("Query executed");
    }

}