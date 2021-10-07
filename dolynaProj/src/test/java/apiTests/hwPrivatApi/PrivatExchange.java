package apiTests.hwPrivatApi;

import api.PrivatTest.CurrencyDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.PrivatTest.EndPointsPrivatBank.BaseUrl;
import static api.PrivatTest.EndPointsPrivatBank.EXCHANGE_RATES;
import static io.restassured.RestAssured.given;

public class PrivatExchange {
    Logger logger = Logger.getLogger(getClass());
    @Test
    public void getExchangeRates(){
        CurrencyDTO[] responseBody = given()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EXCHANGE_RATES)
                .then()
                .statusCode(200)
                .extract().body().as(CurrencyDTO[].class);
        for (CurrencyDTO currency :
        responseBody){
            logger.info(currency.printCurrency());
        }
        CurrencyDTO[] expectedResponseBody = {
                new CurrencyDTO("USD", "UAH"),

                new CurrencyDTO("EUR","UAH"),

        };
        Assert.assertEquals("Items of Number:", expectedResponseBody.length, responseBody.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResponseBody.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResponseBody[i],"buy","sale");

        }
        softAssertions.assertAll();






    }

}
