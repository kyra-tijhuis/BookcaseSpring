package database.dao;

import database.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kyra on 19/04/2016.
 */
@Repository
public class BookcaseDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public Bookcase getBookcase(int bookcaseID) {
        return em.find(Bookcase.class, bookcaseID);
    }

    @Transactional
    public Bookcase createBookcase(String name, int width) {
        Bookcase result = new Bookcase();
        result.setBookcaseName(name);
        result.setWidth(width);
        result.setPlanks(new ArrayList<Plank>());

        em.persist(result);
        return result;
    }

    /**
     * Update the Bookcase parameter before calling this method!
     * @param bookcase the updated Bookcase object
     * @return updated bookcase
     */
    @Transactional
    public Bookcase updateBookcase(Bookcase bookcase) {
        Bookcase result = em.merge(bookcase);
        return result;
    }

    @Transactional
    public User getUserFromBookcase(Bookcase bookcase) {
        User user = null;
        Query query = em.createQuery("from User u where :bc member of u.bookcases");
        query.setParameter("bc", bookcase);
        try {
            user = (User) query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No result");
        }
        return user;
    }

    @Transactional
    public List<Bookcase> getAllBookcases() {
        return em.createQuery("from Bookcase").getResultList();
    }

    @Transactional
    public List<Bookcase> searchBookcases(String searchTerm) {
        Query query = em.createQuery("from Bookcase b where b.bookcaseName = :name");
        query.setParameter("name", searchTerm);
        return query.getResultList();
    }
}
