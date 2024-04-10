import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter printWriter = response.getWriter();

        String uname = request.getParameter("uname");
        String upwd = request.getParameter("upwd");

        printWriter.println("<html>");
        printWriter.println("<body>");
        printWriter.println("<br><br>");
        printWriter.println("<h2 style='color: red;' align='center'>");
        if(uname.equals("Aravinth") && upwd.equals("password")){
            printWriter.println("User Login Success");
        }
        else{
            printWriter.println("User Login Failure");
        }
        printWriter.println("</h2>");
        printWriter.println("<br><br>");
        printWriter.println("</body>");
        printWriter.println("<h3 align='center'/>");
        printWriter.println("<a href='./loginform.html'>Logout</a>");
    }

}
