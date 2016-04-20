package database.dao;

import database.model.BookDetails;
import database.model.Bookcase;
import database.model.Plank;
import database.model.User;

import javax.persistence.*;
import java.util.ArrayList;

/**
 * Created by Kyra on 19/04/2016.
 */
public class BookcaseDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public Bookcase getBookcase(int bookcaseID) {
        return emf.createEntityManager().find(Bookcase.class, bookcaseID);
    }

    public Bookcase createBookcase(String name, int width) {
        Bookcase result = new Bookcase();
        result.setBookcaseName(name);
        result.setWidth(width);
        result.setPlanks(new ArrayList<Plank>());

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(result);
        t.commit();
        em.close();

        return result;
    }

    /**
     * Update the Bookcase parameter before calling this method!
     * @param bookcase the updated Bookcase object
     * @return updated bookcase
     */
    public Bookcase updateBookcase(Bookcase bookcase) {
        Bookcase result = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        result = em.merge(bookcase);

        t.commit();
        em.close();
        return result;
    }
}
