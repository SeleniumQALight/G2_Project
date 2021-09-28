package apiTests;

import api.AuthorDTO;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostByUser(){
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
            Assert.assertEquals("Username", "autoapi", responseBody[i].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test2", "test body2", "All Users", new AuthorDTO(USER_NAME), false),
                new PostDTO("test", "test body", "All Users", new AuthorDTO(USER_NAME), false)
        };

        Assert.assertEquals(responseBody.length, expectedPostDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();


        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(expectedPostDTO[i])
                .isEqualToIgnoringGivenFields(responseBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar" );
        }


        softAssertions.assertAll();
    }

    @Test
    public void getAllPostByUserNegative(){
        String responseBody =
                given()
                    .contentType(ContentType.JSON)
                    .log().all()
                .when()
                    .get(POST_BY_USER, "notValidUser")
                .then()
                    .statusCode(200)
                    .log().all()
                    .extract().response().getBody().asString();

        Assert.assertEquals("Message in response", "\"Sorry, invalid user requested.undefined\"", responseBody);
        Assert.assertEquals("Message in response", "Sorry, invalid user requested.undefined", responseBody.replaceAll("\"", ""));
    }

    @Test
    public  void getAllPostByUserPath(){
        Response responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(POST_BY_USER, USER_NAME)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        List<String> titleList = responseBody.jsonPath().getList("title", String.class);
        List<Map> authorList = responseBody.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < titleList.size(); i++) {
            softAssertions.assertThat(titleList.get(i)).as("Item number" + i).contains("test");
            softAssertions.assertThat(authorList.get(i).get("username"))
                    .as("Item number" + i).isEqualTo(USER_NAME);

        }

        softAssertions.assertAll();

        logger.info(responseBody.getTime());
        logger.info(responseBody.getTimeIn(TimeUnit.SECONDS));
        logger.info(responseBody.time());
        logger.info(responseBody.timeIn(TimeUnit.MILLISECONDS));
    }


    @Test
    public void getALLPostByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("resources/responseHW.json"));
    }
}
