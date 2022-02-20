
package ma.data;

import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.business.Message;
import ma.util.MySQLConnectionPool;


public class MessageDB {
    
    
    
    public static long insert(Message msg){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into message (sender_id, receiver_id, msg) values(?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, msg.getSenderId().getId());
            ps.setInt(2, msg.getReceiverId().getId());
            ps.setString(3, msg.getMsg());
            
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
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
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
    
    public static Message getMessage(String query){
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Message(
                        rs.getInt(1),
                        UserDB.getUser("select * from user where id = " + rs.getInt(2)),
                        UserDB.getUser("select * from user where id = " + rs.getInt(3)),
                        rs.getString(4),
                        rs.getString(5)
                    );
            } else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MessageDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Message> getAll(String query){
        
        ArrayList<Message> users = new ArrayList();
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultset = null;

        try {
            ps = connection.prepareStatement(query);
            resultset = ps.executeQuery();
            while(resultset.next()){
                users.add(new Message(
                    resultset.getInt(1),
                    UserDB.getUser("select * from user where id = " + resultset.getInt(2)),
                    UserDB.getUser("select * from user where id = " + resultset.getInt(3)),
                    resultset.getString(4),
                    resultset.getString(5)
                ));
                
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(MessageDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return users;
    }
    
    
    
}
