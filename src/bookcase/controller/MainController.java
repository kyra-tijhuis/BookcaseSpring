package bookcase.controller;

/**
 * Created by Student on 12-4-2016.
 */
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bookcase.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {

    @RequestMapping(value="/index", method=RequestMethod.GET)
    public String index(HttpServletRequest request, @ModelAttribute("error") String error) {

        prepareLoginBar(request, error);
        return "MainScreen";
    }




    @RequestMapping(value="/login", method=RequestMethod.POST)
    public String login(LoginForm login, HttpSession session, RedirectAttributes redirectAttributes){

        if (login.getUsername().equals("Kyra") && login.getPassword().equals("Jelle")) {        // TODO uit database trekken
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

    @RequestMapping(value="/logout", method=RequestMethod.POST)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:" + request.getParameter("url");
    }

    @RequestMapping(value={"/login", "/logout"}, method=RequestMethod.GET)
    public String loginGet(){
        return "redirect:/index";
    }



    @RequestMapping(value="/search", method=RequestMethod.GET)
    public String searchGet(HttpServletRequest request, @ModelAttribute("error") String error) {
        prepareLoginBar(request, error);
        return "SearchResults";
    }









    @RequestMapping("/user/*")
    public String user(HttpServletRequest request, @ModelAttribute("error") String error) {
        String url = request.getRequestURL().toString();
        request.setAttribute("username", url.substring(url.lastIndexOf("/")+1));
        if (request.getAttribute("username").equals("Kyra")) {
            prepareLoginBar(request, error);
            return "User";
        } else {
            prepareLoginBar(request, error);
            return "InvalidUser";
        }

    }






    @ModelAttribute("LoginForm")
    public LoginForm createLoginForm() {
        return new LoginForm();
    }

    @ModelAttribute("SearchForm")

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