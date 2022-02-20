
package ma.dao;


public abstract class DAOFactory {
    
    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    
    public abstract PostDAO getPostDAO();
    public abstract UserDAO getUserDAO();
    public abstract CommentDAO getCommentDAO();
    public abstract MessageDAO getMessageDAO();
    public abstract PostIsgDAO getPostIsgDAO();
    public abstract EmailTracDAO getEmailTracDAO();
    public abstract CommentIsgDAO getCommentIsgDAO();
    public abstract PasswordTracDAO getPasswordTracDAO();
    
    public static DAOFactory getDactory(int whichFactory){
        return switch (whichFactory) {
            case MYSQL -> new MySQLDAOFactory();
            case ORACLE -> new OracleDAOFactory();
            default -> null;
        };
    }

}
