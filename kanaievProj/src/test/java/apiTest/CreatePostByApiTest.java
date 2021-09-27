package apiTest;

import api.ApiHelper;
import api.AuthorDTO;
import api.EndPoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    final String USER_NAME = "pkanaiev";
    final String PASSWORD = "123456qwerty";
    private ApiHelper apiHelper = new ApiHelper();

    @Before
    public void DeleteAllPosts(){
        apiHelper.deletePostsTillPresent(USER_NAME, PASSWORD);
    }

    @Test
    public void createNewPostByApi() {
        String token = apiHelper.getToken(USER_NAME, PASSWORD);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "Pasha ApiTitle " + Util.getDateAndTimeFormatted());
        requestParams.put("body", "Body of post ... ... ...");
        requestParams.put("select1", "All Users");
        requestParams.put("token", token);

        String response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Message", "\"Congrats.\"", response);

        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser(USER_NAME);
        Assert.assertEquals("Number of posts", 1, actualPostDTO.length);

        PostDTO[] expectedPostDTO = {
                new PostDTO(
                        requestParams.getString("title"),
                        requestParams.getString("body"),
                        requestParams.getString("select1"),
                        new AuthorDTO(USER_NAME),
                        false
                )
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostDTO[0])
                .isEqualToIgnoringGivenFields(expectedPostDTO[0], "_id", "createdDate", "author");
        softAssertions.assertThat(actualPostDTO[0].getAuthor())
                .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(), "avatar");
        softAssertions.assertAll();
    }
}
