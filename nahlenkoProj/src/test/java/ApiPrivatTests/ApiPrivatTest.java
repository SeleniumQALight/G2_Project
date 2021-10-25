package ApiPrivatTests;

import api.CurrencyDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPointsPrivat.GET_Currency;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ApiPrivatTest {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getAllPosts() {
        CurrencyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .log().all().queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .when()
                .get(GET_Currency)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CurrencyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) { //вывести в консоль "Курс RUB к UAH покупки 0,28000 и продажи 0,32000"(имя валют и курсы взять с респонса) и так для каждой валюты
            System.out.println("Курс " + responseBody[i].getCcy() + " к " + responseBody[i].getBase_ccy() + " покупки " + responseBody[i].getBuy() + " и " + responseBody[i].getSale() + " продажи");

        }
        CurrencyDTO[] expectedCurrencyDTO = {
                new CurrencyDTO("USD", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("EUR", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("RUR", "UAH", "26.50000", "26.90000"),
                new CurrencyDTO("BTC", "USD", "26.50000", "26.90000"),
        };
        Assert.assertEquals(responseBody.length, expectedCurrencyDTO.length);//сравнение наполнения
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedCurrencyDTO.length; i++) {
            softAssertions.assertThat(expectedCurrencyDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");//игнорим для проверки поля buy и sale

        }
        softAssertions.assertAll();
    }

    @Test
        public void getAllSchema () {//schema validation
            given()
                    .log().all().queryParam("json")
                    .queryParam("exchange")
                    .queryParam("coursid", "5")
                    .when()
                    .get(GET_Currency)
                    .then()
                    .statusCode(200).log().all()
                    .assertThat().body(matchesJsonSchemaInClasspath("responsePrivat.json"));
        }
    }


