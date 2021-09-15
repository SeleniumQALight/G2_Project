package apiTests;

import api.AuthorDTO;
import api.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "testiren";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {

        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
        .response().as(PostDTO[].class);
        logger.info(responseBody.length);
        logger.info(responseBody[0].getTitle());
        logger.info(responseBody[0].getAuthor().getUsername());

        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Username", "testiren", responseBody[i].getAuthor().getUsername());
        }
            //Create an Object to compare ER & AR
            PostDTO[] expectedPostDTO = {
                    new PostDTO("New Post from IrenUserAPI - 23", "post body", "One Person1",
                            new AuthorDTO(USER_NAME), false),
                    new PostDTO("New Post from IrenUserAPI - 23", "post body", "One Person1",
                            new AuthorDTO(USER_NAME), false),
                    new PostDTO("New Post from IrenUserAPI - 1", "post body", "One Person1",
                            new AuthorDTO(USER_NAME), false),
            };

            Assert.assertEquals(responseBody.length, expectedPostDTO.length);
            SoftAssertions softAssertions = new SoftAssertions();

            for(int i = 0; i < expectedPostDTO.length; i++) {
                //fields for ignoring
                softAssertions.assertThat(expectedPostDTO[i])
                        .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
                softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                        .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(),"avatar");
            }

            softAssertions.assertAll();

        }
    }
