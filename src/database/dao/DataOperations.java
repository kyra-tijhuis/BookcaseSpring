package database.dao;

import database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Kyra on 24/04/2016.
 */
@Repository
public class DataOperations {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private BookcaseDAO bookcaseDAO;

    @Autowired
    private PlankDAO plankDAO;

    @Autowired
    private BookDAO bookDAO;

    @Autowired
    private BookDetailsDAO bookDetailsDAO;

    public void createUser() {

    }

    public Bookcase createBookcase(String bookcaseName) {
        return bookcaseDAO.createBookcase(bookcaseName, 800);
    }

    public Plank createPlank(int plankHeight) {
        return plankDAO.createPlank(plankHeight);
    }

    /**
     * Creates a book if it not exists in the database and returns it,
     * if the book does exist (isbn is already in database), that book
     * is returned.
     * @param isbn
     * @param title
     * @param author
     * @return book
     */
    public Book createBook(String isbn, String title, String author) {
        Book existingBook = bookDAO.getBook(isbn);
        if (existingBook == null) {
            return bookDAO.createBook(isbn, title, author, 200, 150, 20);
        } else {
            return existingBook;
        }
    }

    public BookDetails createBookDetails(Book b, Plank p) {
        BookDetails result = bookDetailsDAO.createBookDetails(b, Orientation.COVER, p, plankDAO.firstEmptyOnPlank(p.getPlankID()));
        p.getBooks().add(result);
        plankDAO.updatePlank(p);
        return result;
    }

    public User getUser(String userName) {
        return userDAO.getUser(userName);
    }

    public Bookcase getBookcase(int bookcaseID) {
        return bookcaseDAO.getBookcase(bookcaseID);
    }

    public Plank getPlank(String plankID) {
        int ID = Integer.parseInt(plankID);
        return plankDAO.getPlank(ID);
    }

    /**
     *
     * @param bookcaseID
     * @return plankID of created plank as String
     */
    public String addNewPlankToBookcase(int bookcaseID, int plankHeight) {
        Plank plank = createPlank(plankHeight);
        Bookcase bookcase = getBookcase(bookcaseID);
        bookcase.getPlanks().add(plank);
        bookcaseDAO.updateBookcase(bookcase);
        return "" + plank.getPlankID();
    }


    public void addBookToPlank() {

    }

    public String userNameFromBookcase(Bookcase bookcase) {
        return bookcaseDAO.getUserFromBookcase(bookcase).getUserName();
    }

}