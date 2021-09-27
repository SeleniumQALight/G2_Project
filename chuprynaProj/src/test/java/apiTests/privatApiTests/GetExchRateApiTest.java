package apiTests.privatApiTests;

import api.privatbankApi.CurrencyDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.privatbankApi.PrivatEndPoints.GET_EXCHANGE_RATE_URL;
import static io.restassured.RestAssured.given;

public class GetExchRateApiTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRateTest() {
        CurrencyDTO[] responseBody = given()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(GET_EXCHANGE_RATE_URL)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(CurrencyDTO[].class);


        for (CurrencyDTO currency :
                responseBody) {
            logger.info(currency.printPrettyCurrency());
        }

        CurrencyDTO[] expectedResponseBody = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals("Number of items:", expectedResponseBody.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResponseBody.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResponseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
