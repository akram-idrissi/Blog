package ma.business;

public class Comment  implements java.io.Serializable {

    private Integer id;
    private User user;
    private Post post;
    private String content;
    private Integer likeCount;
    private Integer dislikeCount;
    private String commentDate;

    public Comment() {
    }

    public Comment(Integer id, User user, Post post, String content, Integer likeCount, Integer dislikeCount, String commentDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.content = content;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentDate = commentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(String commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" + "id=" + id + ", user=" + user + ", post=" + post + ", content=" + content + ", likeCount=" + likeCount + ", dislikeCount=" + dislikeCount + ", commentDate=" + commentDate + '}';
    }

}