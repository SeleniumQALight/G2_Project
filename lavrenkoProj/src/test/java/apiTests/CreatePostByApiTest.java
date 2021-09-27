package apiTests;

import api.ApiHelper;
import api.AuthorDTO;
import api.Endpoints;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CreatePostByApiTest {

    final String username = "lavrenko";
    final String password = "123412341234";
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts(){
        apiHelper.deletePostsTillPresent(username, password);
    }

    @Test
    public void createNewPostApi() {
        String token = apiHelper.getToken(username, password);
        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New post - APItest");
        requestParams.put("body", "Body of the post");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        String response =

        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()

                .when()
                .post(Endpoints.CREATE_POST)

        .then()
        .statusCode(200)
        .extract().response().getBody().asString()
                ;

        Assert.assertEquals("Response body is matching","\"Congrats.\"",response);

        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser(username);
        Assert.assertEquals("Number of posts", 1, actualPostDTO.length);

        PostDTO[] expectedPostDTO = {
                new PostDTO(
                        requestParams.getString("title"),
                        requestParams.getString("body"),
                        requestParams.getString("select1"),
                        new AuthorDTO(username),
                        false
                )
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPostDTO[0])
                .isEqualToIgnoringGivenFields(expectedPostDTO[0], "_id", "createdDate","author");
        softAssertions.assertThat(actualPostDTO[0].getAuthor())
                .isEqualToIgnoringGivenFields(expectedPostDTO[0].getAuthor(), "avatar");
        softAssertions.assertAll();
    }
}
