
package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.data.PostDB;
import ma.business.Post;
import ma.business.User;
import ma.data.CommentDB;
import ma.business.Comment;

import static ma.constants.Page.*;


public class CommentActions extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String id = request.getParameter("post-id");
        String action = request.getParameter("action");
        
        Post post = PostDB.getPost("select * from post where id = " + id);
        User user = UserDB.getUser("select * from User where id = " + (int) session.getAttribute("user"));
        
        if(user != null){
            switch (action) {
                case "add" -> {
                    addComment(request, user, post);
                }
                
                case "delete" -> {
                    deleteComment(request, post);
                }
            }
            
            session.setAttribute("post-id", post.getId());
            response.sendRedirect("comment-inter");
            
        } else{
            response.sendRedirect(TO_HOME_S);
        }
    }

    private void addComment(HttpServletRequest request, User user, Post post) {
        Comment comment = new Comment();
        String content = request.getParameter("content").trim();
        
        comment.setUser(user);
        comment.setPost(post);
        comment.setLikeCount(0);
        comment.setDislikeCount(0);
        comment.setContent(content);

        CommentDB.insert(comment);
        post.setCommentCount(post.getCommentCount() + 1);
        PostDB.update(post);
    }


    private void deleteComment(HttpServletRequest request, Post post) {
        String id = request.getParameter("comment-id");
        String query = "delete from comment where id = " + id;
        
        CommentDB.delete(query);
        post.setCommentCount(post.getCommentCount() - 1);
        PostDB.update(post);
    }
}
