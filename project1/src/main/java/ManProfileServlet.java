import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ManProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session!=null){
            String username = (String) session.getAttribute("username");
            if(!username.equals("") && username!=null){
                request.getRequestDispatcher("manNavbar.html").include(request, response);
                out.println("<h1>Welcome to profile</h1>");
                out.println("<h1> Welcome "+username+"</h1>");
            }
            else{
                out.println("please do the login page and login first");
                request.getRequestDispatcher("/manLogin.html").include(request,response);
            }
            out.close();
        }
        else{
            out.println("<br>please do the login page and login first");
            request.getRequestDispatcher("/manLogin.html").include(request,response);
        }
        out.close();

    }
}


