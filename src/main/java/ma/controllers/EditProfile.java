
package ma.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;


@MultipartConfig
public class EditProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Getting the selected image
        Part part = request.getPart("image");
        String imageName = part.getSubmittedFileName();
        
        // getting the current user
        int userID = (int) request.getSession().getAttribute("user");
        User user = (User) UserDB.getUser("select * from user where id = " + userID);

        String imagePath = "C:\\Users\\Ce pc\\Desktop\\Blog-web-application\\blog\\src\\main\\webapp\\images";
        String newImage = imagePath + File.separator + imageName;

        // updating user image in db
        user.setImage(imageName);
        UserDB.update(user);
        
        boolean saved = saveImage(part.getInputStream(), newImage);
        
        if(saved)
            Methods.setMessageInfo(request, PROFILE, "block", "done-message");
        else
            Methods.setMessageInfo(request, PROFILE_ERR, "block", "error-message");
        
        try {
            TimeUnit.SECONDS.sleep(2); 
        } catch (InterruptedException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        response.sendRedirect(TO_PROFILE_S);
    }
    
    public boolean saveImage(InputStream is, String path) throws IOException{
        
        try {
            byte[] b  = new byte[is.available()];
            is.read(b);
            
            FileOutputStream fileOS = new FileOutputStream(path);
            fileOS.write(b);
            fileOS.flush();
            fileOS.close();
            
            return true;
            
        } catch (IOException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(
                    Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
    public boolean deleteImage(String path){
            File file = new File(path);
            return file.delete();
        
    }

}
