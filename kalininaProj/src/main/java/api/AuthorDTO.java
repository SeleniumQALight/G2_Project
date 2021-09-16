package api;

public class AuthorDTO {
    String  username;
    String avatar;

    public AuthorDTO(String username) {
        this.username = username;
    }

    public AuthorDTO(){


    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String avatar){
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "AuthorDTO{" +
                "username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

}
