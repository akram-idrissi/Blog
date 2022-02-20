
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.EmailTrac;

public interface EmailTracDAO {
    public int insertEmailTrac(EmailTrac emailTrac);
    public boolean deleteEmailTrac(EmailTrac emailTrac);
    public boolean updateEmailTrac(EmailTrac emailTrac);
    public EmailTrac findEmailTrac(String emailTracQuery);
    public RowSet selectEmailTracsRS(String emailTracQuery);
    public Collection selectEmailTracsTO(String emailTracQuery);
}
