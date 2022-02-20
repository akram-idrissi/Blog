
package ma.data;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.util.MySQLConnectionPool;
import ma.business.PostIsg;


public class PostInsightDB {
    
    public static long insert(PostIsg pi){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into postinsight (user_id, post_id, is_like, is_dislike) values(?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pi.getUser().getId());
            ps.setInt(2, pi.getPost().getId());
            ps.setInt(3, pi.getIsLike());
            ps.setInt(4, pi.getIsDislike());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(PostInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static long update(PostIsg pi){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update postinsight set is_like = ?, is_dislike = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pi.getIsLike());
            ps.setInt(2, pi.getIsDislike());
            
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static long delete(PostIsg pi){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from postinsight where user_id = ? and post_id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, pi.getUser().getId());
            ps.setInt(2, pi.getPost().getId());
            
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }    
    
    public static PostIsg getPostInsight(String query){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet resultset = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            resultset = ps.executeQuery();
            if(resultset.next()){
                return new PostIsg(
                    resultset.getInt("id"),
                    UserDB.getUser("select * from user where id = " + resultset.getInt("user_id")),
                    PostDB.getPost("select * from post where id = " + resultset.getInt("post_id")), 
                    resultset.getInt("is_like"),
                    resultset.getInt("is_dislike"),
                    resultset.getString("postIns_date")
                 );
            } else
                return null;
                
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    } 
    
}
