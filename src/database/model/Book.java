package database.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;

/**
 * Created by Kyra on 13/04/2016.
 */
@Entity
public class Book {
    private String isbn;
    private String bookTitle;
    private String author;
    private int height;
    private int width;
    private int thickness;
    private File cover;

    @Id
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getThickness() {
        return thickness;
    }

    public void setThickness(int thickness) {
        this.thickness = thickness;
    }

    public File getCover() {
        return cover;
    }

    public void setCover(File cover) {
        this.cover = cover;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (height != book.height) return false;
        if (width != book.width) return false;
        if (thickness != book.thickness) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!bookTitle.equals(book.bookTitle)) return false;
        return author.equals(book.author);

    }

    @Override
    public int hashCode() {
        int result = isbn.hashCode();
        result = 31 * result + bookTitle.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + height;
        result = 31 * result + width;
        result = 31 * result + thickness;
        return result;
    }
}
