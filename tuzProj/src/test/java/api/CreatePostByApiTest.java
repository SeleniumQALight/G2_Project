package api;

import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class CreatePostByApiTest {
    final String userName = "etuz";
    final String passWord = "Temp1234%@#$";
    ApiHelper apiHelper = new ApiHelper();

    @Before
    public void deleteAllPosts(){
        apiHelper.deletePostsTillPresent(userName, passWord);
    }

    @Test
    public void createNewPostByApi(){
        String token = apiHelper.getToken(userName, passWord);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", "New post from API");
        requestParams.put("body", "Test body API");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

        String response =


        given()
                .contentType(ContentType.JSON)
                .body(requestParams.toMap())
                .log().all()
                .when()
                .post(EndPoints.CREATE_POST)
                .then()
                .statusCode(200)
                .extract().response().getBody().asString();

        Assert.assertEquals("Message","\"Congrats.\"",response);

        PostDTO[] actualPostDTO = apiHelper.getAllPostsByUser(userName);
        Assert.assertEquals("Number of posts", 1, actualPostDTO.length);

        PostDTO[] expectedPostDTO = {
                new PostDTO(
                        requestParams.getString("title"),
                        requestParams.getString("body"),
                        requestParams.getString("select1"),
                        new AuthorDTO(userName),
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
