import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reimburse.Reimburse;
import reimburse.ReimburseDao;
import reimburse.ReimburseDaoFactory;

import java.io.IOException;
import java.io.PrintWriter;

public class ManApproveServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        request.getRequestDispatcher("manNavbar.html").include(request, response);

        out.println("this is the reimburse approve servlet");

        int id = Integer.parseInt(request.getParameter("id"));

        ReimburseDao reimburseDao = ReimburseDaoFactory.getReimburseDao();
        reimburseDao.updateReimburseStatus(id);


    }
}