
package ma.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.util.ConnectionPool;
import ma.business.Commentinsight;


public class CommentInsightDB{
    
    public static long insert(Commentinsight ci){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into commentinsight (user_id, post_id, comment_id, is_commented, is_like, is_dislike) values(?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ci.getUser().getId());
            ps.setInt(2, ci.getPost().getId());
            ps.setInt(3, ci.getComment().getId());
            ps.setInt(4, ci.getIsCommented());
            ps.setInt(5, ci.getIsLike());
            ps.setInt(6, ci.getIsDislike());

            return ps.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(CommentInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    
    public static long update(Commentinsight ci){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update commentinsight set is_commented = ?,"
                + " is_like = ?,"
                + " is_dislike = ?"
                + " where id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ci.getIsCommented());
            ps.setInt(2, ci.getIsLike());
            ps.setInt(3, ci.getIsDislike());
            ps.setInt(4, ci.getId());

            return ps.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(CommentInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static long delete(Commentinsight ci){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from commentinsight where user_id = ? and post_id = ? and comment_id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, ci.getUser().getId());
            ps.setInt(2, ci.getPost().getId());
            ps.setInt(3, ci.getComment().getId());

            return ps.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(CommentInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static Commentinsight getCommentIsg(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs;
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            return new Commentinsight(
                    rs.getInt(1),
                    UserDB.getUser("select * from user where id = " + rs.getInt(2)),
                    PostDB.getPost("select * from post where id = " + rs.getInt(3)),
                    CommentDB.getComment("select * from comment where id = " + rs.getInt(4)),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getString(8)
            );


        } catch (SQLException ex) {
            Logger.getLogger(CommentInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Commentinsight> getAll(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Commentinsight> all = new ArrayList<>();
        ResultSet rs;
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                all.add(
                
                    new Commentinsight(
                    rs.getInt(1),
                    UserDB.getUser("select * from user where id = " + rs.getInt(2)),
                    PostDB.getPost("select * from post where id = " + rs.getInt(3)),
                    CommentDB.getComment("select * from comment where id = " + rs.getInt(4)),
                    rs.getInt(5),
                    rs.getInt(6),
                    rs.getInt(7),
                    rs.getString(8)
                ));
            }
            
            return all;

        } catch (SQLException ex) {
            Logger.getLogger(CommentInsightDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
}
