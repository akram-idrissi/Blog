
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;

import static ma.constants.Page.*;

public class Profile extends HttpServlet {

   
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int userID = (int) request.getSession().getAttribute("user");
        User user = UserDB.getUser("select * from User where id = " + userID);
        
        request.setAttribute("user", user);
        request.getRequestDispatcher(TO_PROFILE).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println(this.getServletContext().getResourcePaths("/"));

    }
}
