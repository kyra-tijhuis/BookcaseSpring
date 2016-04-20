package database.dao;

import database.model.*;

/**
 * Created by Kyra on 15/04/2016.
 */
public class DAOTester {
      private void userTest() {
        UserDAO obj = new UserDAO();
        String userName = "Rudolf";
        obj.createUser(userName, "passwordpasswordpassword");
        User user = obj.getUser(userName);
        if (user!=null) {
            System.out.println(obj.correctPassword(userName, "passwordpasswordpassword"));
        } else {
            System.out.println("no user");
        }
    }

    private void bookTest() {

    }

    private void putBookInBookcase() {
        UserDAO userDAO = new UserDAO();
        BookcaseDAO bookcaseDAO = new BookcaseDAO();
        PlankDAO plankDAO = new PlankDAO();
        Book book = new BookDAO().getBook("1234567890124");
        User user = userDAO.getUser("Bob");
        Bookcase bookcase = bookcaseDAO.createBookcase("Kyra's tweede kast", 1000);
        user.getBookcases().add(bookcase);
        userDAO.updateUser(user);
        Plank plank = plankDAO.createPlank(270);
        bookcase.getPlanks().add(plank);
        bookcaseDAO.updateBookcase(bookcase);
        int index = plankDAO.firstEmptyOnPlank(plank.getPlankID());
        BookDetails details = new BookDetailsDAO().createBookDetails(book, Orientation.SPINE, plank, index);
        plank.getBooks().add(details);

        for (Bookcase b : user.getBookcases()) {
            System.out.println(b.getBookcaseID() + ": " + b.getBookcaseName());
        }
    }

    public static void main(String[] args) {
        DAOTester obj = new DAOTester();
        obj.userTest();
//        obj.bookTest();
        obj.putBookInBookcase();
        System.exit(0);
    }
}
