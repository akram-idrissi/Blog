
package ma.controllers;

import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ma.data.PostDB;
import ma.business.Post;

import static ma.constants.Page.*;


public class PostDetail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException { 
        
        String url;
        HttpSession session = request.getSession();
        String id = request.getParameter("post-id");    
        
        // getting the post with the specific date
        Post post = (Post) PostDB.getPost("select * from Post where id = " + id);
        
        if(post != null){
            session.setAttribute("post", post);
            url = TO_POST_DETAIL;
        
        } else{
            url = TO_HOME_S;
        }

        response.sendRedirect(url);

    }
}
