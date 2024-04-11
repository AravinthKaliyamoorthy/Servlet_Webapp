package com.servletlearning;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CustomServletFactory extends GenericServlet {

    public static final Logger logger = LoggerFactory.getLogger(CustomServletFactory.class);

    public CustomServletFactory(){
        logger.info("Entered constructor method");
        logger.info("Exited constructor method");
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Entered service method");
        // Create a new instance of your custom servlet here
        TestingServletObjectCreation testingServletObjectCreation = new TestingServletObjectCreation();
        testingServletObjectCreation.service(servletRequest, servletResponse);
        logger.info("Exited service method");
    }

}
