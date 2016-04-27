package database.dao;

import database.model.Book;
import database.model.BookDetails;
import database.model.Orientation;
import database.model.Plank;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

/**
 * Created by Kyra on 19/04/2016.
 */
@Repository
public class BookDetailsDAO {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public BookDetails getBookDetails(int bookDetailsID) {
        return em.find(BookDetails.class, bookDetailsID);
    }

    @Transactional
    public BookDetails createBookDetails(Book book, Orientation orientation, Plank plank, int bookIndex) {
        BookDetails details = new BookDetails();
        details.setBook(book);
        details.setOrientation(orientation);
        details.setBookIndex(bookIndex);

        em.persist(details);

        return details;
    }

    /**
     * Update the BookDetails parameter before calling this method!
     * @param bookDetails the updated BookDetails object
     * @return updated bookDetails
     */
    @Transactional
    public BookDetails updateBookDetails(BookDetails bookDetails) {
        BookDetails result = em.merge(bookDetails);
        return result;
    }

    @Transactional void deleteBookDetails(BookDetails bookDetails) {
        em.remove(bookDetails);
        em.close();
    }
}
