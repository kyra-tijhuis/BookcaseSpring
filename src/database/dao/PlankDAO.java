package database.dao;

import database.model.Plank;

import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by Kyra on 15/04/2016.
 */
public class PlankDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public Plank getPlank(int plankID) {
        Plank plank = null;
        Query query = emf.createEntityManager().createQuery("from Plank p where plankID=:plankid");
        query.setParameter("plankid", plankID);
        try {
            plank = (Plank) query.getSingleResult();
        } catch (NoResultException e) {
            // plank is not present in database and stays null
        }
        return plank;
    }

    public Plank createPlank() {
        // TODO
        return null;
    }

}
