import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reimburse.Reimburse;
import reimburse.ReimburseDao;
import reimburse.ReimburseDaoFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ManViewApprovedServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ReimburseDao reimburseDao = ReimburseDaoFactory.getReimburseDao();
        List<Reimburse> list = reimburseDao.getReimbursesByApproved();

        out.println("<a href=\"/RevatureProject_1\">Go back</a> |");

        out.println("<table border=1>" +
                "<tr>" +
                "<th>Id</th>" +
                "<th>Amount</th>" +
                "<th>Status</th>" +
                "<th>Username</th>" +
                "</tr>"
        );

        for(Reimburse e:list){
            out.println(
                    "<tr>" +
                            "<td>"+e.getId()+"</td>" +
                            "<td>"+e.getAmount()+"</td>" +
                            "<td>"+e.getStatus()+"</td>" +
                            "<td>"+e.getName()+"</td>" +
                            "</tr"
            );
        }


    }
}
