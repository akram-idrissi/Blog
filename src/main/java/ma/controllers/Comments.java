
package ma.controllers;

import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;
import ma.data.CommentDB;
import ma.business.Comment;

import static ma.constants.Page.*;
        

public class Comments extends HttpServlet {
    
    public static int currentRep = 1;
    
    @Override
    protected synchronized void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Comment> comments = new ArrayList<>();

        String title = request.getParameter("title");
        String date = request.getParameter("post-date");
        String seeMore = request.getParameter("see-more");
        
        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
        String query = String.format("select * from comment where post_id = %d order by id desc", post.getId());
        
        final int commentPerPage = 5;
        long totalComment = CommentDB.size("select count(*) from comment");
        int repetition = (int) Math.ceil((float) totalComment/commentPerPage );
        
        if(seeMore.compareTo("true") == 0){  
            if(currentRep < repetition){
                
                int range = currentRep * commentPerPage;
                comments = CommentDB.getAll(String.format("select * from comment where post_id"
                    + " = %d order by id desc limit %d, 5", post.getId(), range));
                currentRep ++;
            } else{
                currentRep = 1;
            }
            
            
            String json = new Gson().toJson(comments);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            
        } else{
            comments = CommentDB.getAll(String.format("select * from comment where post_id"
                    + " = %d order by id desc limit 0, 5", post.getId()));
            
            request.setAttribute("post", post);
            request.setAttribute("comments", comments);
            request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*
            This method is called by the CommentAction servlet only
        */
        
        String date = request.getParameter("date");
        String title = request.getParameter("title");
        Post post = PostDB.getPost(String.format("select * from post where posted_date = '%s' and title = '%s'", date, title));
        
        String query = String.format("select * from comment where post_id = %d order by id desc limit 0, 5", post.getId());
        ArrayList<Comment> comments = CommentDB.getAll(query);
        
        request.setAttribute("post", post);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher(TO_COMMENTS).forward(request, response);
        
    }
}
