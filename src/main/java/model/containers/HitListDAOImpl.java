package model.containers;

import model.entities.Hit;
import org.hibernate.HibernateException;
import org.hibernate.TransactionException;

import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.NoSuchElementException;

@Singleton
public class HitListDAOImpl implements HitListDAO<Hit> {

    private final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("persist");

    @Override
    public Hit insert(@NotNull Hit obj) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();
            entityManager.persist(obj);
            entityTransaction.commit();
            return obj;

        } catch (HibernateException exception) {

            entityTransaction.rollback();
            throw new TransactionException("Transaction failed...");
        }
    }

    @Override
    public Hit getById(long id) {

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        try {
            Hit result = entityManager.find(Hit.class, id);

            if (result == null)
                throw new NoSuchElementException("No such element in database, sorry((");

            return result;
        }
        catch (HibernateException e){
            throw new TransactionException("Failing while searching for element");
        }
    }

    @Override
    public List<Hit> getAll() {

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        try {
            List<Hit> result = entityManager.createNativeQuery("SELECT * FROM Hit",Hit.class).getResultList();

            if (result == null)
                throw new NoSuchElementException("Database is clear...");

            return result;
        }
        catch (HibernateException e){
            throw new TransactionException("Failing while searching for element");
        }
    }

    @Override
    public boolean clear() {
        try {
            List<Hit> elements = getAll();
            for (Hit element : elements) {
                delete(element);
            }
            return true;
        }
        catch (HibernateException exception){
            throw new TransactionException("Failed while clearing database");
        }
    }

    @Override
    public boolean delete(@NotNull Hit obj) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();
            Hit thisElementInDB = entityManager.find(Hit.class, obj.getId());
            if (thisElementInDB == null) return false;

            entityManager.remove(thisElementInDB);
            entityTransaction.commit();
            return true;

        } catch (HibernateException exception) {

            entityTransaction.rollback();
            throw new TransactionException("Transaction failed...");
        }
    }
}
