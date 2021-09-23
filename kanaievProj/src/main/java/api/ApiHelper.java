package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;

import static api.EndPoints.LOGIN;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());
    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String password) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", password);

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

    public PostDTO[] getAllPostsByUser(String userName) {
        return
                given()
                        .spec(requestSpecification)
                        .when()
                        .get(POST_BY_USER, userName)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent(String userName, String password) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);

        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName, password, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted"
                    , listOfPosts[i].get_id()
                    , listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number of posts ", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String password, String postId) {
        String token = getToken(userName, password);
        JSONObject requestBody = new JSONObject();
        requestBody.put("token", token);
        given()
                .spec(requestSpecification)
                .body(requestBody.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, postId)
                .then()
                .statusCode(200).log().all();
    }
}
