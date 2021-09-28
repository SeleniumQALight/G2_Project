package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.Endpoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String username, String password){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username",username);
        requestParams.put("password", password);
        ResponseBody responseBody = given()
                .spec(requestSpecification)
                .body(requestParams.toMap())
                .when()
                .post(Endpoints.LOGIN)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody();

        return responseBody.asString().replace("\"","");

    }
    public PostDTO[] getAllPostsByUser(String username){
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

    public void deletePostsTillPresent(String username, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(username);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostsById(username,password,listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted"
            ,listOfPosts[i].get_id()
            ,listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of posts ", 0, getAllPostsByUser(username).length);
    }

    private void deletePostsById(String username, String password, String id) {
        String token = getToken(username, password);
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
                .when()
                .delete(Endpoints.DELETE_POST, id)
                .then()
                .statusCode(200).log().all();

    }
}
