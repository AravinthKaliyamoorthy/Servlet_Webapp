package com.webapp.service;

import jakarta.servlet.RequestDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class UserService {

    Logger logger = LoggerFactory.getLogger(UserService.class);

    Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;

    public UserService(){
        logger.info("Inside Constructor");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet_webappdb","root","password");
            stmt = con.createStatement();
            logger.info("Connection established successfully");
            logger.info("Statement created successfully");
            logger.info("Exited Constructor");
        }
        catch (Exception e){
            logger.error("Exception occurred while connecting to database");
            e.printStackTrace();
        }
    }

    public String checkLogin(String umail, String upwd){
        logger.info("Entered checkLogin");
        String status = "";
        try{
            rs = stmt.executeQuery("SELECT * FROM users WHERE umail = '"+umail+"' AND upwd = '"+upwd+"'");
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

    public String registerUser(String uname, String umail, String upwd, String ucpwd){
        logger.info("Entered registration");
        String status = "";
        try{
            rs = stmt.executeQuery("SELECT * FROM users WHERE umail = '"+umail+"'");
            boolean b = rs.next();
            if(b){
                status = "existed";
                logger.info("User already existed");
            }
            else{
                if(!upwd.equals(ucpwd)){
                    status = "failed";
                }
                else{
                    stmt.executeUpdate("INSERT INTO users VALUES('"+uname+"', '"+umail+"', '"+upwd+"')");
                    status = "success";
                    logger.info("Query executed successfully");
                    logger.info("Exited registration");
                }
            }
        }
        catch (Exception e){
            logger.error("Exception occurred while executing query");
            e.printStackTrace();
        }
        return status;
    }

}
