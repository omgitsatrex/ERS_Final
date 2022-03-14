import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import reimburse.Reimburse;
import reimburse.ReimburseDao;
import reimburse.ReimburseDaoFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManViewEmpServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<a href=\"ManProfileServlet\">Go back</a> |");

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();

        List <Employee> list = session.createQuery("from Reimburse where status='pending'", Employee.class).list();

        out.println("<a href=\"ManProfileServlet\">Go back</a> |");

        out.println("<table border=1>" +
                "<tr>" +
                "<th>Id</th>" +
                "<th>Amount</th>" +
                "<th>Status</th>" +
                "<th>username</th>" +
                "</tr>"
        );

        for(Employee e:list){
            out.println(
                    "<tr>" +
                            "<td>"+e.getId()+"</td>" +
                            "<td>"+e.getName()+"</td>" +
                            "<td>"+e.getEmail()+"</td>" +
                            "</tr>"
            );
        }


    }
}
