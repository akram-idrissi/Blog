
package ma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.dao.PostDAO;
import ma.dao.UserDAO;
import ma.business.Post;
import ma.business.User;
import ma.dao.DAOFactory;

import static ma.constants.Page.*;

public class PostCommand implements Command{

    @Override
    public String executeCommand(HttpServletRequest request, 
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String userID = (String) request.getSession().getAttribute("user");
        
        DAOFactory mysqlFactory = DAOFactory.getDactory(DAOFactory.MYSQL);
        UserDAO userDAO = mysqlFactory.getUserDAO();
        PostDAO postDAO = mysqlFactory.getPostDAO();

        switch (action) {
            case "add" -> {
                
                String title = request.getParameter("title").trim();
                String content = request.getParameter("content").trim();
                User user = userDAO.findUser("select * from user where id = " + userID);
                
                Post post = new Post();
                post.setUser(user);
                post.setTitle(title);
                post.setLikeCount(0);
                post.setDislikeCount(0);
                post.setCommentCount(0);
                post.setContent(content);
                
                postDAO.insertPost(post);
            }
            
            case "update" -> {
                
                String postID = request.getParameter("post-id");
                String title = request.getParameter("title").trim();
                String content = request.getParameter("content").trim();
                
                Post post = postDAO.findPost("select * from post where id = " + postID);
                post.setTitle(title);
                post.setContent(content);
                postDAO.updatePost(post);
            }
            
            case "delete" -> {
                
                String postID = request.getParameter("post-id");
                Post post = postDAO.findPost("select * from post where id = " + postID);
                postDAO.deletePost(post);
            }
        }
        
        return TO_HOME;
    }
}
