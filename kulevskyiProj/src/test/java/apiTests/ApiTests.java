package apiTests;

import api.AuthorDTO;
import api.CurrencyDTO;
import api.PostDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;



import java.util.List;
import java.util.Map;

import static api.EndPoints.POST_BY_PRIVAT_BANK;
import static api.EndPoints.POST_BY_USER;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTests {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser() {
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200)
                .log().all()
                .extract().response().as(PostDTO[].class);

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
                    .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(), "avatar");

        }

        softAssertions.assertAll();
    }


    @Test
    public void getAllPostByUserNegative() {
        String responsBody = given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(POST_BY_USER, "notValidUser")
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();
        Assert.assertEquals("Message in response", "\"Sorry, invalid user requested.undefined\"", responsBody);
//        Assert.assertEquals("Message in response","\"Sorry, invalid user requested.undefined\"",responsBody.replace("\"",""));
    }

    @Test
    public void getAllPostsByUserPath() {
        Response responseBody = given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(POST_BY_USER, USER_NAME)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response();
        List<String> titleList = responseBody.jsonPath().getList("title", String.class);
        List<Map> autoList = responseBody.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < titleList.size(); i++) {
            softAssertions.assertThat(titleList.get(i)).as("Item number " + i).contains("test");
            softAssertions.assertThat(autoList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(USER_NAME);
        }


        softAssertions.assertAll();
    }


    @Test
    public void getAllPostsByUserSchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(POST_BY_USER, USER_NAME)
        .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("respons.json"));
    }

    @Test
    public void getAllCurrencyByPrivatBank() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("coursid","5")
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
