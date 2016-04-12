package bookcase.model;

import java.util.ArrayList;

/**
 * Created by Kyra on 05/04/2016.
 */
public class Bookcase {
    private ArrayList<Plank> bookcasePlanks = new ArrayList<Plank>();
    private String name = "";
    private int width;

    // constructors, perhaps not all required
    public Bookcase(String name, int width) {
        this.name = name;
        this.width = width;
        if (bookcasePlanks.size() == 0) {
            bookcasePlanks.add(new Plank(this.width));
        }
    }
    public Bookcase(String name, int width, ArrayList<Plank> importPlankList) {
        this(name, width);
        this.bookcasePlanks = importPlankList;
    }

    // getters
    public String getName(){
        return this.name;
    }
    public ArrayList<Plank> getBookcasePlanks(){return this.bookcasePlanks;}
    public int getWidth() {
        return this.width;
    }

    // setters
    public void setName(String name){
        this.name = name;
    }
    public void setWidth(int width) {
        for (Plank p : bookcasePlanks) {
            if (p.getBooksThickness() > width) {
                // TODO Can't resize bookcase error!
                return;
            }
        }
        this.width = width;

        for (Plank p : bookcasePlanks) {
            p.setWidth(width);
        }
    }

    public void addPlank() {
        bookcasePlanks.add(new Plank(this.width));
    }

    @Override
    public String toString() {
        StringBuilder returnString = new StringBuilder(this.name + "\n");
        for (Plank p : bookcasePlanks) {
            returnString.append(p);
        }
        return returnString.toString();

    }

}
