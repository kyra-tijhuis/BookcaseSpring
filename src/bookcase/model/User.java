package bookcase.model;

import java.util.ArrayList;

/**
 * Created by Kyra on 05/04/2016.
 */
public class User {
    private ArrayList<Bookcase> bookcaseList;
    private String name = "";

    public User() {bookcaseList = new ArrayList<Bookcase>();}

    // getters
    public String getName(){return name;}
    public ArrayList<Bookcase> getBookcaseList(){return bookcaseList;}

    // setters


    public void loginUser(String name, String passHash){
        boolean validLogin = (name.equals("Kyra") && passHash.equals("Jelle"));
            // add function for above line

        if (validLogin) {
            this.name = name;
            retrieveBookcases();
        }
    }


    public ArrayList<Bookcase> retrieveBookcases() {
        if (this.name.equals("")){
            return null;
        } else {
            // TODO this.bookcaseList = ....import Bookcase-list from database

            return this.bookcaseList;
        }
    }

    public void addBookcase(String name) {
        this.bookcaseList.add(new Bookcase(name, 80));
    }
    public void addBookcase(String name, int width) {
        this.bookcaseList.add(new Bookcase(name, width));
    }


    public static void main(String[] args) {

    }

    @Override
    public String toString() {
        return this.name;
    }
}

