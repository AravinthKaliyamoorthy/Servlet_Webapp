package com.webapp.servlets;

import com.webapp.service.UserService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "com.webapp.servlets.CoursesRegistrationServlet", value = "/coursesregistration")
public class CoursesRegistrationServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(CoursesRegistrationServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Entered doPost method");
        String studentName = request.getParameter("studentName");
        String studentQualification = request.getParameter("studentQualification");
        String studentGender = request.getParameter("studentGender");
        String[] studentCourse = request.getParameterValues("studentCourse");
        String branch = request.getParameter("branch");
        String studentAddress = request.getParameter("studentAddress");

        String status = UserService.courseRegistration(studentName, studentQualification, studentGender, studentCourse, branch, studentAddress);

        if(status.equals("success")){
            response.sendRedirect("./index.html");
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("<h1>Please provide all required details!</h1>");
            RequestDispatcher rd = request.getRequestDispatcher("coursesregistrationform.html");
            rd.include(request, response);
            out.close();
            request.setAttribute("message", "Course Registration Failed");
        }
        logger.info("Exited doPost method");
    }
    
}
