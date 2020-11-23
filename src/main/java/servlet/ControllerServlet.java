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
        if ("dot".equals(request.getParameter("RequestType"))){
            servlet.AreaCheckServlet areaCheckServlet = new servlet.AreaCheckServlet();
            areaCheckServlet.doGet(request,response);
        }
        else {
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
}
