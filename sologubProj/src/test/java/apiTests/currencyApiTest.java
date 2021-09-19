package apiTests;

import api.DTO.CurrencyDTO;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;
import javax.ws.rs.core.UriBuilder;
import static io.restassured.RestAssured.given;
import static api.EndPoints.privatBankUrl;

public class currencyApiTest {
    @Test
    public void CurrencyApiTest() {
        CurrencyDTO[] responseBody = given()
                        .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get((UriBuilder.fromUri(privatBankUrl).queryParam("coursid", "5")
                                .build()).toString())
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
}
