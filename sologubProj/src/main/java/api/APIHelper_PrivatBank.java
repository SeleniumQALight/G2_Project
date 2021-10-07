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
        String buy = "";

        if (currencyType.equals("USD")) {
            buy = getCurrencyValues()[0].getBuy();
        }
        else if (currencyType.equals("EUR")) {
            buy = getCurrencyValues()[1].getBuy();
        }
        else if (currencyType.equals("RUR")) {
            buy = getCurrencyValues()[2].getBuy();
        }

        double trimmedCurrencyValue = roundTwoDecimals(buy);
        return trimmedCurrencyValue;
    }

    public double getCurrencySellValueByAPI(String currencyType) {
        String saleValue = "";

        if (currencyType.equals("USD")) {
            saleValue = getCurrencyValues()[0].getSale();
        }
        else if (currencyType.equals("EUR")) {
            saleValue = getCurrencyValues()[1].getSale();
        }
        else if (currencyType.equals("RUR")) {
            saleValue = getCurrencyValues()[2].getSale();
        }

        double trimmedCurrencyValue = roundTwoDecimals(saleValue);
        return trimmedCurrencyValue;
    }
}
