package database.dao;

import database.model.*;

/**
 * Created by Kyra on 15/04/2016.
 */
public class DAOTester {
      private void userTest() {
        UserDAO obj = new UserDAO();
        String userName = "Alice";
        obj.createUser(userName, "password");
        User user = obj.getUser(userName);
        if (user!=null) {
            System.out.println(obj.correctPassword(userName, "password"));
        } else {
            System.out.println("no user");
        }
    }

    private void bookTest() {
        BookDAO bookDAO = new BookDAO();
        bookDAO.createBook("9780545139700", "Harry Potter and the Deathly Hallows", "JK Rowling", 250, 150, 20);
        for (Book b : bookDAO.searchBooks("Titl")) {
            System.out.println(b);
        }
        for (Book b : bookDAO.searchExactBooks("Man")) {
            System.out.println(b);
        }
    }

    private void putBookInBookcase() {
        UserDAO userDAO = new UserDAO();
        BookcaseDAO bookcaseDAO = new BookcaseDAO();
        PlankDAO plankDAO = new PlankDAO();
        Book book = new BookDAO().getBook("9780545139700");
        User user = userDAO.getUser("Alice");
        Bookcase bookcase = bookcaseDAO.createBookcase("Harry Potter", 800);
        user.getBookcases().add(bookcase);
        userDAO.updateUser(user);
        Plank plank = plankDAO.createPlank(300);
        bookcase.getPlanks().add(plank);
        bookcaseDAO.updateBookcase(bookcase);
        int index = plankDAO.firstEmptyOnPlank(plank.getPlankID());
        BookDetails details = new BookDetailsDAO().createBookDetails(book, Orientation.SPINE, plank, index);
        plank.getBooks().add(details);

        for (Bookcase b : user.getBookcases()) {
            bookcaseDAO.getUserFromBookcase(b);
            System.out.println(b.getBookcaseID() + ": " + b.getBookcaseName());
        }
    }

    public static void main(String[] args) {
        DAOTester obj = new DAOTester();
//        obj.userTest();
        obj.bookTest();
        obj.putBookInBookcase();
//        new BookcaseDAO().getAllBookcases();
        System.exit(0);
    }
}
