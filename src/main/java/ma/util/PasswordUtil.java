package ma.util;

import java.util.Random;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


public class PasswordUtil {
    
    /*  This code uses SHA-256. If this algorithm isn't available to you,
        you can try a weaker level of encryption such as SHA-128.
    */    
    public static String hashPassword(String password){     
        try {
            // getInstance() selects the name of the algorithm to be used and 
            // returns the MessageDigest object implmenting the specified algorithm.
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // we need to reset md object each time we use it.
            md.reset();
            // passing the data (password) in the form of byte array.
            md.update(password.getBytes());
            byte[] mdArray = md.digest();
            StringBuilder sb = new StringBuilder(mdArray.length * 2);
            for (byte b : mdArray) {
                int v = b & 0xff;
                if (v < 16) {
                    sb.append('0');
                }
                // append a string representation of the int in base 16. 
                sb.append(Integer.toHexString(v));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PasswordUtil.class.getName()).log(Level.SEVERE,
                    null, ex);    
            return null;
        }
    }
    
    public static String generateToken(){
        String uuid = UUID.randomUUID().toString();
        return hashPassword(uuid.replace("-", ""));
    }
    
    public static String getSalt() {
        Random r = new SecureRandom();
        byte[] saltBytes = new byte[32];
        r.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }
    
    public static String hashAndSaltPassword(String password)
            throws NoSuchAlgorithmException {
        String salt = getSalt();
        return hashPassword(password + salt);
    }
    
    public static boolean checkPasswordStrength(String password){
        return password.length() >= 8;
    }
    
    public static void main(String[] args){
        System.out.println(generateToken());
    }
}