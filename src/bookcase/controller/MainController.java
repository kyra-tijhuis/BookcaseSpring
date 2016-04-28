package bookcase.controller;

/**
 * Created by Student on 12-4-2016.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;


import bookcase.forms.AddCaseForm;
import bookcase.forms.SignupForm;
import database.dao.BookcaseDAO;
import database.dao.DataOperations;
import database.dao.PlankDAO;
import database.dao.UserDAO;
import database.model.Bookcase;
import database.model.Plank;
import database.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.List;


@Controller
public class MainController {

    @Autowired
    private DataOperations dao;

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute("error") String error) {
        ControllerFunctions.prepareLoginBar(request, error);
        return "MainScreen";
    }

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(LoginForm login, HttpSession session, RedirectAttributes redirectAttributes){
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

    @RequestMapping(value="/search")
    public String searchGet(HttpServletRequest request, Model model, @ModelAttribute("error") String error, @ModelAttribute("SearchForm") @Valid SearchForm query) {
        List<Bookcase> bookcaseList;
        System.out.println("\n");

        ControllerFunctions.prepareLoginBar(request, error);

        if ((query.getBookcaseName() != null) && (!query.getBookcaseName().isEmpty())) {
            bookcaseList = dao.searchBookcaseName(query.getBookcaseName());
        } else if ((query.getUsername() != null) && (!query.getUsername().isEmpty())) {
            bookcaseList = dao.searchBookcasesUser(query.getUsername());
        } else if (query.getBookName() != null && !query.getBookName().isEmpty()) {
            bookcaseList = dao.searchBooks(query.getBookName());
        } else {
            bookcaseList = dao.getAllBookcases();
        }


        model.addAttribute("searchlist", bookcaseList);
        model.addAttribute("dao", dao);

        return "SearchResults";
    }

    @RequestMapping("/user/*")
    public String user(HttpServletRequest request, Model model, @ModelAttribute("error") String error, @ModelAttribute("AddCaseForm") @Valid AddCaseForm addCaseForm, BindingResult result) {
        ControllerFunctions.prepareLoginBar(request, error);

        String url = request.getRequestURL().toString();
        User activeUser = dao.getUser(url.substring(url.lastIndexOf("/")+1).toString());

        if (result.hasErrors()) {
            System.out.println(result.getFieldError("name"));
        }

        if (activeUser != null) {
            request.setAttribute("userName", activeUser.getUserName());
            request.setAttribute("searchlist", activeUser.getBookcases());
            return "User";
        } else {
            return "InvalidUser";
        }

    }

    @RequestMapping(value="/addcase", method=RequestMethod.POST)
    public String addbookcase(@Valid AddCaseForm inputform, BindingResult result, HttpSession session) {
        if (inputform.getUser().equals(session.getAttribute("user"))) {
            User user = dao.getUser(inputform.getUser());
            if (user != null) {
                for (Bookcase b :  user.getBookcases()) {
                    if (b.getBookcaseName().equals(inputform.getName())) {
                        result.addError(new FieldError("AddCaseForm", "name", "Bookcase already exists"));
                    }
                }
                if (!result.hasErrors()) {
                    int bookcaseID = dao.addNewBookcaseToUser(user, inputform.getName());
                    dao.addNewPlankToBookcase(bookcaseID, 300);
                }
            }
        }
        return "redirect:/user/" + inputform.getUser();
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

    @ModelAttribute("AddCaseForm")
    public AddCaseForm createAddCaseForm() {
        return new AddCaseForm();
    }
    // preparation of loginbar by giving possible errors and the url of source page

}