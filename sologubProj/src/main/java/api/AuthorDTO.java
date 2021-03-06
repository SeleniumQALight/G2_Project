package api;

public class AuthorDTO {
    String username;
    String avatar;

    public AuthorDTO(String username) {
        this.username = username;
    }

    public AuthorDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "userName='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
