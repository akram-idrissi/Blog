
package ma.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.data.PostDB;
import ma.business.User;
import ma.business.Post;
import ma.data.CommentDB;
import ma.business.Comment;

import static ma.constants.Page.*;


public class Comments extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String date = request.getParameter("post-date");
        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
        
        Object obj = request.getSession().getAttribute("user");
        if(obj != null){
            User user = UserDB.getUser("select * from user where id = " + (int) obj);
            String query = String.format("select * from comment where user_id = %d and post_id = %d", user.getId(), post.getId());
            
            ArrayList<Comment> comments = CommentDB.getAll(query);
            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            response.sendRedirect(TO_COMMENTS);
            //request.getRequestDispatcher(TO_COMMENTS).forward(request, response);

        } else {
            ArrayList<Comment> comments = CommentDB.getAll("select * from comment where post_id = " + post.getId());
            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
        }
    }
   
}
