import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import manager.Manager;
import manager.ManagerDao;
import manager.ManagerDaoFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class ManLoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        String username = request.getParameter("username");
        String password = request.getParameter("password");

        ManagerDao managerDao = ManagerDaoFactory.getManagerDao();
        Manager manager = managerDao.getManagerByUsername(username);

        if(manager!=null && manager.getPassword().equals(password)){
            //request.getRequestDispatcher("manNavbar.html").include(request, response);
//            out.println("<a href='Servlet6'>Click Here</a>");
            out.println("    <style>\n" +
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
                    "}"+
                    "    </style>\n");
            out.println(" <ul>\n" +
                    "  <li> <a href=\"LogoutServlet\">Logout</a></li>\n" +
                    "<li><a href=\"ManProfileServlet\">Profile</a></li>\n" +
                    "<li><a href=\"manApprove.html\">Approve a reimbursement</a></li>\n"+
                    "<li><a href=\"manReject.html\">Reject a reimbursement</a></li>\n"+
                    "<li><a href=\"ManViewPendingServlet\">View Pending reimbursements</a></li>\n" +
                    "<li><a href=\"ManViewApprovedServlet\">View Approved reimbursements</a></li>\n" +
                    "<li><a href=\"ViewAllServlet\">View All Employees</a></li>\n"+
                    " </ul>\n");
            out.println("You are successfully logged in");
            out.println("<br>Welcome, " + manager.getUsername());

            //Http Session
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
        }
        else{
            out.println("<br>Sorry! invalid details");
            request.getRequestDispatcher("/manLogin.html").include(request, response);
        }
        out.close();
    }
}