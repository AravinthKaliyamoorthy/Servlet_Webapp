package com.webapp.servlets;

import com.webapp.service.StudentService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateStudent", value = "/updateStudent")
public class UpdateStudent extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(UpdateStudent.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Entered doGet method");
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String studentName = (String) session.getAttribute("studentName");
        String studentEmail = request.getParameter("studentEmail");
        String studentNames = request.getParameter("studentName");
        logger.info("Student email: {}", studentEmail);
        logger.info("Student name: {}", studentName);
        logger.info("Student name: {}", studentNames);

        ServletContext context = getServletContext();
        try {
            StudentService.updateStudent(studentEmail, studentName, context);
            logger.info("Student updated successfully");
        } catch (SQLException e) {
            logger.error("Error occurred while updating student:", e);
            throw new RuntimeException(e);
        }
    }

}