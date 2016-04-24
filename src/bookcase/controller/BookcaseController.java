package bookcase.controller;

import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import database.dao.*;
import database.goodreadsAPI.GoodreadsDAO;
import database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Student on 19-4-2016.
 */

@Controller
public class BookcaseController {

    @Autowired
    private DataOperations dao;

    @RequestMapping(value="/bookcase")
    public String bookcases(HttpServletRequest request, HttpServletResponse resp, @ModelAttribute("error") String error, Model model) {
        ControllerFunctions.prepareLoginBar(request, error);
        try {
            int ID = Integer.parseInt(request.getParameter("id"));
            Bookcase b = dao.getBookcase(ID);
            String username = dao.userNameFromBookcase(b);
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
    public @ResponseBody String addplank(String username, int bookcaseID, HttpSession session, HttpServletResponse resp) {
        if (session.getAttribute("user").equals(username)) {
//            Plank plank = plankDAO.createPlank(300);
//            Bookcase bookcase = bookcaseDAO.getBookcase(bookcaseID);
//            bookcase.getPlanks().add(plank);
//            bookcaseDAO.updateBookcase(bookcase);
//            return "" + plank.getPlankID();
            return dao.addNewPlankToBookcase(bookcaseID, 300);
        }
        try { // TODO in else
            resp.sendError(401);
        } catch (IOException io) {

        }
        return null;
    }

    @RequestMapping(value="/addbook")
    public @ResponseBody String[] addbook(String username, String plankID, String isbn, String title, String author, HttpSession session, HttpServletResponse resp) {
        String[] returnstring = new String[4];

        System.out.println("addBook: " + plankID + " " + isbn + " " + title + " " + author);


        Book b = dao.createBook(isbn, title, author);
        Plank p = dao.getPlank(plankID);
        dao.createBookDetails(b, p);

        String width = "" + b.getWidth();
        String bookheight = "" + b.getHeight();
        String plankheight = "" + p.getHeight();

        returnstring[0]= width;
        returnstring[1]= bookheight;
        returnstring[2]= plankheight;
        returnstring[3]= new GoodreadsDAO().getImage(isbn);


        if (session.getAttribute("user") != null && session.getAttribute("user").equals(username)) {
            return returnstring;
        } else {
            System.out.println("hallo"); // TODO iets nuttigs
        }
        try { // TODO verplaatsen?
            resp.sendError(401);
        } catch (IOException io) {

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
