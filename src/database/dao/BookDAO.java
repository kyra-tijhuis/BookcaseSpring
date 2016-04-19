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

    public Book createBook(String isbn, String bookTitle, String author, int height, int width, int thickness, File cover) {
        Book result = getBook(isbn);
        if (result==null) {
            // new book
            result.setIsbn(isbn);
            result.setBookTitle(bookTitle);
            result.setAuthor(author);
            result.setHeight(height);
            result.setWidth(width);
            result.setThickness(thickness);
            result.setCover(cover);

            EntityManager em = emf.createEntityManager();
            EntityTransaction t = em.getTransaction();
            t.begin();
            em.persist(result);
            t.commit();
            em.close();
        }
        return result;
    }

}
