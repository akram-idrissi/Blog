
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


public class CommentInter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = (int) request.getSession().getAttribute("post-id");
        Post post = PostDB.getPost("select * from post where id = " + id);
        ArrayList<Comment> comments = CommentDB.getAll(String.format("select * from comment where post_id = %d order by id desc", post.getId()));
        
        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
    }
}
