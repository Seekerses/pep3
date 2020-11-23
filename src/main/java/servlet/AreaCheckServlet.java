package servlet;


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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        double xCoordinate;

        double yCoordinate = Double.parseDouble(request.getParameter("Y"));
        int rValue = Integer.parseInt(request.getParameter("R"));
        boolean isHit = false;
        try {
            xCoordinate = Double.parseDouble(request.getParameter("X"));
            if (xCoordinate < -5 || xCoordinate > 3) {
                throw new Exception();
            }
        }
        catch (Exception e){
            writeSessionResults(request.getSession(), response);
            response.getWriter().print("Invalid Number;" + yCoordinate + ";" + rValue + ";Invalid");
            response.getWriter().close();
            return;
        }
        response.setContentType("text/plain");
        if ((xCoordinate >=0 && yCoordinate >= 0) && (yCoordinate <=  -xCoordinate/2 + (float)rValue /2)){
            isHit = true;
        }
        if ((xCoordinate <= 0 && yCoordinate <= 0) && (Math.pow(xCoordinate,2) + Math.pow(yCoordinate,2) <= Math.pow(rValue,2))) {
            isHit = true;
        }
        if ((xCoordinate >= 0 && yCoordinate <=0) && (xCoordinate <= rValue && yCoordinate >= -rValue)) {
            isHit = true;
        }
        writeSessionResults(request.getSession(), response);
        response.getWriter().print(xCoordinate + ";" + yCoordinate + ";" + rValue + ";" + isHit);
        response.getWriter().close();
        saveResult(new Result(xCoordinate, yCoordinate, rValue, isHit), request.getSession());
    }

    static class Result{
        private double x;
        private double y;
        private int r;
        private boolean hit;

        Result(double x, double y, int r, boolean hit) {
            this.x = x;
            this.y = y;
            this.r = r;
            this.hit = hit;
        }

        public Result(){}

        public double getX() {
            return x;
        }

        public void setX(double x) {
            this.x = x;
        }

        public double getY() {
            return y;
        }

        public void setY(double y) {
            this.y = y;
        }

        public int getR() {
            return r;
        }

        public void setR(int r) {
            this.r = r;
        }

        public boolean isHit() {
            return hit;
        }

        public void setHit(boolean hit) {
            this.hit = hit;
        }

        @Override
        public String toString() {
            return x +
                    ";" + y +
                    ";" + r +
                    ";" + hit;
        }
    }

    private void writeSessionResults (HttpSession session, HttpServletResponse response){
        ArrayList<Result> results = (ArrayList<Result>) session.getAttribute("Results");
        if (results != null) results.forEach((k) -> {
            try {
                response.getWriter().print(k.toString() + "/");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void saveResult(Result result, HttpSession session){
        if (session.getAttribute("Results" ) == null) session.setAttribute("Results",new ArrayList<Result>());
        ArrayList<Result> results = (ArrayList<Result>) session.getAttribute("Results");
        if (results != null) results.add(result);
        session.setAttribute("Results", results);
    }
}
