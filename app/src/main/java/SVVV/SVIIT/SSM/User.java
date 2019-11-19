package SVVV.SVIIT.SSM;

public class User {
    String userId,name,email,mobile_no;

    public User(){

    }

    public User(String name, String email, String mobile_no) {
        this.name = name;
        this.email = email;
        this.mobile_no = mobile_no;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
