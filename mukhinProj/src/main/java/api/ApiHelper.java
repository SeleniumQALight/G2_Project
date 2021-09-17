package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import static api.EndPoints.LOGIN;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String passWord){
        JSONObject requestParam = new JSONObject();
        requestParam.put("username", userName);
        requestParam.put("password", passWord);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParam.toMap())
                .when()
                        .post(LOGIN)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody();

        return responseBody.asString().replaceAll("\"", "");

    }
}
