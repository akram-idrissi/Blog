package ma.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import javax.servlet.http.Part;
import java.util.logging.Logger;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.dao.UserDAO;
import ma.business.User;
import ma.dao.DAOFactory;
import ma.constants.Methods;
import ma.controllers.EditProfile;
import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;

public class ProfileCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Part part = request.getPart("image");
        if (part != null) {
            String newImahe = part.getSubmittedFileName();

            String userID = (String) request.getSession().getAttribute("user");

            DAOFactory mysqlFactory = DAOFactory.getDactory(DAOFactory.MYSQL);
            UserDAO userDAO = mysqlFactory.getUserDAO();
            User user = userDAO.findUser("select * from user where id = " + userID);
            

            String imagePath = "C:\\Users\\Ce pc\\Desktop\\Blog-web-application\\blog\\src\\main\\webapp\\images";
            String oldImage = imagePath + File.separator + user.getImage();
            String newImage = imagePath + File.separator + newImahe;

            user.setImage(newImahe);
            userDAO.updateUser(user);

            boolean saved = saveImage(part.getInputStream(), newImage);

            if (saved) {
                Methods.setMessageInfo(request, PROFILE, "block", "done-message");
            } else {
                Methods.setMessageInfo(request, PROFILE_ERR, "block", "error-message");
            }
            
            deleteImage(oldImage);
            
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException ex) {
                Logger.getLogger(EditProfile.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return TO_PROFILE;
    }

    public boolean saveImage(InputStream is, String path) throws IOException {

        try {
            byte[] b = new byte[is.available()];
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

    public boolean deleteImage(String path) {
        File file = new File(path);
        return file.delete();
    }

}
