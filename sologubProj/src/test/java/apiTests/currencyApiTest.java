package apiTests;

import api.DTO.CurrencyDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import static api.EndPoints.*;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class currencyApiTest {
    @Test
    public void getCurrencyData() {
        CurrencyDTO[] responseBody = given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid", "5")
                .when()
                        .get(PRIVATE_BANK_CURRENCY_URL)
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

        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();

        for (int i = 0; i < responseBody.length; i++) {
            System.out.println("Курс " + responseBody[i].getCcy() + " к " + responseBody[i].getBase_ccy()
            + " покупки " + responseBody[i].getBuy() + " и продажи " + responseBody[i].getSale());
        }
    }

    @Test
    public void getCurrencyDataByScheme() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
        .when()
                .get(PRIVATE_BANK_CURRENCY_URL)
       .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("privatbank_response.json"));
    }
}
