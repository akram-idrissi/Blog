
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
        String url = TO_PASSWORD_RESET_CONFIRM;
        HttpSession session = request.getSession();

        int userId = (int) session.getAttribute("user-non-valid-id");
        user = UserDB.getUser("select * from User where id = " + userId);
        
        String pass = request.getParameter("new-pass");
        String confirm = request.getParameter("new-confirm");
        
        if (Methods.any(new String[]{pass, confirm})){
            Methods.setMessageInfo(request, EMPTY, "block", "error-message");
        }
        else if (!Methods.isPassword(pass)){
            Methods.setMessageInfo(request, PASS_ERR, "block", "error-message");
        }
        else{
            if (pass.equals(confirm)){
                url = TO_LOGIN;
                Methods.setMessageInfo(request, PASS_RESET_SUCCESS, "block", "done-message");
                
                String salt = PasswordUtil.getSalt();
                String hash = PasswordUtil.hashPassword(pass + salt);
                
                user.setHashedpass(hash);
                user.setSalt(salt);
                UserDB.update(user);
                
                session.removeAttribute("message");
                session.removeAttribute("user-non-valid-id");
                
            }
            else{
                Methods.setMessageInfo(request, CONFPASS_ERR, "block", "error-message");
            }
        }
        
        response.sendRedirect(url);
    }
}
