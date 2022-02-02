
package ma.controllers;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;

import static ma.constants.Page.*;


public class UserPosts extends HttpServlet {
    
    final int postPerPage = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String page = request.getParameter("page");
        String userId = request.getParameter("user-id");
        
        int range;
        ArrayList<Post> posts;
        int totalPosts = (int) PostDB.size("select count(*) from post where user_id = " + userId);
        int totalPages = (int) Math.ceil((float) totalPosts / postPerPage);

        int[] pages = new int[totalPages];
        for(int i = 0; i < totalPages; i++){
            pages[i] = i + 1;
        }

        if(page != null){
            int pageNumber = Integer.parseInt(page);
            range = postPerPage * (pageNumber - 1);
            String query = String.format("select * from post where user_id = %s order by id desc limit %d, 5", userId, range);
            System.out.println(query);
            posts = PostDB.getAll(query);

            request.setAttribute("pages", pages);
            request.setAttribute("posts", posts);
            
        } else{
            posts = PostDB.getAll(String.format("select * from post where user_id = %s order by id desc limit 0, 5", userId));
            request.setAttribute("pages", pages);
            request.setAttribute("posts", posts);
        }
        
        request.setAttribute("page", page);
        request.setAttribute("size", totalPosts);
        request.getRequestDispatcher(TO_USER_POST).forward(request, response);
        
    }

}
