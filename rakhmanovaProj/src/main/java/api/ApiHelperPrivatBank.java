package api;

import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;
import org.junit.Assert;

import java.text.DecimalFormat;

import static api.EndPoints.CURRENCY_LIST;
import static io.restassured.RestAssured.given;

public class ApiHelperPrivatBank {
    Logger logger = Logger.getLogger(getClass());

    public CurrenciesDTO[] getCurrencyValues() {
        return given()
                .contentType(ContentType.JSON)
                .log().all()
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(CURRENCY_LIST)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrenciesDTO[].class);
    }

    double roundTwoDecimals(String currencyValue) {
        double currency = Double.parseDouble(currencyValue);
        DecimalFormat twoDecimalFormat = new DecimalFormat("#.##");
        return Double.valueOf(twoDecimalFormat.format(currency));
    }

    public double getCurrencyBuyValueViaAPI(String currencyType) {
        double buyValue = 0;
        for (int i = 0; i < getCurrencyValues().length; i++) {
            if (getCurrencyValues()[i].getCcy().equals(currencyType)) {
                buyValue = roundTwoDecimals(getCurrencyValues()[i].getBuy());
            }
        }
        return buyValue;
    }

    public double getCurrencySellValueViaAPI(String currencyType) {
        double sellValue = 0;
        for (int i = 0; i < getCurrencyValues().length; i++) {
            if (getCurrencyValues()[i].getCcy().equals(currencyType)) {
                sellValue = roundTwoDecimals(getCurrencyValues()[i].getSale());
            }
        }
        return sellValue;
    }
}
