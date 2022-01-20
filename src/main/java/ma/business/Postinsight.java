package ma.business;

public class Postinsight  implements java.io.Serializable {

    private Integer id;
    private User user;
    private Post post;
    private Integer isLike;
    private Integer isDislike;
    private String postInsDate;

    public Postinsight() {
    }

	
    public Postinsight(Post post, User user, String postInsDate) {
        this.post = post;
        this.user = user;
        this.postInsDate = postInsDate;
    }

    public Postinsight(Integer id, User user, Post post, Integer isLike, Integer isDislike, String postInsDate) {
        this.id = id;
        this.user = user;
        this.post = post;
        this.isLike = isLike;
        this.isDislike = isDislike;
        this.postInsDate = postInsDate;
    }
    
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
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

    public String getPostInsDate() {
        return this.postInsDate;
    }
    
    public void setPostInsDate(String postInsDate) {
        this.postInsDate = postInsDate;
    }




}


