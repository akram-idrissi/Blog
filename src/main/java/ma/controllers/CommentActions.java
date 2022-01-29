
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
        
        String date = request.getParameter("post-date");
        String title = request.getParameter("title");
        String action = request.getParameter("action");
        
        User user = UserDB.getUser("select * from User where id = " + (int) session.getAttribute("user"));
        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
        
        if(user != null){
            switch (action) {
                case "add" -> {
                    addComment(request, user, post);
                }
                case "update" -> {
                    updateComment(request);
                }
                case "delete" -> {
                    deleteComment(request, post);
                }
                default -> {

                }
            }
            request.getRequestDispatcher("comments").forward(request, response);
            
        } else{
            response.sendRedirect(TO_HOME_S);
        }
       
    }

    private void addComment(HttpServletRequest request, User user, Post post) {
        String content = request.getParameter("content").trim();

        Comment comment = new Comment();
        comment.setUser(user);
        comment.setPost(post);
        comment.setLikeCount(0);
        comment.setDislikeCount(0);
        comment.setContent(content);
        
        CommentDB.insert(comment);
        post.setCommentCount(post.getCommentCount() + 1);
        PostDB.update(post);
    }

    private void updateComment(HttpServletRequest request) {
        
        
    
    }

    private void deleteComment(HttpServletRequest request, Post post) {
        String date = request.getParameter("comment-date");
        String query = String.format("delete from comment where comment_date = '%s' ", date);
        CommentDB.delete(query);
        post.setCommentCount(post.getCommentCount() - 1);
        PostDB.update(post);
    }

}
