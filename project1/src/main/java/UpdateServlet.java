import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import reimburse.Reimburse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateServlet  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();


        HttpSession ses= request.getSession(false);
        String username= (String)ses.getAttribute("uname");


        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        int id= Integer.parseInt(request.getParameter("id"));
        int amount= Integer.parseInt(request.getParameter("amount"));

        Reimburse re= session.find(Reimburse.class,id);
        re.setAmount(amount);
        session.update(re);
        request.getRequestDispatcher("EmpProfile.html").include(request, response);
        out.println("<h1> "+username+" Profile");
       out.println("<br> <h1>Id: " +id+" has been updated.</h1>");
        t.commit();
        session.close();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("Update.html").include(request,response);
    }
}
