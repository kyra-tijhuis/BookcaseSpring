package bookcase.model;

import java.util.ArrayList;

/**
 * Created by Student on 8-4-2016.
 */
public class Plank {
    private ArrayList<Book> plankBooks;
    private int height;
    private int width;

    private static final int defaultHeight = 275;

    // constructors for new planks
    public Plank (int width) {
        this(defaultHeight, width);

    }
    public Plank (int height, int width) {
        this.plankBooks = new ArrayList<Book>();
        this.width = width;
        if (height > 0) {
            this.height = height;
        } else {
            this.height = defaultHeight;
        }
    }

    // constructor for existing planks from database
    public Plank (ArrayList<Book> booklistFromDatabase, int width, int height) {
        this.height = height;
        this.width = width;
        this.plankBooks = booklistFromDatabase;
    }


    // setters
    public void setHeight(int height) {
        int minHeight = 0;
        for (Book b : plankBooks) {
            if (b.getHeight() > minHeight) {
                minHeight = b.getHeight();
            }
        }


        if (height > minHeight) {
            this.height = height;
        } else {
            // TODO Can't resize plank error!
        }
    }
    public void setWidth(int width) {
        if (width < getBooksThickness()) {
            // TODO Can't resize plank error!
        } else {
            this.width = width;
        }
    }

    // getters
    public ArrayList<Book> getBooksFromPlank() {
        return plankBooks;
    }


    public void addBook(Book addedBook) {
        // TODO what to do when books taller than plank height are added? auto resize plank? refuse addition?

        if ((addedBook.getOrientation() == Orientation.SPINE) && ((getBooksThickness() + addedBook.getThickness()) < this.width)) {
            this.plankBooks.add(addedBook);
        } else if ((addedBook.getOrientation() == Orientation.COVER) && ((getBooksThickness() + addedBook.getWidth()) < this.width)) {
            this.plankBooks.add(addedBook);
        } else {
            // TODO error, can't add, plank not wide enough
        }
    }

    public void rotateBook(int index) {
        if ((index < this.plankBooks.size()) && (index >= 0)) {

            int difference = this.plankBooks.get(index).getWidth() - this.plankBooks.get(index).getThickness();

            if (this.plankBooks.get(index).getOrientation() == Orientation.SPINE) {
                if ((getBooksThickness() + difference) < this.width) {
                    this.plankBooks.get(index).setOrientation(Orientation.COVER);
                    return;
                }
            } else {
                if ((getBooksThickness() - difference) < this.width) {
                    this.plankBooks.get(index).setOrientation(Orientation.SPINE);
                    return;
                }
            }
        }

        // TODO Can't rotate book error!


    }



    public int getBooksThickness(){
        int totalBooksThickness = 0;
        for (Book b : plankBooks) {
            if (b.getOrientation() == Orientation.SPINE) {
                totalBooksThickness += b.getThickness();
            } else {
                totalBooksThickness += b.getWidth();
            }
        }
        return totalBooksThickness;
    }




    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder("== Plank ==\n");
        for (Book b : plankBooks) {
            returnString.append(b);
        }
        return returnString.toString();
    }



}
