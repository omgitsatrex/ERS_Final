import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginEmployeeServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();

        String username=request.getParameter("username");
        String password=request.getParameter("password");

//use with  HttpSession

        request.getRequestDispatcher("navbar.html").include(request,response);
        //when user enters correct details
        if(username.equals("wfuentes1") && password.equals("Brotherhood")) {
            out.print("<br>You are successfully logged in");
            out.println("<br>Welcome " + username);


            HttpSession session= request.getSession();
            session.setAttribute("uname",username);
            session.setAttribute("pass",password);



        }else{
            out.println("<br>Sorry! invalid details");
            // out.println("Sorry invalid username and password");
            RequestDispatcher requestDispatcher=request.getRequestDispatcher("/login.html");
            requestDispatcher.include(request,response);
        }
        out.close();

}


}
