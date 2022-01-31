
package ma.constants;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import ma.util.PasswordUtil;


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
    
    public static void setMessageInfo(HttpServletRequest req, String message, String style, String cssClass){
        req.getSession().setAttribute("cssClass", cssClass);
        req.getSession().setAttribute("message", message);
        req.getSession().setAttribute("style", style);
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
