package ma.business;


public class PasswordTrac extends PojoFactory implements java.io.Serializable {

    private Integer id;
    private String email;
    private String code;
    private Integer flag;
    private String date;

    public PasswordTrac() {
    }

    public PasswordTrac(Integer id, String email, String code, Integer flag, String date) {
        this.id = id;
        this.email = email;
        this.code = code;
        this.flag = flag;
        this.date = date;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
}


