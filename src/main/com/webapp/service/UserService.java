package com.webapp.service;

import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public String checkLogin(String username, String password, ServletContext servletContext){
        logger.info("Entered checkLogin");
        String status = "";
        try{
            Connection connection = (Connection) servletContext.getAttribute("databaseConnection");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE student_mail = ? AND password = ?");
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            boolean b = rs.next();
            if(b){
                status = "success";
            }
            else{
                status = "failed";
            }
            logger.info("Query executed successfully");
        }
        catch (Exception e){
            logger.error("Exception occurred while executing query");
            e.printStackTrace();
        }
        logger.info("Exited checkLogin");
        return status;
    }

    public String registerUser(String username, String mail, String password, String confirmPassword, ServletContext servletContext){
        logger.info("Entered registration");
        String status = "";
        try{
            Connection connection = (Connection) servletContext.getAttribute("databaseConnection");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE student_mail = ? AND password = ?");
            stmt.setString(1, mail);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            boolean b = rs.next();
            if(b){
                status = "existed";
                logger.info("User already existed");
            }
            else if (!password.equals(confirmPassword)) {
                status = "passwordsDoNotMatch";
            }
            else{
                stmt.executeUpdate("INSERT INTO users VALUES('"+username+"', '"+mail+"', '"+password+"')");
                status = "success";
                logger.info("Query executed successfully");
                logger.info("Exited registration");
            }
        }
        catch (Exception e){
            logger.error("Exception occurred while executing query");
            e.printStackTrace();
        }
        return status;
    }

    public static String courseRegistration(String studentName, String studentQualification, String studentGender, String[] studentCourse, String branch, String studentAddress){
        logger.info("Entered courseRegistration");
        String status = "";
        try{
            if(studentName.isEmpty() || studentQualification.isEmpty() || studentGender.isEmpty() || studentCourse.length == 0 || branch.isEmpty() || studentAddress.isEmpty()) {
                status = "emptyFields";
            }
            else {
                logger.info("Received required details from student");
                status = "success";
            }
        }
        catch (Exception e){
            status = "failed";
            logger.error("Exception occurred while courseRegistration", e.getMessage());
            e.printStackTrace();
        }
        logger.info("Exited courseRegistration");
        return status;
    }

}
