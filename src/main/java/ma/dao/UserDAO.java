
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.User;

public interface UserDAO {
    public int insertUser(User user);
    public boolean deleteUser(User user);
    public boolean updateUser(User user);
    public User findUser(String userQuery);
    public RowSet selectUsersRS(String userQuery);
    public Collection selectUsersTO(String userQuery);
}
