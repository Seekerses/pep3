package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller","/controller/*"})
public class ControllerServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.print("eee");
        if ("dot".equals(request.getParameter("RequestType"))) {
            System.out.print("afa");
            request.getRequestDispatcher("/check").forward(request, response);
        }

        else if ("clear".equals(request.getParameter("RequestType")))
            request.getRequestDispatcher("/clear").forward(request,response);

        else
            request.getRequestDispatcher("/index.jsp").forward(request,response);
    }
}
