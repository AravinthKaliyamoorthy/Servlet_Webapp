import jakarta.servlet.*;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeGenericServlet extends GenericServlet {

    public static final Logger logger = LoggerFactory.getLogger(WelcomeGenericServlet.class);

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        logger.info("Entered service method");
        servletResponse.setContentType("text/html");
        PrintWriter out = servletResponse.getWriter();
        out.println("<html><body>");
        out.println("<h1>Welcome to Generic Servlet</h1>");
        out.println("</body></html>");
        logger.info("Exited service method");
    }

}