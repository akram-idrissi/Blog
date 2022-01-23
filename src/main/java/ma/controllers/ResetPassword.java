
package ma.controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;
import ma.util.PasswordUtil;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


public class ResetPassword extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user;
        
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("user-non-valid-id");
        user = UserDB.getUser("select * from User where id = " + userId);
        
        String pass = request.getParameter("new-pass");
        String confirm = request.getParameter("new-confirm");
        
        if (Methods.any(new String[]{pass, confirm})){
            Methods.setMessageInfo(response, request, EMPTY, false);
        }
        else if (!Methods.isPassword(pass)){
            Methods.setMessageInfo(response, request, PASS_ERR, false);
        }
        else{
            if (pass.equals(confirm)){
                Methods.setMessageInfo(null, request, NONE, true);
                
                String salt = PasswordUtil.getSalt();
                String hash = PasswordUtil.hashPassword(pass + salt);
                
                user.setSalt(salt);
                user.setHashedpass(hash);
                UserDB.update(user);
                
                session.removeAttribute("message");
                session.removeAttribute("user-non-valid-id");
                response.sendRedirect(TO_LOGIN);
            }
            else{
                Methods.setMessageInfo(response, request, CONFPASS_ERR, false);
            }
        }
        
        
    }
}
