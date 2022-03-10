import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();


        HttpSession session=request.getSession(false);
        session.invalidate();
        out.println("<br>you are logged out successfully \n"+
                "<br>");

        RequestDispatcher requestDispatcher=request.getRequestDispatcher("/index.html");
        requestDispatcher.include(request,response);
    }
}
