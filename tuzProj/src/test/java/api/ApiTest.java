package api;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.http.ContentType;

public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class)
                ;
        logger.info(responseBody.length);
        logger.info(responseBody[0].getTitle());
        logger.info(responseBody[0].getAuthor().getUsername());
        for (int i = 0;i < responseBody.length;i++){
            Assert.assertEquals("Username", "autoapi", responseBody[1].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test2","test body2", "All Users",new AuthorDTO(USER_NAME), false),
                new PostDTO("test","test body", "All Users",new AuthorDTO(USER_NAME), false)
        };

        Assert.assertEquals(responseBody.length, expectedPostDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

    for(int i = 0; i< expectedPostDTO.length; i++){
         softAssertions.assertThat(expectedPostDTO[i])
                 .isEqualToIgnoringGivenFields(responseBody[i],"_id", "createdDate", "author");
         softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                 .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(),"avatar");
    }
        softAssertions.assertAll();





    }
}