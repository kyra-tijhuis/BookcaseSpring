package database;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import bookcase.model.*;

import static java.awt.SystemColor.text;

/**
 * Created by Kyra on 05/04/2016.
 */
public class DB implements DBConstants {

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement prepStatement = null;

    public void readDataBase() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3456/bookcase?" +
                            "user=bookcase&password=Ballerup2750!");
            statement = connection.createStatement();

            getAllBooks();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getUserSalt(String userName) throws SQLException {
        ResultSet resultSet = statement.executeQuery(
                "select ");
        return null;
    }

    public ArrayList<Book> getAllBooks() throws SQLException {
        // ResultSet is initially before the first data set
        ArrayList<Book> allBooks = new ArrayList<>();

        ResultSet resultSet = statement.executeQuery("select * from book");

        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
            int rowcount = 0;
            if (resultSet.last()) {
                rowcount = resultSet.getRow();
                resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            while (resultSet.next()) {
                String title = resultSet.getString(resultSet.findColumn(DBConstants.BOOK_TITLE));
                String author = resultSet.getString(resultSet.findColumn(DBConstants.BOOK_AUTHOR));
                Long isbn = Long.parseLong(resultSet.getString(resultSet.findColumn(DBConstants.ISBN)));
                // allBooks.add(new Book(isbn, title, author));
            }
        }
        return null;
    }

    public boolean addBook(long isbn, String title, String author, int height, int thickness, int width, Object cover) {
        String strISBN = String.valueOf(isbn);
        // if ISBN already in database
        return false;
        // else: add book to database
    }

    public void addUser(String userName, String password) {

    }

    public static void main(String[] args) {
        DB obj = new DB();
        obj.addUser("Kyra", "hallo");
//        obj.readDataBase();
    }
}
