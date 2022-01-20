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
import ma.data.PostInsightDB;
import ma.business.Postinsight;

import static ma.constants.Page.*;

public class PostActions extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        
        String url;
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        int userId = (int) session.getAttribute("user");
        String userQuery = String.format("select * from User where id = %d", userId);
        User user = UserDB.getUser(userQuery);
        
        switch (action) {
            case "add" -> {
                url = TO_HOME_S;
                addPost(request, user);
            }
            case "update" -> {
                url = TO_HOME_S;
                updatePost(request);
            }
            case "delete" -> {
                url = TO_HOME_S;
                deletePost(request, user);
            }
            default -> url = TO_HOME_S;
        }
        
        response.sendRedirect(url);
        
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
        Post post = (Post) session.getAttribute("post");
        String title = req.getParameter("title").trim();
        String content = req.getParameter("content").trim();

        post.setTitle(title);
        post.setContent(content);
        PostDB.update(post);
        session.removeAttribute("post");
    }
    
    public void deletePost(HttpServletRequest req, User user){
        HttpSession session = req.getSession();
        Post post = (Post) session.getAttribute("post");
        
        String postIsgQuery = "select * from postinsight where user_id = " +
                user.getId() + " and post_id =  " + post.getId();
        
        try{
            Postinsight postIsg = PostInsightDB.getPostInsight(postIsgQuery);
            PostInsightDB.delete(postIsg);
        } catch(NullPointerException ex){
        
        } finally{
            PostDB.delete(post);
        }
        
        
        session.removeAttribute("post");
    }
}
