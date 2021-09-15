package api;

import com.google.gson.annotations.SerializedName;

public class PostDTO {
    @SerializedName("_id")
    String _id;
    @SerializedName("title")
    String title;
    @SerializedName("body")
    String body;
    @SerializedName("select1")
    String select1;
    @SerializedName("createdDate")
    String createdDate;
    @SerializedName("author")
    AuthorDTO author;
    @SerializedName("isVisitorOwner")
    Boolean isVisitorOwner;

    public PostDTO(String title, String body, String select1, AuthorDTO author, Boolean isVisitorOwner) {
        this.title = title;
        this.body = body;
        this.select1 = select1;
        this.author = author;
        this.isVisitorOwner = isVisitorOwner;
    }

    public  PostDTO(){

    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getSelect1() {
        return select1;
    }

    public AuthorDTO getAuthor() {
        return author;
    }

    public Boolean getIsVisitorOwner() {
        return isVisitorOwner;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setSelect1(String select1) {
        this.select1 = select1;
    }

    public void setAuthor(AuthorDTO author) {
        this.author = author;
    }

    public void setIsVisitorOwner(Boolean isVisitorOwner) {
        this.isVisitorOwner = isVisitorOwner;
    }
}