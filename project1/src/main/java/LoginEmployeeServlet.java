import jakarta.servlet.RequestDispatcher;
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
import java.util.List;

public class LoginEmployeeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        Configuration config = new Configuration();

        // read the Configuration and load in the object
        config.configure("hibernate.cfg.xml");

        // create factory
        SessionFactory factory = config.buildSessionFactory();
        // ope the session
        Session session = factory.openSession();
        Transaction t= session.beginTransaction();

        String username=request.getParameter("username");
        String password=request.getParameter("password");
    String query="FROM Employee where username='"+username+"' and password='"+password+"'";
        List<Employee> list=session.createQuery(query, Employee.class).list();
        String user="";
        String pass="";
        try {
            user = list.get(0).getName();
             pass= list.get(0).getPassword();
        }catch(Exception e){

        }
//use with  HttpSession

        request.getRequestDispatcher("empnavbar.html").include(request,response);
        //when user enters correct details
       // if(username.equals(list.) && password.equals("")) {


        if(user.equals(request.getParameter("username")) && pass.equals(request.getParameter("password")) ){
            out.print("<br>You are successfully logged in");
            out.println("<br>Welcome " + username);




            HttpSession ses= request.getSession(true);


            ses.setAttribute("uname",username);
            ses.setAttribute("pass",password);



        }else{
            out.println("<br>Sorry! invalid details");
            // out.println("Sorry invalid username and password");
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/Login.html");
            requestDispatcher.include(request,response);
        }
        t.commit();
        session.close();


}


}
