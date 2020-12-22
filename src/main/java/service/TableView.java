package service;

import model.containers.HitListDAO;
import model.entities.Hit;
import org.hibernate.TransactionException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
@ManagedBean
public class TableView implements Serializable {

    @EJB
    private HitListDAO<Hit> hitHitListDAO;

    @EJB
    private ErrorContainer errorContainer;


    public List<Hit> getHitList() {
        List<Hit> hitResultsFromDB = new ArrayList<>();

        try {
            hitResultsFromDB = hitHitListDAO.getAll();
        } catch (TransactionException e) {
            errorContainer.setMessage(e.getMessage());
            FacesContext.getCurrentInstance()
                    .getApplication()
                    .getNavigationHandler()
                    .handleNavigation(FacesContext.getCurrentInstance(), null, "/error.xhtml");
        }
        return hitResultsFromDB;
    }

    public void clearTable() {
        try {
            hitHitListDAO.clear();
        } catch (TransactionException e) {
            errorContainer.setMessage(e.getMessage());
        }
    }
}
