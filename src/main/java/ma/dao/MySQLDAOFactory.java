
package ma.dao;

import java.sql.Connection;
import ma.util.MySQLConnectionPool;


public class MySQLDAOFactory extends DAOFactory {
    
    public static Connection createConnection() {
        MySQLConnectionPool msConnectionPool =  MySQLConnectionPool.getInstance();
        return msConnectionPool.getConnection();
    }
    
    @Override
    public PostDAO getPostDAO() {
        return new MySQLPostDAO();
    }

    @Override
    public UserDAO getUserDAO() {
        return new MySQLUserDAO();
    }

    @Override
    public CommentDAO getCommentDAO() {
        return new MySQLCommentDAO(); 
    }

    @Override
    public MessageDAO getMessageDAO() {
        return new MySQLMessageDAO();
    }

    @Override
    public PostIsgDAO getPostIsgDAO() {
        return new MySQLPostIsgDAO();
    }

    @Override
    public EmailTracDAO getEmailTracDAO() {
        return new MySQLEmailTracDAO();
    }

    @Override
    public CommentIsgDAO getCommentIsgDAO() {
        return new MySQLCommentIsgDAO();
    }

    @Override
    public PasswordTracDAO getPasswordTracDAO() {
        return new MySQLPasswordTracDAO();
    }
    
}
