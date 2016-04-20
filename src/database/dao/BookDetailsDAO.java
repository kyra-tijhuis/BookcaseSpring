package database.dao;

import database.model.Book;
import database.model.BookDetails;
import database.model.Orientation;
import database.model.Plank;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Kyra on 19/04/2016.
 */
public class BookDetailsDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public BookDetails getBookDetails(int bookDetailsID) {
        return emf.createEntityManager().find(BookDetails.class, bookDetailsID);
    }

    public BookDetails createBookDetails(Book book, Orientation orientation, Plank plank, int bookIndex) {
        BookDetails details = new BookDetails();
        details.setBook(book);
        details.setOrientation(orientation);
        details.setBookIndex(bookIndex);
        plank.getBooks().add(details);

        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();
        em.persist(details);
        t.commit();
        details.setPlank(plank);
        t.begin();
        em.merge(plank);
        t.commit();
        em.close();

        return details;
    }

    /**
     * Update the BookDetails parameter before calling this method!
     * @param bookDetails the updated BookDetails object
     * @return updated bookDetails
     */
    public BookDetails updateBookDetails(BookDetails bookDetails) {
        BookDetails result = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        result = em.merge(bookDetails);

        t.commit();
        em.close();
        return result;
    }
}
