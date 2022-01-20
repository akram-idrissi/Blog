
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
        String date = request.getParameter("postDate");    
        String postQuery = String.format("select * from Post where posted_date = '%s'", date);
        
        // getting the post with the specific date
        Post post = (Post) PostDB.getPost(postQuery);
        
        if(post != null){
            session.setAttribute("post", post);
            if (session.getAttribute("user") == null) {
                url = TO_POST;
            } else if ((int) session.getAttribute("user") != post.getUser().getId()) {
                url = TO_POST;
            } else {
                url = TO_USER_POST;
                session.setAttribute("post", post);
                session.setAttribute("title", post.getTitle());
            }
        
        } else{
            url = TO_HOME_S;
        }

        response.sendRedirect(url);

    }
}
