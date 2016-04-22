package database.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kyra on 14/04/2016.
 */
@Entity
public class Plank {
    private int plankID;
    private int height;
    private List<BookDetails> books;

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getPlankID() {
        return plankID;
    }

    public void setPlankID(int plankID) {
        this.plankID = plankID;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, orphanRemoval = true, targetEntity = BookDetails.class)
    public List<BookDetails> getBooks() {
        return books;
    }

    public void setBooks(List<BookDetails> books) {
        this.books = books;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Plank plank = (Plank) o;

        if (plankID != plank.plankID) return false;
        return books != null ? books.equals(plank.books) : plank.books == null;

    }

    @Override
    public int hashCode() {
        int result = plankID;
        result = 31 * result + (books != null ? books.hashCode() : 0);
        return result;
    }
}
