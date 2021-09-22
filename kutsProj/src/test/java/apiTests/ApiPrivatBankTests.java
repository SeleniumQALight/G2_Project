package apiTests;

import api.CurrencyDTO;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.EXCHANGE_RATES;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllExchangeRates(){
        CurrencyDTO[] responseBody = given()
                .contentType(JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
            .when()
                .get(EXCHANGE_RATES)
            .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            logger.info("\n" + responseBody[i].getCcy() + " to UAH exchange rate is: \n buy = "
                    + responseBody[i].getBuy() + "\n sale = " + responseBody[i].getSale());
        }

        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH", "26.45000", "26.85000"),
                new CurrencyDTO("EUR", "UAH", "30.90000", "31.50000"),
                new CurrencyDTO("RUR", "UAH", "0.35000", "0.38000"),
                new CurrencyDTO("BTC", "USD", "41205.0704", "45542.4462")
        };

        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }

    @Test
    public void getAllExchangeRatesSchema(){
        given()
                .contentType(JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
        .when()
                .get(EXCHANGE_RATES)
        .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivatBankExchangeRates.json"));
    }
}
