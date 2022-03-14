import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ManProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if(session!=null){
            String username = (String) session.getAttribute("username");
            if(!username.equals("") && username!=null){
                //request.getRequestDispatcher("manNavbar.html").include(request, response);
                out.println(" <ul>\n" +
                        "  <li> <a href=\"LogoutServlet\">Logout</a></li>\n" +
                        "<li><a href=\"ManProfileServlet\">Profile</a></li>\n" +
                        "<li><a href=\"manApprove.html\">Approve a reimbursement</a></li>\n"+
                        "<li><a href=\"manReject.html\">Reject a reimbursement</a></li>\n"+
                        "<li><a href=\"ManViewPendingServlet\">View Pending reimbursements</a></li>\n" +
                        "<li><a href=\"ManViewApprovedServlet\">View Approved reimbursements</a></li>\n" +
                        "<li><a href=\"ViewAllServlet\">View All Employees</a></li>\n"+
                        " </ul>\n");
                out.println("<h1>Welcome to profile</h1>");
                out.println("<h1> Welcome "+username+"</h1>");
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
                        "</div>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>");
            }
            else{
                out.println("please do the login page and login first");
                request.getRequestDispatcher("/manLogin.html").include(request,response);
            }
            out.close();
        }
        else{
            out.println("<br>please do the login page and login first");
            request.getRequestDispatcher("/manLogin.html").include(request,response);
        }
        out.close();

    }
}


