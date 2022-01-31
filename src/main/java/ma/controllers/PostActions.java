package ma.controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.data.PostDB;
import ma.business.Post;
import ma.business.User;
import ma.data.CommentDB;

import static ma.constants.Page.*;


public class PostActions extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        int userId = (int) session.getAttribute("user");
        String userQuery = String.format("select * from User where id = %d", userId);
        User user = UserDB.getUser(userQuery);
        
        switch (action) {
            case "add" -> {
                addPost(request, user);
            }
            case "update" -> {
                updatePost(request);
            }
            case "delete" -> {
                deletePost(request, user);
            }
        }
        
        response.sendRedirect(TO_HOME_S);
    }
    
    public void addPost(HttpServletRequest req, User user){
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content").trim();
        
        Post post = new Post();
        post.setUser(user);
        post.setTitle(title);
        post.setLikeCount(0);
        post.setDislikeCount(0);
        post.setCommentCount(0);
        post.setContent(content);
        PostDB.insert(post);
    }
    
    public void updatePost(HttpServletRequest req){
        HttpSession session = req.getSession();
        String title = req.getParameter("title").trim();
        Post post = (Post) session.getAttribute("post");
        String content = req.getParameter("content").trim();

        post.setTitle(title);
        post.setContent(content);
        PostDB.update(post);
        session.removeAttribute("post");
    }
    
    public void deletePost(HttpServletRequest req, User user){
        HttpSession session = req.getSession();
        Post post = (Post) session.getAttribute("post");
        
        try{
            CommentDB.delete("delete from comment where post_id = " + post.getId());
            PostDB.delete(post);
        } catch(Exception ex){
            PostDB.delete(post);
        }

        session.removeAttribute("post");
    }
}
