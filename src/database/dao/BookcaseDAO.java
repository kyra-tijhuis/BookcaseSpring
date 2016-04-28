package database.dao;

import database.model.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
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
//        return em.find(Bookcase.class, bookcaseID);

        Bookcase bookcase = null;
        Query query = em.createQuery("from Bookcase b where bookcaseID= :bID");
        query.setParameter("bID", bookcaseID);
        try {
            bookcase = (Bookcase) query.getSingleResult();
        } catch (NoResultException e) {
            // bookcase stays null
        }
        return bookcase;
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

//    @Transactional
//    public HashSet<Book> getAllBooks(Bookcase bookcase) {
//        HashSet<Book> result = new HashSet<>();
//        Query query = em.createQuery("select b.planks from Bookcase b where b.bookcaseID = :bID");
//        query.setParameter("bID", bookcase.getBookcaseID());
//        for (Plank p : (List<Plank>)query.getResultList()) {
//            Query bookQuery = em.createQuery("select p.books from Plank  as p fetch all properties where p.plankID = :pID");
//            bookQuery.setParameter("pID", p.getPlankID());
//            for (BookDetails bd : (List<BookDetails>)bookQuery.getResultList()) {
//                result.add(bd.getBook());
//                Book book = bd.getBook();
//            }
//        }
//        return result;
//    }

    @Transactional
    public List<Bookcase> searchBooks(String searchTerm) {
        HashSet<Bookcase> resultSet = new HashSet<>();
        for (Bookcase bookcase : getAllBookcases()) {
            Query query = em.createQuery("select b.planks from Bookcase b where b.bookcaseID = :bID");
            query.setParameter("bID", bookcase.getBookcaseID());
            for (Plank p : (List<Plank>)query.getResultList()) {
                Query bookQuery = em.createQuery("select p.books from Plank  as p fetch all properties where p.plankID = :pID");
                bookQuery.setParameter("pID", p.getPlankID());
                for (BookDetails bd : (List<BookDetails>)bookQuery.getResultList()) {
                    Book book = bd.getBook();
                    if (book.getAuthor().contains(searchTerm) || book.getBookTitle().contains(searchTerm) || book.getIsbn().contains(searchTerm)) {
                        resultSet.add(bookcase);
                    }
                }
            }
        }
        return new ArrayList<>(resultSet);
    }

    @Transactional
    public boolean bookInBookcase(Bookcase bookcase, String searchTerm) {
        Query query = em.createQuery("select b.planks from Bookcase b where b.bookcaseID = :bID");
        query.setParameter("bID", bookcase.getBookcaseID());
        for (Plank p : (List<Plank>)query.getResultList()) {
            Query bookQuery = em.createQuery("select p.books from Plank  as p fetch all properties where p.plankID = :pID");
            bookQuery.setParameter("pID", p.getPlankID());
            for (BookDetails bd : (List<BookDetails>)bookQuery.getResultList()) {
                Book book = bd.getBook();
                if (book.getAuthor().contains(searchTerm) || book.getBookTitle().contains(searchTerm) || book.getIsbn().contains(searchTerm)) {
                    return true;
                }
            }
        }
        return false;
    }
}
