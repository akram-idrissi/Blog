
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.PostIsg;

public interface PostIsgDAO {
    public int insertPostIsg(PostIsg postIsg);
    public boolean deletePostIsg(PostIsg postIsg);
    public boolean updatePostIsg(PostIsg postIsg);
    public PostIsg findPostIsg(String postIsgQuery);
    public RowSet selectPostIsgsRS(String postIsgQuery);
    public Collection selectPostIsgsTO(String postIsgQuery);
}
