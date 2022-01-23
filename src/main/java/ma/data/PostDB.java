
package ma.data;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.business.Post;
import ma.util.ConnectionPool;


public class PostDB {

    public static long insert(Post post){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into post "
                + "(user_id, title, content, like_count, dislike_count, comment_count)"
                + " values(?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, post.getUser().getId());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getContent());
            ps.setInt(4, post.getLikeCount());
            ps.setInt(5, post.getDislikeCount());
            ps.setInt(6, post.getCommentCount());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    } 
    
    public static long update(Post post){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update post set title = ?,"
                + " content = ?,"
                + " like_count = ?,"
                + " dislike_count = ?,"
                + " comment_count = ?"
                + " where id = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, post.getTitle());
            ps.setString(2, post.getContent());
            ps.setInt(3, post.getLikeCount());
            ps.setInt(4, post.getDislikeCount());
            ps.setInt(5, post.getCommentCount());
            ps.setInt(6, post.getId());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    } 
    
    public static long delete(Post post){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from post where id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, post.getId());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(PostDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    } 
    
    public static Post getPost(String query){
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet resultset = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            resultset = ps.executeQuery();
            
            if(resultset.next()){
                return new Post(
                    resultset.getInt("id"),
                    UserDB.getUser("select * from user where id = " + resultset.getInt("user_id")),
                    resultset.getString("title"),
                    resultset.getString("content"),
                    resultset.getInt("like_count"),
                    resultset.getInt("dislike_count"),
                    resultset.getInt("comment_count"),
                    resultset.getString("posted_date")
                 );
            } else{
                return null;
            }
                
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }finally{
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Post> getAll(){
        
        ArrayList<Post> posts = new ArrayList();
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultset = null;
        String select = "select * from post order by id desc";
        try {
            ps = connection.prepareStatement(select);
            resultset = ps.executeQuery();
            while(resultset.next()){
                posts.add(new Post(
                    resultset.getInt("id"),
                    UserDB.getUser("select * from user where id = " + resultset.getInt("user_id")),
                    resultset.getString("title"),
                    resultset.getString("content"),
                    resultset.getInt("like_count"),
                    resultset.getInt("dislike_count"),
                    resultset.getInt("comment_count"),
                    resultset.getString("posted_date")
                ));
                
            }
            return posts;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return posts;
        
    }
    
}
