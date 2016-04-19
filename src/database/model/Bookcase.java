package database.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kyra on 13/04/2016.
 */
@Entity
public class Bookcase {
    private String bookcaseName;
    private int bookcaseID;
    private int width;
    private Collection<Plank> planks;

    public String getBookcaseName() {
        return bookcaseName;
    }

    public void setBookcaseName(String bookcaseName) {
        this.bookcaseName = bookcaseName;
    }

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    public int getBookcaseID() {
        return bookcaseID;
    }

    public void setBookcaseID(int bookcaseID) {
        this.bookcaseID = bookcaseID;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true, targetEntity = Plank.class)
    public Collection<Plank> getPlanks() {
        return planks;
    }

    public void setPlanks(Collection<Plank> planks) {
        this.planks = planks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bookcase bookcase = (Bookcase) o;

        if (bookcaseID != bookcase.bookcaseID) return false;
        return planks != null ? planks.equals(bookcase.planks) : bookcase.planks == null;

    }

    @Override
    public int hashCode() {
        int result = bookcaseID;
        result = 31 * result + (planks != null ? planks.hashCode() : 0);
        return result;
    }
}
