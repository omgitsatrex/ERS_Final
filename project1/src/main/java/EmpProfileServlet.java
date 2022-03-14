import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class EmpProfileServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        //request.getRequestDispatcher("empnavbar.html").include(request,response);

        //Cookie[] cookies=request.getCookies();
        HttpSession session= request.getSession(false);
        if(session!=null){
            String username= (String)session.getAttribute("uname");
            if(!username.equals("")){
                out.println(" <ul>\n" +
                        "  <li> <a href=\"LogoutServlet\">Logout</a></li>\n" +
                        "<li><a href=\"EmpProfile.html\">Profile</a></li>\n" +
                        "<li><a href=\"AddReimbursement.html\">Add Reimbursement</a></li>\n" +
                        "<li><a href=\"ViewPendingEmp\">ViewPending</a></li>\n"+
                        "<li><a href=\"ViewResolvedEmp\">ViewResolved</a></li>\n"+
                        "<li><a href=\"ViewAllEmp\">View All Request</a></li>\n" +
                        "<li><a href=\"Update.html\">Update</a></li>\n" +
                        " </ul>\n");
                out.println("<br><h1>Welcome to " + username + " Profile</h1>");
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
                        " <a href=\"ViewEmp\"/>\n " +
                        "<button class=\"btn\">View Profile Info</button>" +
                        "    <a href=\"UpdateProfile.html\"/>\n " +
                        "<button class=\"btn\">Update Profile Info</button>" +
                        "</div>\n" +

                        "</body>\n" +
                        "</html>");


            }else{
                out.println("please do the login page and login first");
                request.getRequestDispatcher("/Login").include(request,response);
            }
            out.close();
        }

    }
}


