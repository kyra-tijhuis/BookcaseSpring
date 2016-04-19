package database.model;

import com.sun.istack.internal.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Kyra on 15/04/2016.
 */
@Entity
public class BookDetails {
    private int bookDetailsID;
    private Book book;
    private Orientation orientation;
    private int bookIndex;
    private Plank plank;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getBookDetailsID() {
        return bookDetailsID;
    }

    public void setBookDetailsID(int bookDetailsID) {
        this.bookDetailsID = bookDetailsID;
    }

    @NotNull
    @OneToOne(cascade = {CascadeType.REMOVE}, orphanRemoval = true, targetEntity = Book.class)
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Enumerated(EnumType.STRING)
    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public int getBookIndex() {
        return bookIndex;
    }

    public void setBookIndex(int bookIndex) {
        this.bookIndex = bookIndex;
    }

    @ManyToOne(targetEntity = Plank.class,cascade = {CascadeType.ALL})
    @JoinColumn(name = "plankID")
    public Plank getPlank() {
        return plank;
    }

    public void setPlank(Plank plank) {
        this.plank = plank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDetails details = (BookDetails) o;

        if (bookDetailsID != details.bookDetailsID) return false;
        if (!book.equals(details.book)) return false;
        return plank.equals(details.plank);

    }

    @Override
    public int hashCode() {
        int result = bookDetailsID;
        result = 31 * result + book.hashCode();
        result = 31 * result + plank.hashCode();
        return result;
    }
}
