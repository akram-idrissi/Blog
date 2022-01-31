
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;
import ma.data.VerifyEmailDB;
import ma.business.Verifyemail;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


public class VerifyEmailAddress extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String url;
        String code = request.getParameter("code").trim();
        String email = (String) request.getSession().getAttribute("user-email");
        String emailQuery = String.format("select * from User where email = '%s' ", email);
        String codeQuery = String.format("select * from Verifyemail where code = '%s' ", code);
        
        // If the code is empty
        if(code.equals("")){
            url = TO_REGISTER;
            Methods.setMessageInfo(request, NONE, "none", "error-message");
            
        } else{
            User user = UserDB.getUser(emailQuery);
            Verifyemail ve = (Verifyemail) VerifyEmailDB.getVerifyEmail(codeQuery);

            // If user and code exist
            if(ve.getCode().compareTo(code) != 0){
                url = TO_REGISTER;
                Methods.setMessageInfo(request, NONE, "none", "error-message");
                
            }else{
                url = TO_LOGIN;
                
                ve.setFlag(1); // Verified email
                user.setValide(1); // Verified user
                
                VerifyEmailDB.update(ve);
                UserDB.update(user);
                request.getSession().removeAttribute("user-email");
                Methods.setMessageInfo(request, REGISTRED, "block", "done-message");
            }

        }
        
        response.sendRedirect(url);
    }

}
