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

public class AddReimburse extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        //request.getRequestDispatcher("empnavbar.html").include(request,response);
        HttpSession ses= request.getSession(false);
        String username= (String)ses.getAttribute("uname");
        Reimburse re = new Reimburse();
        re.setName(username);
        re.setAmount(Integer.valueOf(request.getParameter("amount")));

        re.setStatus("pending");


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
        out.println("<h2> Request of "+ re.getName()+" added</h2>");
        request.getRequestDispatcher("EmpProfile.html").include(request, response);






    }
}


