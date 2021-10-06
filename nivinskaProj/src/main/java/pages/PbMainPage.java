package pages;

import api.CcyDTO;
import api.EndPoints;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import libs.TestData;
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

    public void getCurrencyCurrentExchangeFromWebsite(String currency) {
        WebElement currency_buy_element = webDriver.findElement(By.xpath(".//*[@id='" + currency + "_buy']"));
        WebElement currency_sell_element = webDriver.findElement(By.xpath(".//*[@id='" + currency + "_sell']"));

        Util.waitABit(3);

        try {
            TestData.setCurrency_buy_web(Utils.roundDouble(Double.parseDouble(currency_buy_element.getText()), 1));
            logger.info("Currency " + currency + " buy exchange on website is " + TestData.getCurrency_buy_api());
            TestData.setCurrency_sell_web(Utils.roundDouble(Double.parseDouble(currency_sell_element.getText()), 1));
            logger.info("Currency " + currency + " sell exchange on website is " + TestData.getCurrency_sell_api());
        } catch (Exception e) {
            logger.error("Elements was not found on website Main page" + e);
            Assert.fail("Elements was not found on website Main page" + e);
        }

    }

    public void compareCurrentCurrencyExchangeViaAPIAndFromWebsite(String currency) {
        try {
            Assert.assertEquals("Currency buy exchange is not correct", TestData.getCurrency_buy_api(), TestData.getCurrency_buy_web(), 0);
            Assert.assertEquals("Currency sell exchange is not correct", TestData.getCurrency_sell_api(), TestData.getCurrency_sell_web(), 0);
        } catch (Exception e) {
            logger.error("Is not possible to compare values" + e);
            Assert.fail("Is not possible to compare values" + e);
        }
    }
}
