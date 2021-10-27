package api;


import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.*;
import static api.EndPoints.POST_BY_PRIVAT_BANK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public String getToken(String userName, String passWord){
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        ResponseBody responseBody =
                given()
                       .spec(requestSpecification)
                        .body(requestParams.toMap())
                .when()
                       .post(LOGIN)
                .then()
                       .statusCode(200)
                       .log().all()
                       .extract().response().getBody();
        return responseBody.asString().replaceAll("\"", "");
    }

    public PostDTO[] getAllPostsByUser(String userName) {
        return given()
                .spec(requestSpecification)
               .when()
                .get(POST_BY_USER, userName)
               .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
    }

    public void deletePostsTillPresent(String userName, String passWord) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName,passWord,listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted"
                                  ,listOfPosts[i].get_id()
                                  ,listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number Of Posts", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String passWord, String id) {
        String  token = getToken(userName,passWord);

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token",token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
        .when()
                .delete(EndPoints.DELETE_POST, id)
        .then()
                .statusCode(200).log().all();

    }

    public void createPost(String title, String userName, String passWord) {
        String token = getToken(userName,passWord);

        JSONObject requestParams = new JSONObject();
        requestParams.put("title", title);
        requestParams.put("body", "post body");
        requestParams.put("select1", "One Person");
        requestParams.put("token", token);

                given()
                        .contentType(ContentType.JSON)
                        .body(requestParams.toMap())
                        .log().all()
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(200);
    }
    @Test
    public CurrencyDTO[] getAllCurrencyByPrivatBank() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(POST_BY_PRIVAT_BANK)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(CurrencyDTO[].class);

        for (CurrencyDTO currencyDTO : responseBody) {
            logger.info(String.format("Курс %s к %s покупки %s и продажи %s",
                    currencyDTO.getCcy(),
                    currencyDTO.getBase_ccy(),
                    currencyDTO.getBuy(),
                    currencyDTO.getSale()));
        }

        CurrencyDTO[] expectedCurrencyDTOList = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD"),
        };

        Assert.assertEquals("Number of currencies",
                expectedCurrencyDTOList.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTOList.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTOList[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();
        return responseBody;
    }

    @Test
    public void getAllCurrencyByUserSchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POST_BY_PRIVAT_BANK)
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivatBank.json"));
    }

}
