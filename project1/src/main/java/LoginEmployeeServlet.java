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

        //request.getRequestDispatcher("empnavbar.html").include(request,response);
        //when user enters correct details
       // if(username.equals(list.) && password.equals("")) {


        if(user.equals(request.getParameter("username")) && pass.equals(request.getParameter("password")) ){
            out.println(" <ul>\n" +
                    "  <li> <a href=\"LogoutServlet\">Logout</a></li>\n" +
                    "<li><a href=\"EmpProfile.html\">Profile</a></li>\n" +
                    "<li><a href=\"AddReimbursement.html\">Add Reimbursement</a></li>\n" +
                    "<li><a href=\"ViewPendingEmp\">ViewPending</a></li>\n"+
                    "<li><a href=\"ViewResolvedEmp\">ViewResolved</a></li>\n"+
                    "<li><a href=\"ViewAllEmp\">View All Request</a></li>\n" +
                    "<li><a href=\"Update.html\">Update</a></li>\n" +
            " </ul>\n");
            out.print("<br>You are successfully logged in");
            out.println("<br><h1>Welcome " + username + "</h1>");
            out.println("<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Title</title>\n" +
                    "    <style>\n" +
                    "    body{\n" +
                    "    background-color:grey;\n" +
                    "    }\n" +
                    "    div{\n" +
                    "             width: 200px;\n" +
                    "            height: 200px;\n" +
                    "\n" +
                    "            display:inline-block;\n" +
                    "            }\n" +
                    "   ul {\n" +
                            "  list-style-type: none;\n" +
                            "  margin: 0;\n" +
                            "  padding: 0;\n" +
                            "  overflow: hidden;\n" +
                            "  background-color: #333;\n" +
                            "}\n" +
                            "li a{\n" +
                            "display: block;\n" +
                            "  color: white;\n" +
                            "  text-align: left;\n" +
                            "  padding: 14px 16px;\n" +
                            "  text-decoration: none;\n" +
                            " float:left;\n"+
                            "}\n" +
                            "li a:hover {\n" +
                            "  background-color: #111;\n" +
                            "}\n" +
                    ".btn{\n" +
                    " background-color: navy;\n" +
                    "  border: none;\n" +
                    "  color: white;\n" +
                    "  padding: 15px 32px;\n" +
                    "  text-align: center;\n" +
                    "  text-decoration: none;\n" +
                    "  display: inline-block;\n" +
                    "  font-size: 16px;\n" +
                    "  margin: 4px 2px;\n" +
                    "  cursor: pointer;\n" +
                    "}\n"+
                    "    </style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>");

            HttpSession ses= request.getSession(true);
            ses.setAttribute("uname",username);






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
