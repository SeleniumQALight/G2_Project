package api;

import api.DTO.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import java.text.DecimalFormat;
import static api.EndPoints.PRIVATE_BANK_CURRENCY_URL;
import static io.restassured.RestAssured.given;

public class APIHelper_PrivatBank {
    Logger logger = Logger.getLogger(getClass());

    public CurrencyDTO[] getCurrencyValues() {
        return given()
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
    }

    double roundTwoDecimals(String currencyValue) {
        double currency = Double.parseDouble(currencyValue);
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(currency));
    }

    public double getCurrencyBuyValueByAPI(String currencyType) {
        double buyValue = 0;
        for (int j = 0; j < getCurrencyValues().length; j++) {
            if (getCurrencyValues()[j].getCcy().equals(currencyType)) {
                buyValue = roundTwoDecimals(getCurrencyValues()[j].getBuy());
            }
        }
        return buyValue;
    }

    public double getCurrencySellValueByAPI(String currencyType) {
        double sellValue = 0;
        for (int j = 0; j < getCurrencyValues().length; j++) {
            if (getCurrencyValues()[j].getCcy().equals(currencyType)) {
                sellValue = roundTwoDecimals(getCurrencyValues()[j].getSale());
            }
        }
        return sellValue;
    }
}
