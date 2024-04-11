import com.webapp.service.UserService;
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

        String umail = request.getParameter("umail");
        String upwd = request.getParameter("upwd");

        UserService userService = new UserService();
        String status = userService.checkLogin(umail, upwd);

        if(status.equals("success")) {
            response.sendRedirect("./index.html");
        }
        else{
            PrintWriter out = response.getWriter();
            out.println("<h1>Invalid Credentials</h1>");
            RequestDispatcher rd = request.getRequestDispatcher("./loginform.html");
            rd.include(request, response);
            out.close();
        }
        logger.info("Exited doPost method");
    }

}
