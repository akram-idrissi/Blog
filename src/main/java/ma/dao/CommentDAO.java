
package ma.dao;

import java.util.Collection;
import javax.sql.RowSet;
import ma.business.Comment;


public interface CommentDAO {
    public int insertComment(Comment comment);
    public boolean deleteComment(Comment comment);
    public boolean updateComment(Comment comment);
    public Comment findComment(String commentQuery);
    public RowSet selectCommentsRS(String commentQuery);
    public Collection selectCommentsTO(String commentQuery);
}
