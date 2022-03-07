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
import java.util.List;

public class ManagerLogin extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("navbar.html").include(request, response);
        out.println("<br>test1");

//        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        Manager manager = new Manager();//(id, username, password);
//        manager.setUsername(username);
//        manager.setPassword(password);

        Configuration config = new Configuration();

        config.configure("hibernate.cfg.xml");

        SessionFactory factory = config.buildSessionFactory();

        Session session = factory.openSession();
        Transaction t= session.beginTransaction();
        List <Manager> list = session.createQuery("from Manager", Manager.class).list(); //where username='"+username+"' and password='"+password+"';

//        out.println(list.toString()); // getting [Employee@20243220]

        for(Manager m:list){
            out.println("testInsideLoop");
            out.println( "<br>" + m.toString());
        }
        out.println("<br>test2");
        t.commit();
        session.close();
    }
}
