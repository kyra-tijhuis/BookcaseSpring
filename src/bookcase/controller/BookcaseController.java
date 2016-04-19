package bookcase.controller;

import bookcase.forms.LoginForm;
import bookcase.forms.SearchForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Student on 19-4-2016.
 */

@Controller
public class BookcaseController {

    @RequestMapping(value="/bookcase")
    public String bookcases (HttpServletRequest request, @ModelAttribute("error") String error) {
        ControllerFunctions.prepareLoginBar(request, error);
        if (true) { // TODO check if bookcase exists
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
