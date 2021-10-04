package apiTest;

import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPointPrivatBank.GET_API_CURRENCY;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatBankTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllCurrencies() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(GET_API_CURRENCY)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        logger.info(responseBody.length);
        logger.info(responseBody[0].getCcy());
        boolean hasCourse = false;
        for (CurrencyDTO currencyDTO : responseBody) {
            logger.info(currencyDTO.getCcy());
            if ((currencyDTO.getCcy().equals("RUR")) & (currencyDTO.getBase_ccy().equals("UAH"))) {
                hasCourse = true;
                logger.info("currency exchange course: ");
                logger.info(String.format("buy: %s", currencyDTO.getBuy()));
                logger.info(String.format("sale: %s", currencyDTO.getSale()));
            }
        }
        Assert.assertTrue("Course UAH/RUR not found", hasCourse);
    }
}
