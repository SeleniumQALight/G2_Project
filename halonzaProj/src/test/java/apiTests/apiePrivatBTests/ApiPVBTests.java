package apiTests.apiePrivatBTests;

import api.apiPrivatB.CurrencyDTO;
import api.apiPrivatB.EndPoints;
import io.restassured.http.ContentType;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ApiPVBTests extends ApiPVBBaseTest{
    @Test
    public void getAllCurrencyRate() {
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
        String[] expectedCurrencyArray = {"EUR", "USD", "RUR", "BTC"};
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(responseBody.length).isEqualTo(expectedCurrencyArray.length);
        for (int i = 0; i < responseBody.length; i++) {
            System.out.println("Курс " + responseBody[i].getCcy() +
                    " к " + responseBody[i].getBase_ccy() +
                    " покупки " + responseBody[i].getBuy() +
                    " и продажи " + responseBody[i].getSale());
            softAssertions.assertThat(responseBody[i].getCcy()).isIn(expectedCurrencyArray);
            if (responseBody[i].getCcy().equalsIgnoreCase(expectedCurrencyArray[3])) {
                softAssertions.assertThat(responseBody[i].getBase_ccy()).isEqualTo("USD");
            } else {
                softAssertions.assertThat(responseBody[i].getBase_ccy()).isEqualTo("UAH");
            }
        }
        softAssertions.assertAll();
    }
}
