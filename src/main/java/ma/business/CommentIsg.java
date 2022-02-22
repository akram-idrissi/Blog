package ma.business;

public class CommentIsg extends PojoFactory implements java.io.Serializable {

    private Integer id;
    private User user;
    private Post post;
    private Comment comment;
    private Integer isCommented;
    private Integer isLike;
    private Integer isDislike;
    private String commentInsDate;
    

    public CommentIsg() {
    }

    public CommentIsg(Integer id, User user, Post post, Comment comment, Integer isCommented, Integer isLike, Integer isDislike, String commentInsDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.comment = comment;
        this.isCommented = isCommented;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.commentInsDate = commentInsDate;
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


