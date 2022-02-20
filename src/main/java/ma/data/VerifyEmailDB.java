
package ma.data;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;

import ma.util.DBUtil;
import ma.util.MySQLConnectionPool;
import ma.business.EmailTrac;


public class VerifyEmailDB {
    
    public static long insert(EmailTrac ve){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "insert into verifyemail (email, code, flag) values(?, ?, ?)";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ve.getEmail());
            ps.setString(2, ve.getCode());
            ps.setInt(3, ve.getFlag());
            
            return ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
            
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static long update(EmailTrac ve){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "update verifyemail set code = ?,"
                + " flag = ?"
                + " where email = ? ";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ve.getCode());
            ps.setInt(2, ve.getFlag());
            ps.setString(3, ve.getEmail());

            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static long delete(EmailTrac ve){
        
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "delete from verifyemail where email = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ve.getEmail());

            return ps.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VerifyEmailDB.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static EmailTrac getVerifyEmail(String query){
        MySQLConnectionPool pool = MySQLConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            rs.next();
            return new EmailTrac(
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
