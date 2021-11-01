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
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

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
    public void compareUiCourse(String ccy, String base_ccy, String apiBuy, String apiSale) {
//        ResponseBody responseBody =
//        given()
//                //.spec(uiRequestSpecification)
//                //.when()
//                .get(EndPoints.UI_GET_EXCHANGE_COURSE)
//                .then()
//                .statusCode(200)
//                .log().all()
//                .extract().response().getBody();
//        XmlPath path = responseBody.htmlPath();

        // //*[@id="app"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]/div[2]/div[1]/div[2]/div[1]
        // //*[@id="app"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]  table
        WebDriver driver = new ChromeDriver();
        driver.get(EndPoints.UI_GET_EXCHANGE_COURSE);
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact-number")));
        WebElement table = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/section/div/div[2]/div[1]/div/div[2]/div[3]/div[2]"));
        List<WebElement> rows = table.findElements(By.xpath("./div[contains(@class, 'root_')]"));
        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            WebElement title = row.findElement(By.xpath("./div/div[2]/div"));
            String currencyTitle = title.getText();
            if(currencyTitle == ccy){
                WebElement buy = row.findElement(By.xpath("./div/div[3]/div"));
                String buyValue = buy.getText();
                Assert.assertNotEquals("Api Currency buy is not the same as UI ", buyValue, apiBuy);

                WebElement sale = row.findElement(By.xpath("./div/div[5]/div"));
                String saleValue = sale.getText();
                Assert.assertNotEquals("Api Currency sale is not the same as UI ", saleValue, apiSale);
                return;
            }
        }
        Assert.assertTrue("Api Currency was not found in UI ", true);
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
