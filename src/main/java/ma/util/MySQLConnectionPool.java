package ma.util;

import java.sql.*;
import javax.sql.DataSource;
import javax.naming.*;

public class MySQLConnectionPool {

    private static MySQLConnectionPool pool = null;
    private static DataSource dataSource = null;

    public synchronized static MySQLConnectionPool getInstance() {
        if (pool == null) {
            pool = new MySQLConnectionPool();
        }
        return pool;
    }

    private MySQLConnectionPool() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:/comp/env/jdbc/blog");
        } catch (NamingException e) {
            System.err.println(e);
        }
    }

    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException sqle) {
            System.err.println(sqle);
            return null;
        }
    }

    public void freeConnection(Connection c) {
        try {
            c.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}