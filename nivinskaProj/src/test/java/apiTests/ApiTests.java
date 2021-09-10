package apiTests;

import api.EndPoints;
import io.restassured.http.ContentType;
import org.junit.Test;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class ApiTests {
    final String USER_NAME = "autoapi";

    @Test
    public void getAllPostsByUser(){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(POST_BY_USER, USER_NAME)
                .then()
                .statusCode(200);
    }

}
