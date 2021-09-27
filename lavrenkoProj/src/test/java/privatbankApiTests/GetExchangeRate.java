package privatbankApiTests;



        import privatbankApi.CurrencyDTO;
        import org.apache.log4j.Logger;
        import org.assertj.core.api.SoftAssertions;
        import org.junit.Assert;
        import org.junit.Test;

        import static privatbankApi.Endpoints.EXCHANGE_URL;
        import static io.restassured.RestAssured.given;

public class GetExchangeRate {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getExchangeRateTest() {
        CurrencyDTO[] responseBody =
        given()
                .queryParam("coursid", "5")
                .log().all()
        .when()
                .get(EXCHANGE_URL)
        .then()
                .statusCode(200)
                .extract().body().as(CurrencyDTO[].class);


        for (CurrencyDTO currency :
                responseBody) {
            logger.info(currency);
        }

        CurrencyDTO[] expectedResponseBody = {
                new CurrencyDTO("USD", "UAH"),
                new CurrencyDTO("EUR", "UAH"),
                new CurrencyDTO("RUR", "UAH"),
                new CurrencyDTO("BTC", "USD")
        };

        Assert.assertEquals("Exchange amount:", expectedResponseBody.length, responseBody.length);

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedResponseBody.length; i++) {
            softAssertions.assertThat(responseBody[i])
                    .isEqualToIgnoringGivenFields(expectedResponseBody[i], "buy", "sale");
        }
        softAssertions.assertAll();
    }
}