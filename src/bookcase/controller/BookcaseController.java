package bookcase.controller;

import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import database.dao.BookDAO;
import database.dao.BookDetailsDAO;
import database.dao.BookcaseDAO;
import database.goodreadsAPI.GoodreadsDAO;
import database.dao.PlankDAO;
import database.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Student on 19-4-2016.
 */

@Controller
public class BookcaseController {
    @Autowired
    private BookDetailsDAO bookDetailsDAO;

    @Autowired
    private BookcaseDAO bookcaseDAO;

    @Autowired
    private PlankDAO plankDAO;

    @Autowired
    private BookDAO bookDAO;

    @RequestMapping(value="/bookcase")
    public String bookcases(HttpServletRequest request, HttpServletResponse resp, @ModelAttribute("error") String error, Model model) {
        ControllerFunctions.prepareLoginBar(request, error);
        try {
            int ID = Integer.parseInt(request.getParameter("id"));
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
    public @ResponseBody String addplank(String username, int bookcaseID, HttpSession session, HttpServletResponse resp) {
        if (session.getAttribute("user").equals(username)) {
            Plank plank = plankDAO.createPlank(300);
            Bookcase bookcase = bookcaseDAO.getBookcase(bookcaseID);
            bookcase.getPlanks().add(plank);
            bookcaseDAO.updateBookcase(bookcase);
            return "" + plank.getPlankID();
        }
        try {
            resp.sendError(401);
        } catch (IOException io) {

        }
        return null;
    }

    @RequestMapping(value="/addbook")
    public @ResponseBody String[] addbook(String username, String plankID, String ISBN, String Title, String Author, HttpSession session, HttpServletResponse resp) {
        String[] returnstring = new String[4];

        System.out.println(plankID + " " + ISBN + " " + Title + " " + Author);





        Book b = bookDAO.createBook(ISBN, Title, Author, 200, 150, 20);
        Plank p = plankDAO.getPlank(Integer.parseInt(plankID));
        BookDetails details = bookDetailsDAO.createBookDetails(b, Orientation.COVER, p, plankDAO.firstEmptyOnPlank(p.getPlankID()));
        p.getBooks().add(details);
        plankDAO.updatePlank(p);
        GoodreadsDAO goodreadsDAO = new GoodreadsDAO();



        String width = "" + b.getWidth();
        String bookheight = "" + b.getHeight();
        String plankheight = "" + p.getHeight();

        returnstring[0]= width;
        returnstring[1]= bookheight;
        returnstring[2]= plankheight;
        returnstring[3]= goodreadsDAO.getImage(ISBN);


        if (session.getAttribute("user") != null && session.getAttribute("user").equals(username)) {

            return returnstring;
        } else {
            System.out.println("hallo");
        }
        try {
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
