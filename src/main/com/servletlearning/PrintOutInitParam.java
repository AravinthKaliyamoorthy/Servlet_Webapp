package com.servletlearning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintOutInitParam extends HttpServlet {
    
    private static final Logger logger = LoggerFactory.getLogger(PrintOutInitParam.class);
    ServletConfig config;

    public void init(ServletConfig config){
        this.config = config;
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Entered service method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Init Param Values : "+ config.getInitParameter("name2") +"</h1>");
        out.println("</body></html>");
        logger.info("Exited service method");
    }

}
