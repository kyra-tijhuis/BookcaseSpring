package bookcase.controller;

import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import database.dao.BookcaseDAO;
import database.goodreadsAPI.GoodreadsDAO;
import database.dao.PlankDAO;
import database.model.Book;
import database.model.BookDetails;
import database.model.Bookcase;
import database.model.Plank;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 * Created by Student on 19-4-2016.
 */

@Controller
public class BookcaseController {

    @RequestMapping(value="/bookcase")
    public String bookcases (HttpServletRequest request, @ModelAttribute("error") String error, Model model) {
        ControllerFunctions.prepareLoginBar(request, error);
        try {
            int ID = Integer.parseInt(request.getParameter("id"));
            BookcaseDAO bookcaseDAO = new BookcaseDAO();
            Bookcase b = bookcaseDAO.getBookcase(ID);
            String username = bookcaseDAO.getUserFromBookcase(b).getUserName();
            if (b!=null) { // TODO check if bookcase exists in DB and import it

                int height = -10;
                for (Plank plank: b.getPlanks()) {
                    height += 15 + plank.getHeight();
                }

                model.addAttribute("bookcaseheight", height);
                model.addAttribute("bookcase", b);
                
                model.addAttribute("goodreadsDAO", new GoodreadsDAO());
                model.addAttribute("userName", username);


                return "Bookcase";
            } else {
                return "InvalidBookcase";
            }
        } catch (NumberFormatException e) {
            return "InvalidBookcase";
        }
    }


    @RequestMapping(value="/addplank")
    public @ResponseBody String addplank(String username, int bookcaseID, HttpSession session) {
        if (session.getAttribute("user").equals(username)) {
            PlankDAO plankDAO = new PlankDAO();
            Plank plank = plankDAO.createPlank(300);
            BookcaseDAO bookcaseDAO = new BookcaseDAO();
            Bookcase bookcase = bookcaseDAO.getBookcase(bookcaseID);
            bookcase.getPlanks().add(plank);
            bookcaseDAO.updateBookcase(bookcase);
            System.out.println(plank.getPlankID());
            return "" + plank.getPlankID();
        }
        return null;
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
