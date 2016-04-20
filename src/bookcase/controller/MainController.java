package bookcase.controller;

/**
 * Created by Student on 12-4-2016.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import bookcase.forms.SignupForm;
import database.dao.UserDAO;
import database.model.Bookcase;
import database.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


@Controller
public class MainController {

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute("error") String error) {

        ControllerFunctions.prepareLoginBar(request, error);
        return "MainScreen";
    }




    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(LoginForm login, HttpSession session, RedirectAttributes redirectAttributes){
        UserDAO dao = new UserDAO();

        if (dao.getUser(login.getUsername()) != null) {
            if (dao.correctPassword(login.getUsername(), login.getPassword())) {
                session.setAttribute("user", login.getUsername());
            } else {
                redirectAttributes.addFlashAttribute("error", "password");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "username");
        }
        return "redirect:" + login.getUrl();
    }

    @RequestMapping(value="/logout", method=RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:" + request.getParameter("url");
    }

    @RequestMapping(value={"/login", "/logout"}, method=RequestMethod.GET)
    public String loginGet(){
        return "redirect:/index";
    }


    @RequestMapping(value="/search")
    public String searchGet(HttpServletRequest request, @ModelAttribute("error") String error, @ModelAttribute("SearchForm") @Valid SearchForm query) {
        ControllerFunctions.prepareLoginBar(request, error);

        // ArrayList<Bookcase> bookcaselist = new ArrayList<>();
        // TODO: get list from database and name it bookcaselist;

        // request.setAttribute("searchlist", bookcaselist);
        return "SearchResults";
    }

    @RequestMapping(value="/signup", method=RequestMethod.GET)
    public String signup(HttpServletRequest request, @ModelAttribute("error") String error) {
        ControllerFunctions.prepareLoginBar(request, error);
        return "Signup";
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signupPost(HttpServletRequest request, HttpSession session, @ModelAttribute("error") String error, @ModelAttribute("SignupForm") @Valid SignupForm userForm, BindingResult result) {
        ControllerFunctions.prepareLoginBar(request, error);

        if (!(userForm.getPassword()).equals(userForm.getPassword2())) {
            result.addError(new FieldError("userForm", "password2", "Passwords do not match!"));
            return "Signup";
        }

        if(!result.hasErrors()) {
            UserDAO dao = new UserDAO();
            User user = dao.createUser(userForm.getUsername(), userForm.getPassword());
            if (user == null) {
                result.addError(new FieldError("userForm", "username", "Username taken!"));
            }
        }



        if (result.hasErrors()) {
            return "Signup";
        }


        session.setAttribute("user", userForm.getUsername());

        return "redirect:/index";
    }



    @RequestMapping("/user/*")
    public String user(HttpServletRequest request, @ModelAttribute("error") String error) {
        ControllerFunctions.prepareLoginBar(request, error);

        String url = request.getRequestURL().toString();
        User activeUser = new UserDAO().getUser(url.substring(url.lastIndexOf("/")+1).toString());
        request.setAttribute("userName", activeUser.getUserName());
        if (activeUser != null) {
            request.setAttribute("searchlist", activeUser.getBookcases());

            return "User";
        } else {

            return "InvalidUser";
        }

    }






    @ModelAttribute("LoginForm")
    public LoginForm createLoginForm() {
        return new LoginForm();
    }

    @ModelAttribute("SearchForm")
    public SearchForm createSearchForm() {
        return new SearchForm();
    }

    @ModelAttribute("SignupForm")
    public SignupForm createSignupForm() {
        return new SignupForm();
    }

    // preparation of loginbar by giving possible errors and the url of source page

}