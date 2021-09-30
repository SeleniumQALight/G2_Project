
package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.*;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String passWord) {
        JSONObject requestParams = new JSONObject(); //not a Map, because we need diff data types
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(LOGIN)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();
        return responseBody.asString().replaceAll("\"", "");
    }

    public PostDTO[] getAllPostsByUser(String username) {
        return given()
                .spec(requestSpecification)
                .when()
                .get(POST_BY_USER, username)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
    }

    public void deletePostsTillPresentForUser(String username, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(username);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(username, password, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title '%s' was deleted",
                    listOfPosts[i].get_id(), listOfPosts[i].getTitle()));
        }

        Assert.assertEquals("Number of posts after deletion:", 0, getAllPostsByUser(username).length);
    }

    private void deletePostById(String username, String password, String id) {
        String token = getToken(username, password);

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);
        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
                .when()
                .delete(DELETE_POST, id)
                .then()
                .statusCode(200)
                .log().all();
    }

    public void createPost(String postTitle, String userName, String passWord) {
        String token = getToken(userName, passWord);
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", postTitle);
        requestParams.put("body", "Body of new post from API");
        requestParams.put("select1", "One person");
        requestParams.put("token", token);

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()
                .when()
                .post(CREATE_POST)
                .then()
                .statusCode(200);
    }
}
