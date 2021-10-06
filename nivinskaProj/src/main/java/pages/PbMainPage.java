package pages;

import api.CcyDTO;
import api.EndPoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import weka.core.Utils;

import java.text.DecimalFormat;

import static io.restassured.RestAssured.given;

public class PbMainPage extends ParentPage {
    DecimalFormat decimalFormat = new DecimalFormat("#.#");

    public PbMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openPbMainPage() {
        try {
            webDriver.get("https://privatbank.ua");
            Util.waitABit(3);
            logger.info("PbMain page was opened");
        } catch (Exception e) {
            logger.error("Can not work with PbMainPage" + e);
            Assert.fail("Can not work with PbMainPage");
        }
    }

    public Double getCurrencyCurrentBuyExchangeViaAPIAndSave(String currency) {
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

        Double currency_buy = null;
        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy() == currency) {
                currency_buy = Double.parseDouble(responseBody[i].getBuy());
            }
            break;
        }
        return currency_buy;
    }

    public Double getCurrencyCurrentSellExchangeViaAPIAndSave(String currency) {
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

        Double currency_sell = null;
        for (int i = 0; i < responseBody.length; i++) {
            if (responseBody[i].getCcy() == currency) {
                currency_sell = Double.parseDouble(responseBody[i].getSale());
            }
            break;
        }
        return currency_sell;
    }

    public Double getCurrencyCurrentBuyExchangeFromWebsiteAndSave(String currency) {
        WebElement currency_buy_element = webDriver.findElement(By.xpath(".//*[@id='" + currency + "_buy']"));
        Util.waitABit(3);
        Double currency_buy = Double.parseDouble(currency_buy_element.getText());
        currency_buy = Utils.roundDouble(currency_buy, 1);
        return currency_buy;
    }


    public Double getCurrencyCurrentSellExchangeFromWebsiteAndSave(String currency) {
        WebElement currency_sell_element = webDriver.findElement(By.xpath(".//*[@id='" + currency + "_sell']"));
        Util.waitABit(3);
        Double currency_sell = Double.parseDouble(currency_sell_element.getText());
        currency_sell = Utils.roundDouble(currency_sell, 1);
        return currency_sell;
    }
}
