import api.EndPoints;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

    @Test
    public void getAllPostsByUser(){
             given()
                   .contentType(ContentType.JSON)
                   .log().all()
             .when()
                   .get(POST_BY_USER, USER_NAME)
             .then()
                   .statusCode(200)
                   .log().all();


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
        Assert.assertEquals
                ("Massege in response","\"Sorry, invalid user requested.undefined\"",responseBody);
    }
    @Test
    public void getAllPostsByUserPath() {
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
            softAssertions.assertThat(titleList.get(i)).as("Item number", + i).contains("test");
            softAssertions.assertThat(authorList.get(i).get("username"))
                    .as("Item number" +i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }
    @Test
    public void getAllPostByUsersSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POST_BY_USER, USER_NAME)
                .then()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));
    }
}
