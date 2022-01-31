
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;
import ma.util.PasswordUtil;
import ma.util.MailUtilGmail;
import ma.data.VerifyPasswordDB;
import ma.business.Verifypassword;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


public class Email extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String url = TO_PASSWORD_RESET;
        String code = PasswordUtil.generateToken();
        String email = request.getParameter("email");
        String subject = "Verification link to reset your password";
        String emailQuery = String.format("select * from User where email = '%s' ", email);
        
        String link = "http://localhost:8090/blog/verify-password?code=" + code;
        String body = 
                        """
                        You 're receiving this email because you requested a password reset for your account.
                        Please go to the following page and make a new password.
                        """
                        + link + "\nThanks for usinng our site.";
        
        // If the email input is empty
        if (Methods.any(new String[]{email})){
            Methods.setMessageInfo(request, EMPTY, "block", "error-message");
        } 
        
        // If the emial address doesn not exist
        else if(UserDB.getUser(emailQuery) == null){
            Methods.setMessageInfo(request, EMAIL_ERR, "block", "error-message");
        } 
        
        else{
            url = TO_DONE;
            Verifypassword vp = new Verifypassword();
            
            User user = UserDB.getUser(emailQuery);
            request.getSession().setAttribute("user-non-valid-id", user.getId());
            
            vp.setFlag(0); 
            vp.setCode(code);
            vp.setEmail(email);
            VerifyPasswordDB.insert(vp);
            
            MailUtilGmail.sendMail(email, subject, body, false);
            Methods.setMessageInfo(request, EMAIL_PASS_RESET, "block", "done-message");
            
        }
        
        response.sendRedirect(url);
    }
}
