
package api.privatbankApi;

import org.apache.log4j.Logger;
import org.junit.Assert;

import static api.privatbankApi.PrivatEndPoints.GET_EXCHANGE_RATE_URL;
import static io.restassured.RestAssured.given;

public class PrivatApiHelper {
    Logger logger = Logger.getLogger(getClass());

    public CurrencyDTO getCurrencyFromAPIByCode(String currencyCode) {
        CurrencyDTO[] actualCurrencyListFromAPI = given()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(GET_EXCHANGE_RATE_URL)
                .then()
                .log().all()
                .statusCode(200)
                .extract().body().as(CurrencyDTO[].class);

        // hint only for RUB currency
        if (currencyCode.equalsIgnoreCase("RUB")) {
            currencyCode = "RUR";
        }

        CurrencyDTO requiredCurrencyDTO = null;
        for (CurrencyDTO cdto : actualCurrencyListFromAPI) {
            if (cdto.getCcy().equalsIgnoreCase(currencyCode)) {
                requiredCurrencyDTO = cdto;
                logger.info("Required currency DTO from API: " + requiredCurrencyDTO);
                break;
            }
        }
        if (requiredCurrencyDTO == null ) {
            logger.error("Cannot get currency from API with code " + currencyCode);
            Assert.fail("Cannot get currency from API with code " + currencyCode);
        }
        return requiredCurrencyDTO;
    }

}
