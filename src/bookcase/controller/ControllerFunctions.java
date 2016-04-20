package bookcase.controller;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Student on 19-4-2016.
 */
public class ControllerFunctions {
    public static void prepareLoginBar(HttpServletRequest request, String error) {
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
