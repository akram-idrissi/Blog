
package ma.dao;

/**
 *
 * @author Ce pc
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        DAOFactory mysqlFactory = DAOFactory.getDactory(DAOFactory.MYSQL);
        PostDAO postDAO = mysqlFactory.getPostDAO();
        
    }
    
}
