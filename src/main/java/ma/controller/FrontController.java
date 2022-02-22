
package ma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FrontController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, java.io.IOException {   
        
        
        Command command = ControllerFactory.getController(request.getRequestURI());
        String view = command.executeCommand(request, response);
        
        if (view.equals(request.getPathInfo().substring(1))) {
            request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
        }
        else {
            response.sendRedirect(view); // We'd like to fire redirect in case of a view change as result of the action (PRG pattern).
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
        
}
