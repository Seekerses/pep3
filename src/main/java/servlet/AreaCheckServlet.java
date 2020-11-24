package servlet;


import exceptions.DataException;
import exceptions.InvalidParameterValue;
import model.containers.HitList;
import model.containers.HitListImpl;
import model.entites.Hit;
import utils.fabrics.HitFabric;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

@WebServlet(urlPatterns = {"/check","/check/*"})
public class AreaCheckServlet extends HttpServlet {

    @EJB
    private HitFabric hitFabric;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");

        double xCoordinate;
        double yCoordinate;
        double rValue;

        try{
            xCoordinate = Double.parseDouble(request.getParameter("X"));
            yCoordinate = Double.parseDouble(request.getParameter("Y"));
            rValue = Double.parseDouble(request.getParameter("R"));

        }catch (NumberFormatException | NullPointerException exception){
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return;
        }
        try {
            Hit hit = hitFabric.createHit(xCoordinate,yCoordinate,rValue);
            saveHit(hit, request.getSession());
            request.setAttribute("hit", hit);
        } catch (InvalidParameterValue exception) {
            request.setAttribute("error", exception.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request,response);
            return;
        }
        System.out.print("Hit done");
        request.getRequestDispatcher("/result.jsp").forward(request,response);
    }

    private void saveHit(Hit hit, HttpSession session){
        if (session.getAttribute("Results" ) == null) session.setAttribute("Results",new HitListImpl());
        HitList<Hit> results = (HitList<Hit>) session.getAttribute("Results");
        if (results != null) results.add(hit);
        session.setAttribute("Results", results);
    }
}
