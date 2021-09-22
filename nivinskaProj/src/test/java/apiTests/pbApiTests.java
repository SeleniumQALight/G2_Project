package apiTests;

import api.CcyDTO;
import api.EndPoints;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class pbApiTests {
    Logger logger = Logger.getLogger(getClass());

    @Test
    public void getCcyExchangeByPb() {
        CcyDTO[] responseBody = given()
                .contentType(ContentType.JSON)
                .queryParam("json")
                .queryParam("exchange")
                .queryParam("coursid", "5")
                .log().all()
                .when()
                .get(EndPoints.CCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response().as(CcyDTO[].class);

        for (int i = 0; i < responseBody.length; i++) {
            System.out.println(
                    "Курс " + responseBody[i].getCcy()
                            + " к " + responseBody[i].getBase_ccy()
                            + " покупки " + responseBody[i].getBuy()
                            + " продажи " + responseBody[i].getSale());
        }
    }

    @Test
    public void getCcyExchangeByPbSchema() {
        given()
                .contentType(ContentType.JSON)
                .log().all()
                .when()
                .get(EndPoints.CCY_EXCHANGE)
                .then()
                .statusCode(200)
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("ccyPbResponse.json"));
    }
}