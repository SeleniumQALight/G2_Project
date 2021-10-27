package apiPrivateBank;

import apiPrivateBank.CurrencyDTO;
import apiPrivateBank.EndPoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;

import static apiPrivateBank.EndPoints.API_GET_EXCHANGE_COURSE;
import static io.restassured.RestAssured.given;

public class ApiHelper {
    Logger logger = Logger.getLogger(getClass());

    RequestSpecification apiRequestSpecification = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    public  CurrencyDTO[] getApiCourseRequest(int courseId){
        return given()
                    .spec(apiRequestSpecification)
                    .when()
                    .get(EndPoints.API_GET_EXCHANGE_COURSE, courseId)
                    .then()
                    .statusCode(200)
                    .log().all()
                    .extract()
                    .response().as(CurrencyDTO[].class);
    }
    public void getUiCourseRequest(){
        ResponseBody responseBody =
        given()
                //.spec(uiRequestSpecification)
                //.when()
                .get(EndPoints.UI_GET_EXCHANGE_COURSE)
                .then()
                .statusCode(200)
                .log().all()
                .extract().response().getBody();
        XmlPath path = responseBody.htmlPath();

        // //*[@id="app"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]/div[1]
        // //*[@id="app"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]
    }
    public CurrencyDTO extractCurrency(CurrencyDTO[] currency, String testCcy, String testBaseCcy) {
        for (int i = 0; i < currency.length; i++) {
            if(currency[i].getCcy().equals(testCcy) && currency[i].getBaseCcy().equals(testBaseCcy)){
                System.out.println(currency[i].getBuy());
                return currency[i];
            }
        }
        return null;
    }
}
