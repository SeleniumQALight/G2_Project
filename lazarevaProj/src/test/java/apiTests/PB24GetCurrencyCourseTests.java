package apiTests;

import api.PrivatGetResponseDTO;
import io.restassured.http.ContentType;
import libs.TestData;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Test;

import static api.EndPoints.getPB24Url;
import static io.restassured.RestAssured.*;

public class PB24GetCurrencyCourseTests {

    Logger logger = Logger.getLogger(getClass());

    @Test
    public void sendGetAndValidateStatusCode(){
                given()
                       .contentType(ContentType.JSON)
                        .log().all()
                .when()
                        .get(getPB24Url+"5")
                .then()
                        .statusCode(200)
                        .log().all();

    }

         // Map currency DTO, print data from response
    @Test
   public void allCurrenciesCourseDTO() {
        PrivatGetResponseDTO[] responseBody =
        given()
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .get(getPB24Url + "5")
        .then()
                .statusCode(200)
                .log().all()
                .extract().as(PrivatGetResponseDTO[].class);
        logger.info(responseBody.length);
        logger.info(responseBody[0]);
        logger.info(responseBody[1]);
        logger.info(responseBody[2]);
        logger.info(responseBody[3]);
    }

         // 5 Validation of the ccy & ccy и base_ccy (fields buy и sale are ignoring )
         @Test
         public void validationOfTheCcyAndBaseCcy() {
             PrivatGetResponseDTO[] responseBody =
                     given()
                             .contentType(ContentType.JSON)
                             .log().all()
                     .when()
                             .get(getPB24Url + "5")
                     .then()
                             .statusCode(200)
                             .log().all()
                             .extract().as(PrivatGetResponseDTO[].class);
             //Create an Object to compare ER & AR
             PrivatGetResponseDTO[] expectedPrivatGetResponseDTO = {
                     new PrivatGetResponseDTO("USD", "UAH", TestData.USD_UAH_BUY, TestData.USD_UAN_SALE),
                     new PrivatGetResponseDTO("EUR", "UAH", TestData.EUR_UAH_BUY, TestData.EUR_UAH_SALE),
                     new PrivatGetResponseDTO("RUR", "UAH", TestData.RUR_UAH_BUY, TestData.RUR_UAH_SALE),
                     new PrivatGetResponseDTO("BTC", "USD", TestData.BTC_USD_BUY, TestData.BTC_USD_SALE)
             };

             Assert.assertEquals(responseBody.length, expectedPrivatGetResponseDTO.length);
             SoftAssertions softAssertions = new SoftAssertions();

             for (int i = 0; i < expectedPrivatGetResponseDTO.length; i++) {
                 //fields for ignoring
                 softAssertions.assertThat(expectedPrivatGetResponseDTO[i])
                         .isEqualToIgnoringGivenFields(responseBody[i], "buy", "sale");

             }
             softAssertions.assertAll();
             logger.info(responseBody.length);
             logger.info(responseBody[0]);
             logger.info(responseBody[1]);
             logger.info(responseBody[2]);
             logger.info(responseBody[3]);
         }

    //Current tests does not relate to HW1
    @Test
    public void validationOfTheBuyAndSale() {
        PrivatGetResponseDTO[] responseBody =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get(getPB24Url + "5")
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().as(PrivatGetResponseDTO[].class);
        //Create an Object to compare ER & AR
        PrivatGetResponseDTO[] expectedPrivatGetResponseDTO= {
                new PrivatGetResponseDTO("USD", "UAN", TestData.USD_UAH_BUY,TestData.USD_UAN_SALE),
                new PrivatGetResponseDTO("EUR", "UAN", TestData.EUR_UAH_BUY,TestData.EUR_UAH_SALE),
                new PrivatGetResponseDTO("RUR", "UAN", TestData.RUR_UAH_BUY,TestData.RUR_UAH_SALE),
                // pay attention - BTC_USD can be failed due to dynamic changes
                new PrivatGetResponseDTO("BTC", "USD", TestData.BTC_USD_BUY,TestData.BTC_USD_SALE)
        };

        Assert.assertEquals(responseBody.length, expectedPrivatGetResponseDTO.length);
        SoftAssertions softAssertions = new SoftAssertions();

        for (int i = 0; i < expectedPrivatGetResponseDTO.length; i++) {
            //fields for ignoring
            softAssertions.assertThat(expectedPrivatGetResponseDTO[i])
                    .isEqualToIgnoringGivenFields(responseBody[i], "ccy","base_ccy");

        }

        softAssertions.assertAll();

        Assert.assertEquals(TestData.EUR_UAH_BUY, responseBody[1].getBuy());
        logger.info(responseBody.length);
        logger.info(responseBody[0]);
        logger.info(responseBody[1]);
        logger.info(responseBody[2]);
        logger.info(responseBody[3]);
    }



}
