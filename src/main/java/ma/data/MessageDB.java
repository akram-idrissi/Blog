
package ma.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.business.Message;
import ma.util.ConnectionPool;


public class MessageDB {
    
    public static long insert(Message msg){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into message values(?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, msg.getSenderId().getId());
            ps.setInt(2, msg.getReceiverId().getId());
            ps.setString(3, msg.getMessageDate());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static long delete(Message msg){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from message where id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, msg.getId());
            
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    
    
    
}
