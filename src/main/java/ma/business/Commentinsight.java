package ma.business;

public class Commentinsight  implements java.io.Serializable {

    private Integer id;
    private User user;
    private Post post;
    private Comment comment;
    private Integer isCommented;
    private String commentInsDate;
    private Integer isLike;
    private Integer isDislike;

    public Commentinsight() {
    }

    public Commentinsight(Integer id, User user, Post post, Comment comment, Integer isCommented, String commentInsDate, Integer isLike, Integer isDislike) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.isCommented = isCommented;
        this.commentInsDate = commentInsDate;
        this.isLike = isLike;
        this.isDislike = isDislike;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Comment getComment() {
        return this.comment;
    }
    
    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return this.post;
    }
    
    public void setPost(Post post) {
        this.post = post;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public Integer getIsCommented() {
        return this.isCommented;
    }
    
    public void setIsCommented(Integer isCommented) {
        this.isCommented = isCommented;
    }

    public String getCommentInsDate() {
        return this.commentInsDate;
    }
    
    public void setCommentInsDate(String commentInsDate) {
        this.commentInsDate = commentInsDate;
    }

    public Integer getIsLike() {
        return this.isLike;
    }
    
    public void setIsLike(Integer isLike) {
        this.isLike = isLike;
    }

    public Integer getIsDislike() {
        return this.isDislike;
    }
    
    public void setIsDislike(Integer isDislike) {
        this.isDislike = isDislike;
    }




}


