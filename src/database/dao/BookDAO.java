package database.dao;

import database.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Kyra on 15/04/2016.
 */
@Repository
public class BookDAO {
    @PersistenceContext
    private EntityManager em;
//    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    @Transactional
    public Book getBook(String isbn){
        return em.find(Book.class, isbn);
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
    @Transactional
    public Book createBook(String isbn, String bookTitle, String author, int height, int width, int thickness) {
        Book result = new Book();
        result.setIsbn(isbn);
        result.setBookTitle(bookTitle);
        result.setAuthor(author);
        result.setHeight(height);
        result.setWidth(width);
        result.setThickness(thickness);

        em.persist(result);
        return result;
    }

    /**
     * Update the Book parameter before calling this method!
     * @param book the updated Book object
     * @return updated book
     */
    @Transactional
    public Book updateBook(Book book) {
        Book result = em.merge(book);
        return result;
    }

    @Transactional
    public List<Book> searchBooks(String searchTerm) {
        Query query = em.createQuery(
                "from Book b where b.bookTitle like :title or b.author like :author");
        query.setParameter("title", "%" + searchTerm + "%");
        query.setParameter("author", "%" + searchTerm + "%");
        return query.getResultList();
    }

    @Transactional
    public List<Book> searchExactBooks(String searchTerm) {
        Query query = em.createQuery(
                "from Book b where b.bookTitle like :title or b.author like :author");
        query.setParameter("title", searchTerm);
        query.setParameter("author", searchTerm);
        return query.getResultList();
    }
}
