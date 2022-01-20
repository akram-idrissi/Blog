
package ma.controllers;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;

import static ma.constants.Page.*;

public class HomePosts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        request.getSession().removeAttribute("post");
        ArrayList<Post> posts = PostDB.getAll();
        request.setAttribute("posts", posts);
        request.getRequestDispatcher(TO_HOME).forward(request, response);
    }
}
