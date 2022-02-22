
package ma.controller;

import java.util.Map;
import java.util.HashMap;

public abstract class ControllerFactory {
    
    public static final Map<String, Command> routes = new HashMap();
    
    static{
        routes.put("/user", new UserCommand());
        routes.put("/post", new PostCommand());
        routes.put("/login", new LoginCommand());
        routes.put("/reset", new ResetCommand());
        routes.put("/verify", new VerifyCommand());
        routes.put("/logout", new LogoutCommand());
        routes.put("/comment", new CommentCommand());
        routes.put("/profile", new ProfileCommand());
        routes.put("/register", new RegisterCommand());
        routes.put("/post-insight", new PostISGCommand());
        routes.put("/comment-insight", new CommentISGCommand());
    }

    
    public static Command getController(String wichCommand){
        
        return routes.get(wichCommand) != null
                ? routes.get(wichCommand)
                : new ErrorCommand();
        
    }
}
