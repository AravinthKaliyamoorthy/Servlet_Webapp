import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class WelcomeHttpServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(WelcomeHttpServlet.class);

    static{
        logger.info("Entered Static Block");
    }

    public WelcomeHttpServlet(){
        logger.info("Entered Constructor method");
    }

    @Override
    public void init(){
        logger.info("Entered init method");
        destroy();
    }

    @Override
    public void destroy(){
        logger.info("Entered destroy method");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("Entered doGet method");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Welcome to Http Servlet</h1>");
        out.println("</body></html>");
        logger.info("Exited doGet method");
    }

}
