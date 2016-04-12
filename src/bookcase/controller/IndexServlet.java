package bookcase.controller;

import java.io.IOException;

/**
 * Created by Student on 11-4-2016.
 */
@javax.servlet.annotation.WebServlet(name = "IndexServlet", urlPatterns = {"/index", "/Index"})
public class IndexServlet extends javax.servlet.http.HttpServlet {
    public static final String LOGIN_BAR = "/WEB-INF/LoginBar.jsp";
    public static final String MAIN_SCREEN = "/WEB-INF/MainScreen.jsp";

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        if (request.getParameter("submit").equals("submit")) {
            request.getSession().invalidate();
            doGet(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setAttribute("address", request.getRequestURL().toString());
        request.getRequestDispatcher(MAIN_SCREEN).forward(request, response);
    }
}
