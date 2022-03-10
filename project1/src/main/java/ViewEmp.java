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
import java.util.Iterator;
import java.util.List;

public class ViewEmp extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        HttpSession ses= request.getSession(false);
        String username= (String)ses.getAttribute("uname");

        out.println("<div>\n " +
                "<table id=\"table\" border=\"1\">\n" +
                "<tr>\n " +
                "<th>Id</th>\n " +
                "<th>Name</th>\n " +
                "<th>Email</th>\n"+
                "</tr>\n ");


       // request.getRequestDispatcher("manNavbar.html").include(request,response);

        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");
        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        String HQL="from Employee where username= '"+username+"' ";
        List<Employee> list=session.createQuery(HQL, Employee.class).list();

        Iterator itr=list.iterator();

        while(itr.hasNext()){
            Employee emp=(Employee)itr.next();
            out.println("<td>"+emp.getId()+"</td>");
            out.println("<td>"+emp.getName()+"</td>");
            out.println("<td>"+emp.getEmail()+"</td></tr>");


        }


        out.println("</table></div>");
        t.commit();
        session.close();
    }
}

