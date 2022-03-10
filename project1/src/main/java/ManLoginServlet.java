import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import manager.Manager;
import manager.ManagerDao;
import manager.ManagerDaoFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class ManLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ManagerDao managerDao = ManagerDaoFactory.getManagerDao();
        Manager manager = managerDao.getManagerByUsername(username);

        if(manager!=null && manager.getPassword().equals(password)){
            request.getRequestDispatcher("manNavbar.html").include(request, response);
//            out.println("<a href='Servlet6'>Click Here</a>");

            out.println("You are successfully logged in");
            out.println("<br>Welcome, " + manager.getPassword());

            //Http Session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
        }
        else{
            out.println("<br>Sorry! invalid details");
            request.getRequestDispatcher("/manLogin.html").include(request, response);
        }
        out.close();
    }
}