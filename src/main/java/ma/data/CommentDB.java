
package ma.data;


import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import ma.util.DBUtil;
import ma.business.Comment;
import ma.util.ConnectionPool;

public class CommentDB {
    
    public static long insert(Comment cmt){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into comment (user_id, post_id, content, like_count, dislike_count) values(?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, cmt.getUser().getId());
            ps.setInt(2, cmt.getPost().getId());
            ps.setString(3, cmt.getContent());
            ps.setInt(4, cmt.getLikeCount());
            ps.setInt(5, cmt.getDislikeCount());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static long update(Comment cmt){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update comment set content = ?,"
                + " ike_count = ?,"
                + " dislike_count = ?"
                + " where id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, cmt.getContent());
            ps.setInt(2, cmt.getLikeCount());
            ps.setInt(3, cmt.getDislikeCount());
            ps.setInt(4, cmt.getId());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static long delete(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        try {
            ps = connection.prepareStatement(query);

            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CommentDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Comment getComment(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs;
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if(rs.next()){
                return new Comment(
                        rs.getInt(1),
                        UserDB.getUser("select * from user where id = " + rs.getInt(2)),
                        PostDB.getPost("select * from post where id = " + rs.getInt(3)),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7)
                );
            } else{
                return null;
            }


        } catch (SQLException ex) {
            Logger.getLogger(CommentDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Comment> getAll(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ArrayList<Comment> comments = new ArrayList<>();
        ResultSet rs;
        
        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next()){
                comments.add(new Comment(
                        rs.getInt(1),
                        UserDB.getUser("select * from user where id = " + rs.getInt(2)),
                        PostDB.getPost("select * from post where id = " + rs.getInt(3)),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getString(7)
                ));
                        
            }
            
            return  comments;

        } catch (SQLException ex) {
            Logger.getLogger(CommentDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
}
