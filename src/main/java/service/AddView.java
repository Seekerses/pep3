package service;

import exceptions.InvalidParameterValue;
import model.containers.HitListDAO;
import model.containers.HitListDAOImpl;
import model.entities.Hit;
import utils.fabrics.HitFabric;
import utils.fabrics.HitFabricImpl;

import javax.ejb.EJB;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@SessionScoped
@ManagedBean
public class AddView implements Serializable {

    private Double xCoordinate;
    private Double yCoordinate;
    private Double xHidden;
    private Double yHidden;
    private Double rValue;
    private String yString;

    @EJB
    private HitFabric fabric;

    @EJB
    private ErrorContainer errorContainer;

    @EJB
    private HitListDAO<Hit> hitListDAO;

    public void makeHit(){
        try {
            System.out.println(xCoordinate);
            System.out.println(yCoordinate);
            System.out.println(rValue);
            Hit hit = fabric.createHit(xCoordinate,yCoordinate,rValue);
            if (hit != null){
                hitListDAO.insert(hit);
            }
        } catch (Exception e) {
            e.printStackTrace();
            errorContainer.setMessage(e.getMessage());
        }
    }

    public void makeHiddenHit(){
        try {
            System.out.println(xHidden);
            System.out.println(yHidden);
            System.out.println(rValue);
            Hit hit = fabric.createHit(xHidden,yHidden,rValue);
            if (hit != null){
                hitListDAO.insert(hit);
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().getExternalContext().setResponseStatus(502);
            errorContainer.setMessage(e.getMessage());
        }
    }

    public Double getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public Double getrValue() {
        return rValue;
    }

    public void setrValue(Double rValue) {
        this.rValue = rValue;
    }

    public Double getxHidden() {
        return xHidden;
    }

    public void setxHidden(Double xHidden) {
        this.xHidden = xHidden;
    }

    public Double getyHidden() {
        return yHidden;
    }

    public void setyHidden(Double yHidden) {
        this.yHidden = yHidden;
    }
}
