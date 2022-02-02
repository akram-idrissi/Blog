package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;

public class Profile extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        User user = new User();
        if(request.getSession().getAttribute("user") != null){
            int userID = (int) request.getSession().getAttribute("user");
            user = UserDB.getUser("select * from User where id = " + userID);
            request.setAttribute("userObj", user);
            request.getRequestDispatcher("profile.jsp").forward(request, response);
        } else{
            response.sendRedirect("login.jsp");
        }
        
    }

}
