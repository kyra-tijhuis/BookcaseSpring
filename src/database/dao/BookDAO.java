package database.dao;

import database.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.File;

/**
 * Created by Kyra on 15/04/2016.
 */
public class BookDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public Book getBook(String isbn){
        return emf.createEntityManager().find(Book.class, isbn);
    }

    /**
     * Creates a new book in the database with the supplied data and returns that book.
     * If the isbn is already present in the database that book is returned.
     * @param isbn
     * @param bookTitle
     * @param author
     * @param height in mm
     * @param width in mm
     * @param thickness in mm
     * @return a Book, newly created or retrieved from the database.
     */
    public Book createBook(String isbn, String bookTitle, String author, int height, int width, int thickness) {
        Book result = getBook(isbn);
        if (result==null) {
            // new book
            result.setIsbn(isbn);
            result.setBookTitle(bookTitle);
            result.setAuthor(author);
            result.setHeight(height);
            result.setWidth(width);
            result.setThickness(thickness);

            EntityManager em = emf.createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(result);
            t.commit();
            em.close();
        }
        return result;
    }

    /**
     * Update the Book parameter before calling this method!
     * @param book the updated Book object
     * @return updated book
     */
    public Book updateBook(Book book) {
        Book result = null;
        EntityManager em = emf.createEntityManager();
        EntityTransaction t = em.getTransaction();
        t.begin();

        result = em.merge(book);

        t.commit();
        em.close();
        return result;
    }
}
