package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static api.EndPoints.CREATE_POST;
import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {
    final String USERNAME = "achu";
    final String PASSWORD = "123456qwerty";
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts(){
        apiHelper.deletePostsTillPresentForUser(USERNAME, PASSWORD);
    }

    @Test
    public void createNewPostByApi() {
        String token = apiHelper.getToken(USERNAME, PASSWORD);
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New post from API");
        requestParams.put("body", "Body of new post from API");
        requestParams.put("select1", "One person");
        requestParams.put("token", token);

        String response = given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()
                .when()
                .post(CREATE_POST)
                .then()
                .statusCode(200)
                .extract().response().as(String.class);
        Assert.assertEquals("Message in response:", "Congrats.", response);

        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser(USERNAME);
        Assert.assertEquals("Number of posts:", 1, actualPostDTO.length);

//      requestParams.get("body").toString() ~ requestParams.getString("body")
        PostDTO[] expectedPostDTO = {
                new PostDTO(
                        requestParams.getString("title"),
                        requestParams.getString("body"),
                        requestParams.getString("select1"),
                        new AuthorDTO(USERNAME),
                        false
                )
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostDTO[0])
                .isEqualToIgnoringGivenFields(expectedPostDTO[0],
                        "_id", "createdDate","author");
        softAssertions.assertThat(actualPostDTO[0].getAuthor())
                .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(),
                        "avatar");
        softAssertions.assertAll();


    }
}
