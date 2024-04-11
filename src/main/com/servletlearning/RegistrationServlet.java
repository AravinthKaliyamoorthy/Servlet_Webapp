import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "com.servletlearning.RegistrationServlet", value = "/registrationLearning")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            response.setContentType("text/html");
            PrintWriter printWriter = response.getWriter();
            String ename = request.getParameter("ename");
            String enumber = request.getParameter("enumber");
            float esalary = Float.parseFloat(request.getParameter("esalary"));
            String eaddress = request.getParameter("eaddress");
            
            printWriter.println("<html>");
            printWriter.println("<body>");
            printWriter.println("<h2 style='color: red;' align='center'>User Registration Details</h2>");
            printWriter.println("<table border='1' align='center'>");
            printWriter.println("<tr>");
            printWriter.println("<th>Employee Number</th>");
            printWriter.println("<th>Employee Name</th>");
            printWriter.println("<th>Employee Salary</th>");
            printWriter.println("<th>Employee Address</th>");
            printWriter.println("</tr>");
            printWriter.println("<tr>");
            printWriter.println("<td>" + enumber + "</td>");
            printWriter.println("<td>" + ename + "</td>");
            printWriter.println("<td>" + esalary + "</td>");
            printWriter.println("<td>" + eaddress + "</td>");
            printWriter.println("</tr>");
            printWriter.println("</table>");
            printWriter.println("<a href='./registrationform.html'> |Registration Form|</a>");
            printWriter.println("</body>");
            printWriter.println("</html>");

            printWriter.close();
        }
        catch (Exception e) {

        }
    }
}
