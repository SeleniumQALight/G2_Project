package api;

public class AuthorDTO {
    String username;
    String avatar;

// this constructor is required for check in test
    public AuthorDTO(String username) {
        this.username = username;
    }

//  this constructor is required for RestAssured
    public  AuthorDTO(){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() { //generated by Alt+Insert
        return "AuthorDTO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
