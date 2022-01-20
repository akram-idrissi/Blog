
package ma.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.Part;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.MultipartConfig;

import ma.data.UserDB;
import ma.business.User;
import ma.constants.Methods;

import static ma.constants.InfoMSG.*;


@MultipartConfig
public class EditProfile extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // C:\\Users\\Ce pc\\Desktop\\Blog App\\blog app\\src\\main\\webapp\\images
        
        // Getting the selected image
        Part part = request.getPart("image");
        String imageName = part.getSubmittedFileName();
         
        // getting the current user
        int userID = (int) request.getSession().getAttribute("user");
        User user = (User) UserDB.getUser("select * from user where id = " + userID);

        // Seeting the path for the new image C:\java-blog - Copie\src\main\webapp\images
        String imgFolderPath = "..\\src\\main\\webapp\\images";
        String newImage = imgFolderPath + File.separator + imageName;

        // updating user image in db
        user.setImage(imageName);
        UserDB.update(user);
        
        saveImage(part.getInputStream(), newImage);
        Methods.setMessageInfo(response, request, PROFILE);
        
    }
    
    public void saveImage(InputStream is, String path) throws IOException{
        
        try {
            byte[] b  = new byte[is.available()];
            is.read(b);
            
            FileOutputStream fileOS = new FileOutputStream(path);
            fileOS.write(b);
            fileOS.flush();
            fileOS.close();
            
        } catch (IOException ex) {
            Logger.getLogger(EditProfile.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
        
    }
    
    public boolean deleteImage(String path){
            File file = new File(path);
            return file.delete();
        
    }

}
