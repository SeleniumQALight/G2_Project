package pages;

import api.CurrencyDTO;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static libs.TestData.currencyDTOFromAPI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


public class PBLandingPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    private String baseUrlPB = "https://privatbank.ua/";
    private Logger logger = Logger.getLogger(getClass());

    public PBLandingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        wait = new WebDriverWait(webDriver, 10);
        PageFactory.initElements(this.webDriver, this);
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrlPB);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void getCurrencyCourseFromLendingPageAndCompareItWithAPIData(String currencyCode) {
        Assert.assertEquals("Invalid page ", baseUrlPB, webDriver.getCurrentUrl());
        CurrencyDTO actualCurrencyDTO = new CurrencyDTO(
                currencyCode,
                wait.until(visibilityOfElementLocated(By.id(currencyCode + "_buy"))).getText().trim(),
                wait.until(visibilityOfElementLocated(By.id(currencyCode + "_sell"))).getText().trim()
        );

        assertThat(actualCurrencyDTO).isEqualToIgnoringGivenFields(currencyDTOFromAPI, "base_ccy");
    }
}
