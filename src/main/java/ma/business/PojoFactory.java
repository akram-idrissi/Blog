
package ma.business;


public abstract class PojoFactory {
  
    public static final int POST = 1;
    public static final int USER = 2;
    public static final int COMMENT = 3;
    public static final int MESSAGE = 4;
    public static final int POSTISG = 5;
    public static final int EMAILTRAC = 6;
    public static final int COMMENTISG = 7;
    public static final int PASSWORDTRAC = 8;
    
    public static PojoFactory getPojo(int whichPojo){
        
        return switch (whichPojo) {
            case POST -> new Post();
                
            case USER -> new User();
                 
            case COMMENT -> new Comment();
                 
            case MESSAGE -> new Message();

            case POSTISG -> new PostIsg();
                
            case EMAILTRAC -> new EmailTrac();
            
            case COMMENTISG -> new CommentIsg();
            
            case PASSWORDTRAC -> new PasswordTrac();
            
            default -> null;
        };
    }
}
