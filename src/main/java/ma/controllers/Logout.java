
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ma.constants.Page.*;


public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.invalidate();
        response.sendRedirect(TO_LOGOUT);
    }
}
