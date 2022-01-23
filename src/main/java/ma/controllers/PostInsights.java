package ma.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.data.UserDB;
import ma.business.User;
import ma.business.Post;
import ma.data.PostInsightDB;
import ma.business.Postinsight;


public class PostInsights extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        String date = request.getParameter("date");
        String title = request.getParameter("title");
        String action = request.getParameter("action");
        
        // getting the dis/liked post 
        String postQuery = String.format("select * from Post where posted_date = '%s' and title = '%s' ", date, title);
        Post post = PostDB.getPost(postQuery);

        // if the user who clicked the dis/like is logged in
        if(session.getAttribute("user") != null){

            // getting the user object of the current user
            int userID = (int) session.getAttribute("user");
            String userQuery = String.format("select * from User where id = %d", userID);
            User user = UserDB.getUser(userQuery);
            
            // query for postInsight of the post that has been dis/liked
            String insightQuery = String.format("select * from postinsight where user_id = %d and post_id = %d order by id desc", userID, post.getId());
            Postinsight postI = PostInsightDB.getPostInsight(insightQuery);
            
            int likeCount = post.getLikeCount();
            int dislikeCount = post.getDislikeCount();
            
            switch (action) {
                case "like" -> {
                    
                    // if the user has already dis/liked the post
                    if(postI != null){

                        // if the user disliked the post we increment likeCount and decrement dislikeCount
                        if(postI.getIsDislike() == 1) {
                            post.setLikeCount(likeCount + 1);
                            post.setDislikeCount(dislikeCount - 1);
                            
                            postI.setIsLike(1);
                            postI.setIsDislike(0);
                            
                            PostDB.update(post);
                            PostInsightDB.insert(postI);
                        } 
                        
                    } else {

                        postI = new Postinsight();
                        postI.setUser(user);
                        postI.setPost(post);
                        postI.setIsLike(1);
                        postI.setIsDislike(0);
                        
                        post.setLikeCount(likeCount + 1);
                        
                        PostDB.update(post);
                        PostInsightDB.insert(postI); 
                    }
                    
                }
                case "dislike" -> {
                    
                    // if the user has already dis/liked the post
                    if(postI != null){

                        // if the user liked the post we increment dislikeCount and decrement likeCount
                        if(postI.getIsLike() == 1) {
                            post.setLikeCount(likeCount - 1);
                            post.setDislikeCount(dislikeCount + 1);
                            
                            postI.setIsLike(0);
                            postI.setIsDislike(1);
                            
                            PostDB.update(post);
                            PostInsightDB.insert(postI);
                        } 
                        
                    } else {
                        postI = new Postinsight();
                        postI.setUser(user);
                        postI.setPost(post);
                        postI.setIsLike(0);
                        postI.setIsDislike(1);
                        
                        post.setDislikeCount(dislikeCount + 1);
                        
                        PostDB.update(post);
                        PostInsightDB.insert(postI);
                    }
                    
                }
                default ->  {
                }
            }
            
        } 

        response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
    }
}
