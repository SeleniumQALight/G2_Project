package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import libs.TestData;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.junit.Assert;
import weka.core.Utils;

import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification requestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public void createPost(String title, String userName, String passWord) {
        String token = getToken(userName, passWord);

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

    public String getToken(String userName, String passWord) {
        JSONObject requestParams = new JSONObject();
        requestParams.put("username", userName);
        requestParams.put("password", passWord);

        ResponseBody responseBody =
                given()
                        .spec(requestSpecification)
                        .body(requestParams.toMap())
                        .when()
                        .post(EndPoints.LOGIN)
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
                .get(EndPoints.POST_BY_USER, userName)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(PostDTO[].class);
    }

    public void deletePostsTillPerson(String userName, String passWord) {
        PostDTO[] listOfPosts = getAllPostsByUser(userName);
        for (int i = 0; i < listOfPosts.length; i++) {
            deletePostById(userName, passWord, listOfPosts[i].get_id());
            logger.info(String.format("Post with id %s and title %s was deleted",
                    listOfPosts[i].get_id(),
                    listOfPosts[i].getTitle()));
        }
        Assert.assertEquals("Number Of posts ", 0, getAllPostsByUser(userName).length);
    }

    private void deletePostById(String userName, String passWord, String id) {
        String token = getToken(userName, passWord);

        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("token", token);

        given()
                .spec(requestSpecification)
                .body(bodyRequest.toMap())
                .when()
                .delete(EndPoints.DELETE_POST, id)
                .then()
                .statusCode(200)
                .log().all();

    }

    public void getCurrencyCurrentExchangeViaAPI(String currency) {

        CcyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPoints.CCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CcyDTO[].class);

        //Temporary hardcode. It is necessary to fix the bug with RUB name.
        if (currency.equalsIgnoreCase("RUB")) {
            currency = "RUR";
            logger.info("Currency was changed to RUR");
        } else {
            logger.info("Currency is correct to continue");
        }
        //Temporary hardcode. It is necessary to fix the bug with RUB name.

        try {
            for (int i = 0; i < responseBody.length; i++) {
                if (responseBody[i].getCcy().equalsIgnoreCase(currency)) {
                    TestData.setCurrency_buy_api(Utils.roundDouble(Double.parseDouble(responseBody[i].getBuy()), 1));
                    logger.info("Currency " + currency + " buy exchange via API is " + TestData.getCurrency_buy_api());
                    TestData.setCurrency_sell_api(Utils.roundDouble(Double.parseDouble(responseBody[i].getSale()), 1));
                    logger.info("Currency " + currency + " sell exchange via API is " + TestData.getCurrency_sell_api());
                }
            }
        } catch (Exception e) {
            logger.error("Currency " + currency + " exchange does not return" + e);
            Assert.fail("Currency " + currency + " exchange does not return" + e);
        }
    }
}
