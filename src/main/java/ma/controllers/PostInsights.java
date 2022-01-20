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
        
        Postinsight postIsg;
        HttpSession session = request.getSession();
        
        String date = request.getParameter("date");
        String action = request.getParameter("action");
        
        String dateQuery = String.format("select * from Post where posted_date = '%s' ", date);

        Post post = PostDB.getPost(dateQuery);
        System.out.println(post.getPostedDate());
        if(action == null){
            response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
        }
        else{
        
            if (session.getAttribute("user") == null){
                response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
            }
            else{
                int userId = (int) session.getAttribute("user");
                String userQuery = String.format("select * from User where id = %d", userId);
                User user = UserDB.getUser(userQuery);
                
                String piQuery = String.format("select * from Postinsight where post_id"
                    + " = %d and user_id = %d ", post.getId(), user.getId());
                
                postIsg =  PostInsightDB.getPostInsight(piQuery);
                
                switch (action) {
                    case "like" -> {
                        if(postIsg == null){
                            postIsg = new Postinsight();
                            postIsg.setUser(user);
                            postIsg.setPost(post);
                            postIsg.setIsLike(1);
                            postIsg.setIsDislike(0);
                            PostInsightDB.insert(postIsg);

                            post.setLikeCount(post.getLikeCount() + 1);
                            PostDB.update(post);
                            response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                        }
                        else{
                            if(postIsg.getIsLike() == 0){
                            
                                postIsg.setIsLike(1);
                                postIsg.setIsDislike(0);
                                PostInsightDB.update(postIsg);

                                post.setLikeCount(post.getLikeCount() + 1);
                                post.setDislikeCount(post.getDislikeCount() - 1);
                                PostDB.update(post);
                                response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                            
                            } else{
                                response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                            }
                        }
                    }
                    case "dislike" -> {
                        if(postIsg == null){
                            postIsg = new Postinsight();
                            postIsg.setUser(user);
                            postIsg.setPost(post);
                            postIsg.setIsLike(0);
                            postIsg.setIsDislike(1);
                            PostInsightDB.insert(postIsg);

                            post.setDislikeCount(post.getDislikeCount() + 1);
                            PostDB.update(post);
                            response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                        }
                        else{
                            if(postIsg.getIsDislike() == 0){
                            
                                postIsg.setIsDislike(1);
                                postIsg.setIsLike(0);
                                PostInsightDB.update(postIsg);

                                post.setDislikeCount(post.getDislikeCount() + 1);
                                post.setLikeCount(post.getLikeCount() - 1);
                                PostDB.update(post);
                                response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                            
                            } else{
                                response.getWriter().write(post.getLikeCount() + "," + post.getDislikeCount());
                            }
                        }
                    }
                    default ->  {
                    }
                }
            }
        }
    }
}
