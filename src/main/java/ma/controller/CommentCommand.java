
package ma.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.dao.PostDAO;
import ma.dao.UserDAO;
import ma.business.Post;
import ma.business.User;
import ma.dao.CommentDAO;
import ma.dao.DAOFactory;
import ma.business.Comment;

import static ma.constants.Page.*;

public class CommentCommand implements Command{

    @Override
    public String executeCommand(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String userID = (String) request.getSession().getAttribute("user");
        String postID = (String) request.getSession().getAttribute("post-id");
        
        DAOFactory mysqlFactory = DAOFactory.getDactory(DAOFactory.MYSQL);
        UserDAO userDAO = mysqlFactory.getUserDAO();
        PostDAO postDAO = mysqlFactory.getPostDAO();
        CommentDAO commentDAO = mysqlFactory.getCommentDAO();
        
        switch (action) {
            case "add" -> {
                
                String content = request.getParameter("content").trim();
                
                Comment comment = new Comment();
                User user = userDAO.findUser("select * from user where id = " + userID);
                Post post = postDAO.findPost("select * from post where id = " + postID);
                
                comment.setUser(user);
                comment.setPost(post);
                comment.setLikeCount(0);
                comment.setDislikeCount(0);
                comment.setContent(content);
                post.setCommentCount(post.getCommentCount() + 1);
                
                commentDAO.insertComment(comment);
                postDAO.updatePost(post);
            }
            
            case "delete" -> {
                String commentID = request.getParameter("comment-id");
                
                Post post = postDAO.findPost("select * from post where id = " + postID);
                Comment comment = commentDAO.findComment("select * from comment where id = " + commentID);
                
                commentDAO.deleteComment(comment);
                post.setCommentCount(post.getCommentCount() - 1);
                postDAO.updatePost(post);
            }
            
            case "all" -> {
                
                String query = "select * from comment where post_id = " + postID;
                ArrayList<Comment> comments = (ArrayList<Comment>) commentDAO.selectCommentsTO(query);
                request.setAttribute("comments", comments);
            }
            
        }
        
        return TO_COMMENTS;
    }
}
