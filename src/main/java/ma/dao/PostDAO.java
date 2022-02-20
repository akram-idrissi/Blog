
package ma.dao;

import javax.sql.RowSet;
import java.util.Collection;

import ma.business.Post;

public interface PostDAO {
    public int insertPost(Post post);
    public boolean deletePost(Post post);
    public boolean updatePost(Post post);
    public Post findPost(String postQuery);
    public RowSet selectPostsRS(String postQuery);
    public Collection selectPostsTO(String postQuery);
}
