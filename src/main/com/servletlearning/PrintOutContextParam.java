import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/contextParam")
public class PrintOutContextParam extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(PrintOutContextParam.class);

    ServletConfig servletConfig;
    ServletContext servletContext;

    public void init(ServletConfig servletConfig){
        logger.info("Entered init method");
        this.servletConfig = servletConfig;
        this.servletContext = servletConfig.getServletContext();
        logger.info("Exited init method");
    }

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Entered service method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Init Param Values : "+ servletContext.getInitParameter("AppName") +"</h1>");
        out.println("</body></html>");

        // To read all names of the context parameters from ServletContext object
        Enumeration<String> e = servletContext.getInitParameterNames();
        logger.info("List out all context parameters from ServletContext Object");
        while(e.hasMoreElements()){
            logger.info(e.nextElement());
        }
        logger.info("Exited service method");
    }

}
