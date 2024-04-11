import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Entered doPost method");
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        if(uname.equals("Aravinth") && upwd.equals("password")){
            logger.info("User Credentials passed successfully");
            response.sendRedirect("./index.html");
        }
        else{
            request.setAttribute("errorMessage", "Invalid username or password!");

            // Forward the request to login.html to display the error message
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.html");
            dispatcher.forward(request, response);
        }
        logger.info("Exited doPost method");
    }

}
