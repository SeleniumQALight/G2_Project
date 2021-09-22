package API;

import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static API.PrivatEndpoints.*;
import static io.restassured.RestAssured.given;

public class CurrencyAPI {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCurrency() {
        CurrencyDTO[] response = given()
                .contentType(ContentType.JSON).queryParam("json").queryParam("exchange").queryParam("coursid", 5)
                .log().all()
        .when()
                .get(Currency)
        .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        CurrencyDTO[] ExpectedCurrencyDTO = {
            new CurrencyDTO("USD", "UAH"),
            new CurrencyDTO("EUR", "UAH"),
            new CurrencyDTO("RUR", "UAH"),
            new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals(response.length, ExpectedCurrencyDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < ExpectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(ExpectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(response[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}
