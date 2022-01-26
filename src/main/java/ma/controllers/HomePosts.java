
package ma.controllers;

import java.util.ArrayList;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;

import static ma.constants.Page.*;


public class HomePosts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Post> posts = PostDB.getAll("select * from post");
        String page = request.getParameter("page");
        request.getSession().removeAttribute("post");
        
        int range;
        final int postPerPage = 5;
        int totalPosts = posts.size();
        int totalPages = (int) Math.ceil((float) totalPosts / postPerPage);
        
        int[] pages = new int[totalPages];
        for(int i = 0; i < totalPages; i++){
            pages[i] = i + 1;
        }
        
        if(page != null){
            int pageNumber = Integer.parseInt(page);
            range = postPerPage * (pageNumber - 1);
            String query = String.format("select * from post order by id desc limit %d, 5", range);
            posts = PostDB.getAll(query);
            
            request.setAttribute("pages", pages);
            request.setAttribute("posts", posts);
            
            
        } else{
            posts.clear();
            posts = PostDB.getAll("select * from post order by id desc limit 0, 5");

            request.setAttribute("pages", pages);
            request.setAttribute("posts", posts);
        }
        
        request.getRequestDispatcher(TO_HOME).forward(request, response);
    }
}

