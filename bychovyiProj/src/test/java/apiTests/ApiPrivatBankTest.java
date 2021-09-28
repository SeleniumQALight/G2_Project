package apiTests;

import api.CurrencyDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.CASH_RATE;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCashRatePrivatBank(){
        CurrencyDTO[] responseBody =
                given()
                        .queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid","5")
                        .log().all()
                .when()
                        .get(CASH_RATE)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(CurrencyDTO[].class);

        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals(responseBody.length, 4);

        SoftAssertions softAssertions =new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();

        for (int i = 0; i < responseBody.length; i++) {
            logger.info("Курс " + responseBody[i].getCcy() + " к " + responseBody[i].getBase_ccy() + " покупки " + responseBody[i].getBuy() + " и продажи " + responseBody[i].getSale());
        }
    }

    @Test
    public void getCashRatePrivatBankShema(){
        given()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid","5")
                .log().all()
        .when()
                .get(CASH_RATE)
        .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsCurrencyPrivatBank.json"));

    }
}
