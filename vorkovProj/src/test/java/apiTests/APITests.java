package apiTests;

import io.restassured.http.ContentType;
import org.junit.Test;

import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;

public class APITests {
    final String USER_NAME = "autoapi";


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
