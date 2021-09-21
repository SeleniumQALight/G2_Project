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

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    final String USER_NAME = "autoapi";

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostDTO[] responceBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(POST_BY_USER, USER_NAME)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(PostDTO[].class);

//        logger.info(responceBody.length);
//        logger.info(responceBody[0].getTitle());
//        logger.info(responceBody[0].getAuthor().getUsername());
//        for (int i = 0; i < responceBody.length; i++) {
//            Assert.assertEquals("Username not equals", "autoapi", responceBody[i].getAuthor().getUsername());
//        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test2", "test body2", "All Users", new AuthorDTO(USER_NAME), false),
                new PostDTO("test", "test body", "All Users", new AuthorDTO(USER_NAME), false)
        };

        Assert.assertEquals(responceBody.length, expectedPostDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPostDTO.length; i++) {
            softAssertions.assertThat(expectedPostDTO[i]).isEqualToIgnoringGivenFields(responceBody[i], "_id", "createdDate", "author");
            softAssertions.assertThat(expectedPostDTO[i].getAuthor()).isEqualToIgnoringGivenFields(responceBody[i].getAuthor(), "avatar");
        }
        softAssertions.assertAll();
    }


    @Test
    public void getAllPostByUserNegative() {
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

        Assert.assertEquals("Kva", "\"Sorry, invalid user requested.undefined\"", responseBody);
        Assert.assertEquals("Kva", "Sorry, invalid user requested.undefined", responseBody.replace("\"", ""));
    }

    @Test
    public void getAllPostsByUserPAth() {
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
            softAssertions.assertThat(titleList.get(i)).as("Item number " + i).contains("test");
            softAssertions.assertThat(authorList.get(i).get("username")).as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();

    }

    @Test
    public void getAllPostsByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }


}
