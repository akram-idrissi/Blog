
package ma.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;
import ma.data.CommentDB;
import ma.business.Comment;

import static ma.constants.Page.*;
        

public class Comments extends HttpServlet {
    
    
    @Override
    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String title = request.getParameter("title");
        String date = request.getParameter("post-date");
        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
        
        ArrayList<Comment> comments = CommentDB.getAll(String.format("select * from comment where post_id = %d order by id desc", post.getId()));
        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doGet(request, response);
        
        
//        String date = request.getParameter("date");
//        String title = request.getParameter("title");
//        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
//        
//        String query = String.format("select * from comment where post_id = %d order by id desc limit 0, 5", post.getId());
//        ArrayList<Comment> commentsList = CommentDB.getAll(query);
//        
//        request.setAttribute("post", post);
//        request.setAttribute("comments", commentsList);
//        request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
        
    }
}