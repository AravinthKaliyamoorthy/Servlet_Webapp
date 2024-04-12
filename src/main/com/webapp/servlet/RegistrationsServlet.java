import com.webapp.service.UserService;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registration")
public class RegistrationsServlet extends HttpServlet {

    Logger logger = LoggerFactory.getLogger(RegistrationsServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        logger.info("Entered doPost method");
        resp.setContentType("text/html");

        ServletContext servletContext = getServletContext();

        String uname = req.getParameter("uname");
        String umail = req.getParameter("umail");
        String upwd = req.getParameter("upwd");
        String ucpwd = req.getParameter("ucpwd");

        PrintWriter out = resp.getWriter();
        UserService  userService = new UserService();
        String status = userService.registerUser(uname, umail, upwd, ucpwd, servletContext);
        if(status.equals("success")){
            resp.sendRedirect("./loginform.html");
        }
        else if(status.equals("existed")){
            out.println("User already existed");
        }
        else{
            out.println("User Registration Failed");
        }
        logger.info("Exited doPost method");
    }
}
