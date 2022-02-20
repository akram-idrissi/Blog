
package ma.data;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.util.MySQLConnectionPool;
import ma.business.PasswordTrac;


public class VerifyPasswordDB {
    
    public static long insert(PasswordTrac vp){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into verifypassword (email, code, flag) values(?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, vp.getEmail());
            ps.setString(2, vp.getCode());
            ps.setInt(3, vp.getFlag());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static long update(PasswordTrac vp){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update verifypassword set code = ?,"
                + " flag = ?"
                + " where email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, vp.getCode());
            ps.setInt(2, vp.getFlag());
            ps.setString(3, vp.getEmail());

            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static long delete(PasswordTrac vp){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from verifypassword where email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, vp.getEmail());

            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static PasswordTrac getVerifyPassword(String query){
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            return new PasswordTrac(
                    rs.getInt("id"),
                    rs.getString("email"),
                    rs.getString("code"),
                    rs.getInt("flag"),
                    rs.getString("date")
            );
        } catch (SQLException ex) {
            Logger.getLogger(UserDB.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally{
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
}
