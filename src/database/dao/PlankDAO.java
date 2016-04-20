package database.dao;

import database.model.Plank;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Kyra on 15/04/2016.
 */
public class PlankDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public Plank getPlank(int plankID) {
        return emf.createEntityManager().find(Plank.class, plankID);
    }

    /**
     *
     * @param plankID
     * @return -1 if plank does not exist, otherwise the first empty index of the plank
     */
    public int firstEmptyOnPlank(int plankID) {
        Plank plank = getPlank(plankID);
        int result = -1;

        if (plank!=null) {
            result = plank.getBooks().size();
        }
        return result;
    }

    public Plank createPlank(int height) {
        Plank result = new Plank();
        result.setHeight(height);
        result.setBooks(new ArrayList<>());

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(result);
        t.commit();
        em.close();

        return result;
    }

    /**
     * Update the Plank parameter before calling this method!
     * @param plank the updated Plank object
     * @return updated bookcase
     */
    public Plank updatePlank(Plank plank) {
        Plank result = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        result = em.merge(plank);

        t.commit();
        em.close();
        return result;
    }
}
