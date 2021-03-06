package database.dao;

import database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    public User createUser(String userName, String password) {
        return userDAO.createUser(userName, password);
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
     * @return book with standard size
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

    public void removeBookDetails(String bookDetailsID) {
        int detailsID = Integer.parseInt(bookDetailsID);
        BookDetails details = bookDetailsDAO.getBookDetails(detailsID);
        bookDetailsDAO.deleteBookDetails(details);
        System.out.println(":(");
    }

    public int addNewBookcaseToUser(User user, String bookcaseName) {
        Bookcase bookcase = bookcaseDAO.createBookcase(bookcaseName, 1000);
        user.getBookcases().add(bookcase);
        userDAO.updateUser(user);
        return bookcase.getBookcaseID();
    }

    public String addNewPlankToBookcase(int bookcaseID, int plankHeight) {
        Plank plank = createPlank(plankHeight);
        Bookcase bookcase = getBookcase(bookcaseID);
        bookcase.getPlanks().add(plank);
        bookcaseDAO.updateBookcase(bookcase);
        return "" + plank.getPlankID();
    }

    public String userNameFromBookcase(Bookcase bookcase) {
        return bookcaseDAO.getUserFromBookcase(bookcase).getUserName();
    }

    public List<Bookcase> getAllBookcases() {
        return bookcaseDAO.getAllBookcases();
    }

    public boolean correctPassword(String userName, String password) {
        return userDAO.correctPassword(userName, password);
    }

    public List<Bookcase> searchBookcaseName(String bookcaseName) {
        return bookcaseDAO.searchBookcases(bookcaseName);
    }

    public List<Bookcase> searchBookcasesUser(String userName) {
        List<Bookcase> result = new ArrayList<>();
        List<Bookcase> temp = bookcaseDAO.getAllBookcases();
        for (Bookcase b : temp) {
            if (bookcaseDAO.getUserFromBookcase(b).getUserName().contains(userName)) {
                result.add(b);
            }
        }
        return result;
    }

    public List<Bookcase> searchBooks(String searchTerm) {
        List<Bookcase> result = new ArrayList<>();
        for (Bookcase bookcase : getAllBookcases()) {
            if (bookcaseDAO.bookInBookcase(bookcase, searchTerm)) {
                result.add(bookcase);
            }
        }
        return result;
    }
}
