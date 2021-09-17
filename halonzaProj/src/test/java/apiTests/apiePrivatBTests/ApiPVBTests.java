package apiTests.apiePrivatBTests;

import api.apiPrivatB.CurrencyDTO;
import api.apiPrivatB.EndPoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPVBTests extends ApiPVBBaseTest{

    @Test
    public void getAllCurrencyRate() {
        final String[] expectedCurrencyArray = {"EUR", "USD", "RUR", "BTC"};
        final String[] expectedBasicCurrencyArray = {"UAH", "USD"};
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPoints.privatBankApiExchangeRate)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responseBody.length).isEqualTo(expectedCurrencyArray.length);
        for (int i = 0; i < responseBody.length; i++) {
            System.out.println("Курс " + responseBody[i].getCcy() +
                    " к " + responseBody[i].getBase_ccy() +
                    " покупки " + responseBody[i].getBuy() +
                    " и продажи " + responseBody[i].getSale());
            softAssertions.assertThat(responseBody[i].getCcy()).isIn(expectedCurrencyArray);
            if (responseBody[i].getCcy().equalsIgnoreCase(expectedCurrencyArray[3])) {
                softAssertions.assertThat(responseBody[i].getBase_ccy())
                        .as(responseBody[i].getBase_ccy()+ " has incorrect basicCurrency " + expectedBasicCurrencyArray[1])
                        .isEqualTo(expectedBasicCurrencyArray[1]);
            } else {
                softAssertions.assertThat(responseBody[i].getBase_ccy())
                        .as(responseBody[i].getBase_ccy()+ " has incorrect basicCurrency " + expectedBasicCurrencyArray[0])
                        .isEqualTo(expectedBasicCurrencyArray[0]);
            }
        }
        softAssertions.assertAll();
    }


    @Test
    public void getAllCurrencyRateBySchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(EndPoints.privatBankApiExchangeRate)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("responseCashExchangePVB.json"));
    }
}
