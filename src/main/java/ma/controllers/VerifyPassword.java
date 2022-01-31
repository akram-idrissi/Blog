
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.constants.Methods;
import ma.data.VerifyPasswordDB;
import ma.business.Verifypassword;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


public class VerifyPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String url;
        Verifypassword vp;
        
        String code = (String) request.getParameter("code");
        String codeQuery = String.format("select * from Verifypassword where code = '%s'", code);
        vp = (Verifypassword) VerifyPasswordDB.getVerifyPassword(codeQuery);
        
        if (vp.getCode().compareTo(code) == 0){
            vp.setFlag(1);
            url = TO_PASSWORD_RESET_CONFIRM;
            VerifyPasswordDB.update(vp);
            VerifyPasswordDB.delete(vp);
        }
        else{
            url = TO_PASSWORD_RESET;
            Methods.setMessageInfo(request, PASS_RESET_ERR, "block", "error-message");
        }
        
        response.sendRedirect(url);
    }

}
