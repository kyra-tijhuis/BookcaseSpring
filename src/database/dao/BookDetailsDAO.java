package database.dao;

import database.model.BookDetails;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Kyra on 19/04/2016.
 */
public class BookDetailsDAO {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookcases");

    public BookDetails getBookDetails(int bookDetailsID) {

        return null;
    }
}
