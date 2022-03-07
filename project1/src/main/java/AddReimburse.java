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

public class AddReimburse extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("navbar.html").include(request,response);

        Reimburse re = new Reimburse();
        re.setName(request.getParameter("username"));
        re.setAmount(Integer.valueOf(request.getParameter("amount")));
        re.setStatus(request.getParameter("status"));


        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        // begin transaction
        Transaction t = session.beginTransaction() ;

        session.persist(re);
        t.commit();
        session.close();
        request.getRequestDispatcher(".html").include(request, response);
        out.println("<h2> "+ re.getName()+ " "+ re.getAmount()+" added</h2>");





    }
}


