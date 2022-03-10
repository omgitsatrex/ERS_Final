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
import java.util.Iterator;
import java.util.List;

public class ViewResolvedEmp  extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();


        out.println("<div>\n " +
                "<table id=\"table\" border=\"1\">\n" +
                "<tr>\n " +
                "<th>Id</th>\n " +
                "<th>Amount</th>\n " +
                "<th>Status</th>\n " +
                "</tr>\n ");

        HttpSession ses= request.getSession(false);
        String username= (String)ses.getAttribute("uname");
        request.getRequestDispatcher("empnavbar.html").include(request,response);

        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        String HQL="from Reimburse where status='approved' and username= '"+username+"'";
        List<Reimburse> list=session.createQuery(HQL, Reimburse.class).list();

        Iterator itr=list.iterator();

        while(itr.hasNext()){
            Reimburse r=(Reimburse)itr.next();
            out.println("<td>"+r.getId()+"</td>");
            out.println("<td>"+r.getAmount()+"</td>");
            out.println("<td>"+r.getStatus()+"</td></tr>");

        }


        out.println("</table></div>");
        t.commit();
        session.close();
    }
}
