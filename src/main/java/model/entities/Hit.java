package model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Hit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double xCoordinate;
    private double yCoordinate;
    private double rValue;
    private boolean isInArea;

    public Hit(){}

    public Hit(double xCoordinate, double yCoordinate, double rValue, boolean isInArea) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.rValue = rValue;
        this.isInArea = isInArea;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public double getRValue() {
        return rValue;
    }

    public void setRValue(double rValue) {
        this.rValue = rValue;
    }

    public boolean isInArea() {
        return isInArea;
    }

    public void setInArea(boolean inArea) {
        isInArea = inArea;
    }

    @Override
    public String toString() {
        return  xCoordinate +
                "," + yCoordinate +
                "," + rValue +
                "," + isInArea;
    }

    public Long getId() {
        return id;
    }
}
