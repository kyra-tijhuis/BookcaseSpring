package bookcase.model;

import org.apache.commons.validator.routines.ISBNValidator;
/**
 * Created by Student on 8-4-2016.
 */
public class ISBN {
    private String isbnNumber;
    public ISBN(String isbn) {
        this.isbnNumber = convertISBN(isbn);

    }

    public static boolean validateISBN (String isbn) {
        ISBNValidator validator = new ISBNValidator(true);
        return(validator.isValid(isbn));
    }

    public static String convertISBN (String isbn) {
        if (isbn.length() == 10) {
            ISBNValidator validator = new ISBNValidator(true);
            return (validator.convertToISBN13(isbn));
        } else if (isbn.length() == 13) {
            return isbn;
        } else {
            return null;
        }

    }

    @Override
    public boolean equals(Object O) {
        if (O instanceof ISBN) {
            if (((ISBN) O).isbnNumber.equals(this.isbnNumber)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return isbnNumber;
    }
}
