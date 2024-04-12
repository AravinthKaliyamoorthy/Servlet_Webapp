package com.webapp.service;

import jakarta.servlet.ServletContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public String checkLogin(String username, String password, ServletContext servletContext){
        logger.info("Entered checkLogin");
        String status = "";
        try{
            Connection connection = (Connection) servletContext.getAttribute("databaseConnection");
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE umail = ? AND upwd = ?");
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
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE umail = ? AND upwd = ?");
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

}
