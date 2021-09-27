package apiTest;

import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Test;

import static api.EndPointPrivatBank.GET_API_CURRENCY;
import static io.restassured.RestAssured.given;

public class ApiPrivatBankTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllCurrencies() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json", "")
                .queryParam("exchange", "")
                .queryParam("courseId", "5")
                .when()
                .get(GET_API_CURRENCY)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);
    }
}