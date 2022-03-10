import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class EmpProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("empnavbar.html").include(request,response);

        //Cookie[] cookies=request.getCookies();
        HttpSession session= request.getSession(false);
        if(session!=null){
            String username= (String)session.getAttribute("uname");
            if(!username.equals("")){
                out.println("<h1>Welcome "+ username+" </h1>");


            }else{
                out.println("please do the login page and login first");
                request.getRequestDispatcher("/Login").include(request,response);
            }
            out.close();
        }

    }
}


