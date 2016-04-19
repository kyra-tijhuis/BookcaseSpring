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
        return emf.createEntityManager().find(Plank.class, plankID);
    }

    public Plank createPlank() {
        // TODO
        return null;
    }

}
