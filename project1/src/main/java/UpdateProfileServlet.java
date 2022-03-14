import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

public class UpdateProfileServlet  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        HttpSession ses= request.getSession(false);
        String username= (String)ses.getAttribute("uname");
       // String id= (String) ses.getAttribute("id");



        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        int id=Integer.parseInt(request.getParameter("id"));
        String email=request.getParameter("email");

        Employee emp= session.find(Employee.class,id);
        emp.setEmail(email);
        session.update(emp);
        out.println("<h1>User: "+username+" profile has been updated.</h1>");
        request.getRequestDispatcher("EmpProfile.html").include(request, response);

        t.commit();
        session.close();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("UpdateProfile.html").include(request,response);
    }
}
