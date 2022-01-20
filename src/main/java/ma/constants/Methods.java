
package ma.constants;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.util.PasswordUtil;
import ma.controllers.Register;


public final class Methods {
    
    final static String USN_REGEX = "[a-zA-Z0-9@\\\\.\\\\+\\\\-\\\\_]{1,30}";
    final static String PASS_REGEX = "(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[ .,;:!?+\\-_*/&#|@])[a-zA-Z0-9 .,;:!?+\\-_*/&#|@]{8,}";
    
    public static boolean any(String[] items){
        for(String item:items){
            if(item.equals("")){
                return true;
            }
        }
        return false;
    }
    
    public static boolean usernameRules(String name){
        Pattern pattern = Pattern.compile(USN_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isPassword(String password){
        Pattern pattern = Pattern.compile(PASS_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    
    public final static void setMessageInfo(HttpServletResponse res, HttpServletRequest req, String message){
        req.setAttribute("message", message);
        
        try {
            if(res != null)
                res.getWriter().write("message");
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String verifyHash(String pass, String salt, String hashed){
        String newHash = PasswordUtil.hashPassword(pass + salt);

        if (hashed != null){
            if(!hashed.equals(newHash)){
                return null;
            }
            else{
                return newHash;
            }
        }
        return newHash;   
    }
    
}
