package ma.controllers;

import java.util.Objects;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;

public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String[] fields = new String[]{username, password};
        String usnQuery = String.format("select * from User where username = '%s' ", username);
        
        // If any input is empty
        if (Methods.any(fields)) {
            Methods.setMessageInfo(response, request, EMPTY, false);
        } 
        
        // If the email do not exist
        else if (Objects.isNull(UserDB.getUser(usnQuery))){
            Methods.setMessageInfo(response, request, USN_ERR, false);
        }
        else {
            User user = UserDB.getUser(usnQuery);
            
            if(Objects.nonNull(user)){
                
                String saltDB = user.getSalt();
                String hashDB = user.getHashedpass();
                
                // If the pass != hashDB show error msg else send user to home
                if (Objects.isNull(Methods.verifyHash(password, saltDB, hashDB))) {
                    Methods.setMessageInfo(response, request, PASS_ERR, false);
                    
                } else {
                    Methods.setMessageInfo(response, request, NONE, true);
                    session.setAttribute("user", user.getId());
                    
                    response.sendRedirect(TO_HOME_S);
                }
            }
        }
        
        
    }
}