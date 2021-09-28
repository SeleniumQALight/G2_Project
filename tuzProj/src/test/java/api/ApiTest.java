package api;

import static api.EndPoints.POST_BY_USER;
import static api.EndPoints.CURRENCY_EXCHANGE;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import io.qameta.allure.restassured.AllureRestAssured;
import org.assertj.core.api.SoftAssertions;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.Map;


public class ApiTest {
    final String USER_NAME = "autoapi";
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPostsByUser(){
        PostDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .filter(new AllureRestAssured())
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
        for (int i = 0;i < responseBody.length;i++){
            Assert.assertEquals("Username", "autoapi", responseBody[1].getAuthor().getUsername());
        }

        PostDTO[] expectedPostDTO = {
                new PostDTO("test2","test body2", "All Users",new AuthorDTO(USER_NAME), false),
                new PostDTO("test","test body", "All Users",new AuthorDTO(USER_NAME), false)
        };

        Assert.assertEquals(responseBody.length, expectedPostDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

    for(int i = 0; i< expectedPostDTO.length; i++){
         softAssertions.assertThat(expectedPostDTO[i])
                 .isEqualToIgnoringGivenFields(responseBody[i],"_id", "createdDate", "author");
         softAssertions.assertThat(expectedPostDTO[i].getAuthor())
                 .isEqualToIgnoringGivenFields(responseBody[i].getAuthor(),"avatar");
    }
        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserNegative(){
        String responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POST_BY_USER,"notValidUser")
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody().asString();

        Assert.assertEquals("Message in response","\"Sorry, invalid user requested.undefined\"", responseBody);
    }

    @Test
    public void getAllPostsByUserPath(){
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
        List<Map> authorList = responseBody.jsonPath().getList("author", Map.class);

        SoftAssertions softAssertions = new SoftAssertions();

        for (int i=0; i < titleList.size(); i++){
            softAssertions.assertThat(titleList.get(i)).as("Item number " + i).contains("test");
            softAssertions.assertThat(authorList.get(i).get("username"))
                    .as("Item number " + i).isEqualTo(USER_NAME);
        }

        softAssertions.assertAll();
    }

    @Test
    public void getAllPostsByUserScheme(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(POST_BY_USER,USER_NAME)
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("response.json"));


    }

    @Test
    public void getPrivatExchangeCoursId(){
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(CURRENCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        logger.info(responseBody.length);
        logger.info(responseBody[0].getCcy());
        logger.info(responseBody[0].getBase_ccy());

        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD","UAH"),
                new CurrencyDTO("EUR","UAH"),
                new CurrencyDTO("RUR","UAH"),
                new CurrencyDTO("BTC","USD")
        };

        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();

        for(int i = 0; i< expectedCurrencyDTO.length; i++){
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i],"buy", "sale");
        }
        softAssertions.assertAll();
    }

    @Test
    public void getPrivatExchangeCoursIdScheme(){
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(CURRENCY_EXCHANGE)
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privatResponse.json"));
    }










}