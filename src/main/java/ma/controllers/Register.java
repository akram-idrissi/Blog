
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;
import ma.util.PasswordUtil;
import ma.util.MailUtilGmail;
import ma.data.VerifyEmailDB;
import ma.business.EmailTrac;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


public class Register extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        
        String[] fields;
        String subject = "Email verification"; 
        HttpSession session = request.getSession();
        String code = PasswordUtil.generateToken();

        String email = request.getParameter("email").trim();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();
        
        String salt = PasswordUtil.getSalt();
        String hash = PasswordUtil.hashPassword(password + salt);
        
        String emailQuery = String.format("select * from User where email = '%s' ", email);
        String usernameQuery = String.format("select * from User where username = '%s' ", username);
        
        String body = 
        """
        Click the link below to verify your email.
        Verification link: http://localhost:8090/blog/verify-email?code="""+  code +
        "\nThanks for using our site.";
        

        fields = new String[]{email,username, password, confirmPassword};

        // If any input is empty 
        if(Methods.any(fields)){ 
            Methods.setMessageInfo(request, EMPTY, "block", "error-message");
        }
        
        // If the username exists 
        else if(UserDB.getUser(usernameQuery) != null){
            Methods.setMessageInfo(request, USN_EXISTS, "block", "error-message");
        }
        
        // If the username do not respect name conditions
        else if(!Methods.usernameRules(username)){
            Methods.setMessageInfo(request, USN_ERR, "block", "error-message");
        }
        
        // If the email exists 
        else if(UserDB.getUser(emailQuery) != null){
            Methods.setMessageInfo(request, EMAIL_EXISTS, "block", "error-message");
        }
        
        // If the password is not strong enough 
        else if (!Methods.isPassword(password)){
            Methods.setMessageInfo(request, PASS_ERR, "block", "error-message");
        }
        
        // If the password contains personal info (username) 
        else if (password.contains(username)){
            Methods.setMessageInfo(request, PASS_ERR, "block", "error-message");
        }
        
        // If the password and the confirm are not the same
        else if (!password.equals(confirmPassword)){
            Methods.setMessageInfo(request, CONFPASS_ERR, "block", "error-message");
        }
        
        // If the credentials are valid
        else{
            Methods.setMessageInfo(request, EMAIL_VERIF, "block", "done-message");
            MailUtilGmail.sendMail(email, subject, body, false);

            User user = new User();
            EmailTrac ve = new EmailTrac();
            
            ve.setFlag(0); // The user has not verify its email yet
            ve.setCode(code);
            ve.setEmail(email);
            user.setValide(0); // Not a valid user
            user.setSalt(salt);
            user.setEmail(email);
            user.setHashedpass(hash);
            user.setUsername(username);
            user.setImage("person.png");  //Default profile img
            
            UserDB.insert(user);
            VerifyEmailDB.insert(ve);
            
            session.setAttribute("user-email", user.getEmail());
            

        }
        
        response.sendRedirect(TO_REGISTER);
    }
    
}
