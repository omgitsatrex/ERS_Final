import jakarta.persistence.Query;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

public class UpdateServlet  extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();



        request.getRequestDispatcher("empnavbar.html").include(request,response);

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

       out.println("Id: " +id+" has been updated.");
        t.commit();
        session.close();
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        request.getRequestDispatcher("Update.html").include(request,response);
    }
}
