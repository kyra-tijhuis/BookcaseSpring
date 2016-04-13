package bookcase.controller;

/**
 * Created by Student on 12-4-2016.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import bookcase.forms.LoginForm;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        request.setAttribute("address", request.getRequestURL());
        return "LoginBar";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(LoginForm login, HttpSession session, Model model){

        if (login.getUsername().equals("Kyra") && login.getPassword().equals("Jelle")) {        // check in database
            session.setAttribute("user", "Kyra");




            return "redirect:" + login.getUrl();
        } else {
            return "redirect:" + login.getUrl();
        }
    }





    @ModelAttribute("LoginForm")
    public LoginForm createLoginForm() {
        return new LoginForm();
    }
}