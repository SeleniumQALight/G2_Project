package pages;

import api.CurrencyDTO;
import io.qameta.allure.Step;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PrivatBankExchangePage  {
    final Logger logger = Logger.getLogger(getClass());

    private final WebDriver webDriver;
    String currencyLocatorBuy = ".//td[@id='%s_buy']";
    String currencyLocatorSale = ".//td[@id='%s_sell']";
    String baseUiUrlPrivat="https://privatbank.ua/ru";

    public PrivatBankExchangePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
@Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUiUrlPrivat);
            logger.info("Exchange page was opened");
        } catch (Exception e) {
            logger.error("Error on ExchangePage" + e);
            Assert.fail("Error on ExchangePage");
        }
    }
@Step
public CurrencyDTO getCurrencyFromPrivatByCode(String currency) {
    try {
        WebElement currBuy = webDriver.findElement(By.cssSelector(String.format(currencyLocatorBuy, currency)));
        logger.info("BUY exchange rate for required currency '" + currency
                + "' was found on page: " + currBuy.getText());
        WebElement currSell = webDriver.findElement(By.cssSelector(String.format(currencyLocatorSale, currency)));
        logger.info("Sale exchange rate for required currency '" + currency
                + "' was found on page: " + currSell.getText());
        return new CurrencyDTO(currency, currBuy.getText().trim(), currSell.getText().trim());
    } catch (Exception e) {
        logger.error("Element is present in API response but absent on page: " + e);
        Assert.fail("Element is present in API response but absent on page");
        return null;
    }
}
    public void compareCurrencyFromApiAndSite(CurrencyDTO currencyAPI, CurrencyDTO currencySite) {
        try {
            Assert.assertEquals("Buy exchange rate API to UI: ",
                    Double.parseDouble(currencyAPI.getBuy()),
                    Double.parseDouble(currencySite.getBuy()),
                    0.01);
            Assert.assertEquals("Sale exchange rate API to UI: ",
                    Double.parseDouble(currencyAPI.getSale()),
                    Double.parseDouble(currencySite.getSale()),
                    0.01);
        } catch (RuntimeException e) {
            logger.error("Error of converting exchange rate to double: " + e);
            Assert.fail("Error of converting exchange rate to double");
             }
        }
    }

