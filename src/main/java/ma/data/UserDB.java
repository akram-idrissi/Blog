
package ma.data;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import ma.util.DBUtil;
import ma.business.User;
import ma.util.MySQLConnectionPool;


public class UserDB {

    public static long insert(User user){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into user (username, email, hashedpass, salt, valide, image)"
                + " values(?, ?, ?, ?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getHashedpass());
            ps.setString(4, user.getSalt());
            ps.setInt(5, user.getValide());
            ps.setString(6, user.getImage());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
    } 
    
    public static long update(User user){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update user set username = ?,"
                + " email = ?,"
                + " hashedpass = ?,"
                + " salt = ?,"
                + " valide = ?,"
                + " image = ?"
                + " where id = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getHashedpass());
            ps.setString(4, user.getSalt());
            ps.setInt(5, user.getValide());
            ps.setString(6, user.getImage());
            ps.setInt(7, user.getId());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
        
    } 
    
    public static long delete(User user){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from user where id = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1, user.getId());
            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    } 
    
    public static User getUser(String query){
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            if(rs.next()){
                return new User(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getInt(7),
                        rs.getString(8)
                    );
            } else{
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<User> getAll(String query){
        
        ArrayList<User> users = new ArrayList();
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet resultset = null;

        try {
            ps = connection.prepareStatement(query);
            resultset = ps.executeQuery();
            while(resultset.next()){
                users.add(new User(
                    resultset.getInt("id"),
                    resultset.getString("username"),
                    resultset.getString("email"),
                    resultset.getString("hashedpass"),
                    resultset.getString("salt"),
                    resultset.getString("register_date"),
                    resultset.getInt("valide"),
                    resultset.getString("image")
                ));
                
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            DBUtil.closeResultSet(resultset);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return users;
    }
    
}
