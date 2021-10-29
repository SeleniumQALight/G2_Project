package api;

import static api.EndPoints.EXCHANGE_RATES;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static libs.TestData.*;

public class PrivatBankApiHelper {

    private Double value_double, scale, result;

    public void getAllExchangeRates() {
        all_exchange_rates =
                given()
                        .contentType(JSON)
                        .log().all()
                        .queryParam("json")
                        .queryParam("exchange")
                        .queryParam("coursid", "5")
                .when()
                        .get(EXCHANGE_RATES)
                .then()
                        .statusCode(200)
                        .log().all()
                        .extract()
                        .response().as(CurrencyDTO[].class);
    }

    public void getExchangeRatesForCurrency (String currency) {
        for (int i = 0; i < all_exchange_rates.length; i++) {
            if (all_exchange_rates[i].getCcy().equalsIgnoreCase(currency)) {
                currency_buy_api = convertStringToDouble(all_exchange_rates[i].getBuy());
                currency_sale_api = convertStringToDouble(all_exchange_rates[i].getSale());
            }
        }
    }

    public Double convertStringToDouble(String value) {
        value_double = Double.parseDouble(value);
        scale = Math.pow(10, 2);
        result = Math.ceil(value_double * scale) / scale;
        return result;
    }
}
