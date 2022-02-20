
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.PasswordTrac;

public interface PasswordTracDAO {
    public int insertPasswordTrac(PasswordTrac pwdTrac);
    public boolean deletePasswordTrac(PasswordTrac pwdTrac);
    public boolean updatePasswordTrac(PasswordTrac pwdTrac);
    public PasswordTrac findPasswordTrac(String pwdTracQuery);
    public RowSet selectPasswordTracsRS(String pwdTracQuery);
    public Collection selectPasswordTracsTO(String pwdTracQuery);
}
