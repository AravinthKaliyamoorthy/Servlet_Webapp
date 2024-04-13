package com.webapp.servlets;

import com.webapp.beans.Student;
import com.webapp.service.StudentService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@WebServlet("/studentCheck")
public class StudentCheck extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(StudentCheck.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        logger.info("Entered doPost method");
        response.setContentType("text/html");
        String studentMail = request.getParameter("studentMail");

        ServletContext context = getServletContext();

        StudentService  studentService = new StudentService();
        try{
            Student student = studentService.isValidStudent(studentMail, context);
            if(student != null){
                logger.info("StudentService object created");
                // Convert student details to query string parameters
                HttpSession session = request.getSession();
                session.setAttribute("studentName", student.getStudentName());
                String queryString = "studentId=" + student.getStudentId() +
                        "&name=" + URLEncoder.encode(student.getStudentName(), "UTF-8") +
                        "&email=" + URLEncoder.encode(student.getStudentEmail(), "UTF-8");
                response.sendRedirect("./updateStudent.html?" + queryString);
            }
            else{
                PrintWriter printWriter = response.getWriter();
                printWriter.println("<h2>Invalid StudentId</h2>");
                response.sendRedirect("./studentCheck.html");
            }
        }
        catch (Exception e){
            logger.error("Error occured while trying to get StudentService object:",e);
        }
    }

}