package bookcase.model;

import org.apache.commons.validator.routines.ISBNValidator;

/**
 * Created by Student on 8-4-2016.
 */
public class Tester {
    public static void main(String[] args) {

        User KyraJelle = new User();
        KyraJelle.loginUser("Kyra", "Jelle");


        KyraJelle.addBookcase("Mooie boeken", 300);

        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(new Book("9789045704241", "Over het water1", "H.M. van den Brink"));
        Book b = new Book("9789045704241", "Over het water2", "H.M. van den Brink");
        b.setOrientation(Orientation.COVER);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(b);
        KyraJelle.getBookcaseList().get(0).addPlank();
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(new Book("9789045704241", "Over het water3", "H.M. van den Brink"));
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(new Book("9789045704241", "Over het water4", "H.M. van den Brink", 100, 100, 100));
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(new Book("9789045704241", "Over het water5", "H.M. van den Brink", 100, 100, 100));

        KyraJelle.getBookcaseList().get(0).setWidth(400);

        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).addBook(new Book("9789045704241", "Over het water6", "H.M. van den Brink", 100, 100, 100));


        KyraJelle.getBookcaseList().get(0).addPlank();
        KyraJelle.getBookcaseList().get(0).addPlank();

        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(2).addBook(new Book("9879036540582", "Photodeposition of platinum nanoparticles on well-defined tungsten oxide", "K. Wenderich"));


        System.out.println(KyraJelle + "\n" + KyraJelle.getBookcaseList().get(0));

        for (Book B : KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).getBooksFromPlank()) {
            System.out.println(B.getOrientation());
        }

        System.out.println("\n");

        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(0);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(1);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(2);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(3);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(4);
        KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).rotateBook(5);

        for (Book B : KyraJelle.getBookcaseList().get(0).getBookcasePlanks().get(0).getBooksFromPlank()) {
            System.out.println(B.getOrientation());
        }






    }
}
