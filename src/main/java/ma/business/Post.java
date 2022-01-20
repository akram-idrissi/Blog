package ma.business;


public class Post  implements java.io.Serializable {

    private Integer id;
    private User user;
    private String title;
    private String content;
    private Integer likeCount;
    private Integer dislikeCount;
    private Integer commentCount;
    private String postedDate;
    
    public Post() {
    }

	
    public Post(User user, String postedDate) {
        this.user = user;
        this.postedDate = postedDate;
    }
    public Post(Integer id, User user, String title, String content, Integer likeCount, Integer dislikeCount, Integer commentCount, String postedDate) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.dislikeCount = dislikeCount;
        this.commentCount = commentCount;
        this.postedDate = postedDate;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    
    public Integer getLikeCount() {
        return this.likeCount;
    }
    
    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getDislikeCount() {
        return this.dislikeCount;
    }
    
    public void setDislikeCount(Integer dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Integer getCommentCount() {
        return this.commentCount;
    }
    
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public String getPostedDate() {
        return this.postedDate;
    }
    
    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }
}


