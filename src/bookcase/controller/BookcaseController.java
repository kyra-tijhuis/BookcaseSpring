package bookcase.controller;

import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import database.model.Book;
import database.model.BookDetails;
import database.model.Bookcase;
import database.model.Plank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by Student on 19-4-2016.
 */

@Controller
public class BookcaseController {

    @RequestMapping(value="/bookcase")
    public String bookcases (HttpServletRequest request, @ModelAttribute("error") String error, Model model) {
        ControllerFunctions.prepareLoginBar(request, error);
        if (true) { // TODO check if bookcase exists in DB and import it

            // temp
            Bookcase b = new Bookcase();
            Plank p = new Plank();
            BookDetails book = new BookDetails();
            book.setBook(new Book());
            book.getBook().setWidth(100);
            book.getBook().setHeight(70);
            ArrayList<BookDetails> listbook = new ArrayList<BookDetails>();
            listbook.add(book);
            listbook.add(book);
            p.setBooks(listbook);
            p.setHeight(200);
            ArrayList<Plank> listplank = new ArrayList<>();
            listplank.add(p);
            listplank.add(p);

            b.setWidth(500);
            b.setPlanks(listplank);
            b.setBookcaseName("Mooie Boeken");

            int height = -10;
            for (Plank plank: b.getPlanks()) {
                height += 15 + plank.getHeight();
            }

            model.addAttribute("bookcaseheight", height);
            model.addAttribute("bookcase", b);
            // end temp


            return "Bookcase";
        } else {
            return "InvalidBookcase";
        }
    }

    @ModelAttribute("SearchForm")
    public SearchForm createSearchForm() {
        return new SearchForm();
    }

    @ModelAttribute("LoginForm")
    public LoginForm createLoginForm() {
        return new LoginForm();
    }
}
