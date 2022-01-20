
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
        
        System.out.println(codeQuery);
        System.out.println(emailQuery);
        
        // If the code is empty
        if(code.equals("")){
            System.out.println("break here 1");
            url = TO_REGISTER;
            Methods.setMessageInfo(null, request, NONE);
            
        } else{
            User user = UserDB.getUser(emailQuery);
            Verifyemail ve = (Verifyemail) VerifyEmailDB.getVerifyEmail(codeQuery);

            // If user and code exist
            System.out.println("l " + code);
            System.out.println("v " + ve.getCode());
            if(ve.getCode().compareTo(code) != 0){
                System.out.println("break here 2");
                url = TO_REGISTER;
                Methods.setMessageInfo(null, request, NONE);
                
                
            }else{
                url = TO_LOGIN;
                
                ve.setFlag(1); // Verified email
                user.setValide(1); // Verified user
                
                VerifyEmailDB.update(ve);
                UserDB.update(user);
                request.getSession().removeAttribute("user-email");
                Methods.setMessageInfo(null, request, NONE);
            }

        }
        
        response.sendRedirect(url);
    }

}
