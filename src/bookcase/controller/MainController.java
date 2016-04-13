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
    public String index(HttpServletRequest request, @ModelAttribute("error") String error) {

        prepareLoginBar(request, error);
        return "MainScreen";
    }

    @RequestMapping(value="/login", method= RequestMethod.POST)
    public String login(LoginForm login, HttpSession session, RedirectAttributes redirectAttributes){

        if (login.getUsername().equals("Kyra") && login.getPassword().equals("Jelle")) {        // check in database
            session.setAttribute("user", "Kyra");




            return "redirect:" + login.getUrl();
        } else {
            if (login.getUsername().equals("Kyra")) {
                redirectAttributes.addFlashAttribute("error", "password");
            } else {
                redirectAttributes.addFlashAttribute("error", "username");
            }


            return "redirect:" + login.getUrl();
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/index";
    }



    @ModelAttribute("LoginForm")
    public LoginForm createLoginForm() {
        return new LoginForm();
    }


    // preparation of loginbar by giving possible errors and the url of source page
    public void prepareLoginBar(HttpServletRequest request, String error) {
        request.setAttribute("address", request.getRequestURL());
        if (error.equals("password")) {
            request.setAttribute("pwPlaceholder", "Invalid Password!");
            request.setAttribute("unPlaceholder", "username");
        } else if (error.equals("username")) {
            request.setAttribute("pwPlaceholder", "password");
            request.setAttribute("unPlaceholder", "User does not exist!");
        } else {
            request.setAttribute("pwPlaceholder", "password");
            request.setAttribute("unPlaceholder", "username");
        }

    }
}