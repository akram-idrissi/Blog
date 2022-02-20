
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.Message;


public interface MessageDAO {
    public int insertMessage(Message msg);
    public boolean deleteMessage(Message msg);
    public boolean updateMessage(Message msg);
    public Message findMessage(String msgQuery);
    public RowSet selectMessagesRS(String msgQuery);
    public Collection selectMessagesTO(String msgQuery);
}
