
package ma.controller;

import java.util.Objects;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.dao.UserDAO;
import ma.data.UserDB;
import ma.business.User;
import ma.dao.DAOFactory;
import ma.constants.Methods;

import static ma.constants.Page.*;
import static ma.constants.InfoMSG.*;

public class LoginCommand implements Command {

    @Override
    public String executeCommand(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String url = TO_LOGIN;
        HttpSession session = request.getSession();
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String[] fields = new String[]{username, password};
        String usnQuery = String.format("select * from User where username = '%s' ", username);
        
        DAOFactory mysqlFactory = DAOFactory.getDactory(DAOFactory.MYSQL);
        UserDAO userDAO = mysqlFactory.getUserDAO();

        // If any input is empty
        if (Methods.any(fields)) {
            Methods.setMessageInfo(request, EMPTY, "block", "error-message");
        } // If the email do not exist
        else if (Objects.isNull(UserDB.getUser(usnQuery))) {
            Methods.setMessageInfo(request, USN_ERR, "block", "error-message");
        } else {
            User user = userDAO.findUser(usnQuery);

            if (Objects.nonNull(user)) {

                String saltDB = user.getSalt();
                String hashDB = user.getHashedpass();

                // If the pass != hashDB show error msg else send user to home
                if (Objects.isNull(Methods.verifyHash(password, saltDB, hashDB))) {
                    Methods.setMessageInfo(request, PASS_ERR, "block", "error-message");

                } else {
                    url = TO_HOME_S;
                    Methods.setMessageInfo(request, NONE, "none", "done-message");
                    session.setAttribute("user", user.getId());
                }
            }
        }

        return url;
    }
    
}
