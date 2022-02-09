package ma.business;


public class User  implements java.io.Serializable {

    private Integer id;
    private String username;
    private String email;
    transient private String hashedpass;
    transient private String salt;
    private String registerDate;
    private Integer valide;
    private String image;
     
    
    public User() {
    }

	
    public User(String registerDate) {
        this.registerDate = registerDate;
    }

    public User(Integer id, String username, String email, String hashedpass, String salt, String registerDate, Integer valide, String image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.hashedpass = hashedpass;
        this.salt = salt;
        this.registerDate = registerDate;
        this.valide = valide;
        this.image = image;
    }
    
    
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedpass() {
        return this.hashedpass;
    }
    
    public void setHashedpass(String hashedpass) {
        this.hashedpass = hashedpass;
    }

    public String getSalt() {
        return this.salt;
    }
    
    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRegisterDate() {
        return this.registerDate;
    }
    
    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public Integer getValide() {
        return this.valide;
    }
    
    public void setValide(Integer valide) {
        this.valide = valide;
    }

    public String getImage() {
        return this.image;
    }
    
    public void setImage(String image) {
        this.image = image;
    }
}


