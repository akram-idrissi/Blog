
package ma.dao;

import java.sql.Connection;

public class OracleDAOFactory extends DAOFactory{
    
    public static Connection createConnection() {
        // Use DriverManger to create a connection
        // Recommend connection pool implementation/usage
        return null;
    }
    
    @Override
    public PostDAO getPostDAO() {
        return null;
    }

    @Override
    public UserDAO getUserDAO() {
        return null;
    }

    @Override
    public CommentDAO getCommentDAO() {
        return null;
    }

    @Override
    public MessageDAO getMessageDAO() {
        return null;
    }

    @Override
    public PostIsgDAO getPostIsgDAO() {
        return null;
    }

    @Override
    public EmailTracDAO getEmailTracDAO() {
        return null;
    }

    @Override
    public CommentIsgDAO getCommentIsgDAO() {
        return null;
    }

    @Override
    public PasswordTracDAO getPasswordTracDAO() {
        return null;
    }
    
}
