package apiTests;

import apiForHomeWork.CurrencyDTO;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

import static apiForHomeWork.EndPointsForHomeWork.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiTestForHomeWork1 {
    Logger logger = Logger.getLogger(getClass());


    @Test
    public void getCurrencyRate(){
         Response response = given()
                .contentType(ContentType.JSON)
                .log().all().queryParam("json").queryParam("exchange").queryParam("coursid","5")
        .when()
                .get(baseUrlPrivatBank)

        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();
        CurrencyDTO[] responseBody = response
                .as(CurrencyDTO[].class);


        logger.info(responseBody.length);
        logger.info(responseBody[2].getCcy());
        logger.info(responseBody[2].getBase_ccy());
        logger.info(responseBody[2].getBuy());
        logger.info(responseBody[2].toString());

            Assert.assertEquals("Exchange", "RUR", responseBody[2].getCcy());
            Assert.assertEquals("Exchange", "UAH", responseBody[2].getBase_ccy());



        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");

        }
        softAssertions.assertThat(response.getTime()).isLessThan(2000);
        softAssertions.assertAll();

        logger.info(response.getTime());
        logger.info(response.getTimeIn(TimeUnit.SECONDS));
        logger.info(response.time());
        logger.info(response.timeIn(TimeUnit.MILLISECONDS));




    }

    @Test
    public void getALLPostByUserSchema(){
        given()
                .contentType(ContentType.JSON)
                .log().all().queryParam("json").queryParam("exchange").queryParam("coursid","5")
                .when()
                .get(baseUrlPrivatBank)
                .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responseHW.json"));
    }
}



