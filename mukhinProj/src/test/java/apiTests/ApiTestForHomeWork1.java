package apiTests;

import apiForHomeWork.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static apiForHomeWork.EndPointsForHomeWork.*;
import static io.restassured.RestAssured.given;

public class ApiTestForHomeWork1 {
    Logger logger = Logger.getLogger(getClass());


    @Test
    public void getCurrencyRate(){
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all().queryParam("json").queryParam("exchange").queryParam("coursid","5")
        .when()
                .get(baseUrlPrivatBank)

        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);


        logger.info(responseBody.length);
        logger.info(responseBody[2].getCcy());
        logger.info(responseBody[2].getBase_ccy());
        logger.info(responseBody[2].getBuy());
        logger.info(responseBody[2].toString());
        for (int i = 0; i < responseBody.length; i++) {
            Assert.assertEquals("Exchange", "EUR", responseBody[i].getCcy());
            Assert.assertEquals("Exchange", "UAH", responseBody[i].getBase_ccy());

        }

        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH", "26.34000", "26.80000"),
                new CurrencyDTO("EUR", "UAH", "30.90000", "31.50000"),
                new CurrencyDTO("RUR", "UAH", "0.35000", "0.38000"),
                new CurrencyDTO("BTC", "USD", "41788.8249", "46187.6485")
        };

        Assert.assertEquals(responseBody.length, expectedCurrencyDTO);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");

        }

        softAssertions.assertAll();

    }


}
