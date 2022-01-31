
package ma.controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.UserDB;
import ma.business.User;
import ma.data.CommentDB;
import ma.business.Comment;
import ma.data.CommentInsightDB;
import ma.business.Commentinsight;


public class CommentInsights extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String id = request.getParameter("comment-id");
        String action = request.getParameter("action");
        
        // getting the dis/liked comment 
        Comment comment = CommentDB.getComment("select * from comment where id = " + id);

        // if the user who clicked the dis/like is logged in
        if(session.getAttribute("user") != null){

            // getting the user object of the current user
            int userID = (int) session.getAttribute("user");
            User user = UserDB.getUser("select * from User where id = " + userID);
            
            // query for commentInsight of the comment that has been dis/liked
            String insightQuery = String.format("select * from commentinsight where user_id = %d and comment_id = %d order by id desc", userID, comment.getId());
            Commentinsight commentI = CommentInsightDB.getCommentIsg(insightQuery);
            
            int likeCount = comment.getLikeCount();
            int dislikeCount = comment.getDislikeCount();
            
            switch (action) {
                case "like" -> {
                    
                    // if the user has already dis/liked the comment
                    if(commentI != null){

                        // if the user disliked the comment we increment likeCount and decrement dislikeCount
                        if(commentI.getIsDislike() == 1) {
                            comment.setLikeCount(likeCount + 1);
                            comment.setDislikeCount(dislikeCount - 1);
                            
                            commentI.setIsLike(1);
                            commentI.setIsDislike(0);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.insert(commentI);
                        } else if(commentI.getIsLike() == 1){
                            commentI.setIsLike(0);
                            comment.setLikeCount(likeCount - 1);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.update(commentI);
                        } else{
                            commentI.setIsLike(1);
                            comment.setLikeCount(likeCount + 1);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.update(commentI);
                        }
                        
                    } else {

                        commentI = new Commentinsight();
                        commentI.setIsLike(1);
                        commentI.setUser(user);
                        commentI.setIsDislike(0);
                        commentI.setIsCommented(0);
                        commentI.setComment(comment);
                        commentI.setPost(comment.getPost());
                        comment.setLikeCount(likeCount + 1);
                        
                        CommentDB.update(comment);
                        CommentInsightDB.insert(commentI); 
                    }
                    
                }
                case "dislike" -> {
                    
                    // if the user has already dis/liked the comment
                    if(commentI != null){

                        // if the user liked the comment we increment dislikeCount and decrement likeCount
                        if(commentI.getIsLike() == 1) {
                            comment.setLikeCount(likeCount - 1);
                            comment.setDislikeCount(dislikeCount + 1);
                            
                            commentI.setIsLike(0);
                            commentI.setIsDislike(1);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.insert(commentI);
                        } else if(commentI.getIsDislike() == 1){
                            commentI.setIsDislike(0);
                            comment.setDislikeCount(dislikeCount - 1);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.update(commentI);
                        } else{
                            commentI.setIsDislike(1);
                            comment.setDislikeCount(dislikeCount + 1);
                            
                            CommentDB.update(comment);
                            CommentInsightDB.update(commentI);
                        }
                        
                    } else {
                        commentI = new Commentinsight();
                        commentI.setIsLike(0);
                        commentI.setUser(user);
                        commentI.setIsCommented(0);
                        commentI.setComment(comment);
                        commentI.setPost(comment.getPost());
                        comment.setDislikeCount(dislikeCount + 1);
                        
                        CommentDB.update(comment);
                        CommentInsightDB.insert(commentI);
                    }
                    
                }
                default ->  {
                }
            }
            
        } 
        response.getWriter().write(comment.getLikeCount() + "," + comment.getDislikeCount());
        
    }
}
