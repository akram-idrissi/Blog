
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.CommentIsg;

public interface CommentIsgDAO {
    public int insertCommentIsg(CommentIsg commentIsg);
    public boolean deleteCommentIsg(CommentIsg commentIsg);
    public boolean updateCommentIsg(CommentIsg commentIsg);
    public CommentIsg findCommentIsg(String commentIsgQuery);
    public RowSet selectCommentIsgsRS(String commentIsgQuery);
    public Collection selectCommentIsgsTO(String commentIsgQuery);
}
