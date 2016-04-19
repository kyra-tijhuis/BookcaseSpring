package database.dao;

import database.model.Bookcase;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Kyra on 19/04/2016.
 */
public class BookcaseDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public Bookcase getBookcase(int bookcaseID) {
        return emf.createEntityManager().find(Bookcase.class, bookcaseID);
    }
}
