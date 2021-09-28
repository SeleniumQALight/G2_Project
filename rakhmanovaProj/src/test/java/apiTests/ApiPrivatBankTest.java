package apiTests;

import api.CurrenciesDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.CURRENCY_LIST;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTest {
    Logger logger = Logger.getLogger(getClass());

    @Test

    public void getAllCurrencies(){
        CurrenciesDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
        .when()
               .get(CURRENCY_LIST)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrenciesDTO[].class)
                ;
        logger.info(responseBody.length);

        CurrenciesDTO[] expectedCurrenciesDTO = {
                new CurrenciesDTO("USD", "UAH"),
                new CurrenciesDTO("EUR", "UAH"),
                new CurrenciesDTO("RUR", "UAH"),
                new CurrenciesDTO("BTC", "USD"),
        };

        Assert.assertEquals(responseBody.length, expectedCurrenciesDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedCurrenciesDTO.length; i++) {
            softAssertions.assertThat(expectedCurrenciesDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();

        for (int i = 0; i < responseBody.length; i++) {
            System.out.println("Rate of " + responseBody[i].getCcy() + " to " + responseBody[i].getBase_ccy()
            + " buy " + responseBody[i].getBuy() + " and sell " + responseBody[i].getSale());
        }
    }

    @Test
    public void getAllCurrenciesBySchema(){
        given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
        .when()
                .get(CURRENCY_LIST)
        .then()
                .statusCode(200).log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));

    }

}
