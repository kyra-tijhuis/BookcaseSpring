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
        new BookDAO().createBook("1234567890124", "Title", "Author", 250, 150, 20);
    }

    private void putBookInBookcase() {
        UserDAO userDAO = new UserDAO();
        BookcaseDAO bookcaseDAO = new BookcaseDAO();
        PlankDAO plankDAO = new PlankDAO();
        Book book = new BookDAO().getBook("1234567890124");
        User user = userDAO.getUser("Alice");
        Bookcase bookcase = bookcaseDAO.createBookcase("Boekenkast", 800);
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
//        obj.bookTest();
        obj.putBookInBookcase();
        System.exit(0);
    }
}
