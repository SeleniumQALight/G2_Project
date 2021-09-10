package apiTest;

import io.restassured.http.ContentType;
import org.junit.Test;

import static api.EndPoints.*;
import static io.restassured.RestAssured.given;

public class ApiTest {
    private final String USER_NAME = "autoapi";

    @Test
    public void getAllPostsByUser() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200)
                .log().all();
    }
}
