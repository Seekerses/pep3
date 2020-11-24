package servlet;

import model.containers.HitList;
import model.entites.Hit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/clear","/clear/*"})
public class ClearTableServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HitList<Hit> list = (HitList<Hit>) request.getSession().getAttribute("Results");
        if (list == null){
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }
        else {
            list.clear();
            request.setAttribute("Results", list);
            request.getRequestDispatcher("/index.jsp").forward(request,response);
        }

    }
}
