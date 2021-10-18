package pages;

import api.privatbankApi.CurrencyDTO;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PrivatBankMainPage extends ParentPage {
    private final String baseUrlPB = "https://privatbank.ua/";

    String currencyLocatorBuy = ".//td[@id='%s_buy']";
    String currencyLocatorSell = ".//td[@id='%s_sell']";


    public PrivatBankMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "";
    }

    @Step
    public void openPrivatBankPage() {
        try {
            webDriver.get(baseUrlPB);
            logger.info("PrivatBank Page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with PrivatBank Page:" + e);
            Assert.fail("Cannot work with PrivatBank Page");
        }
    }

    @Step
    public CurrencyDTO getCurrencyFromPBPageByCode(String currencyCode) {
        try {
            WebElement currBuy = webDriver.findElement(By.xpath(String.format(currencyLocatorBuy, currencyCode)));
            logger.info("BUY exchange rate for required currency '" + currencyCode
                    + "' was found on page: " + currBuy.getText());
            WebElement currSell = webDriver.findElement(By.xpath(String.format(currencyLocatorSell, currencyCode)));
            logger.info("SELL exchange rate for required currency '" + currencyCode
                    + "' was found on page: " + currSell.getText());
            return new CurrencyDTO(currencyCode, currBuy.getText().trim(), currSell.getText().trim());
        } catch (Exception e) {
            logger.error("Element is present in API response but absent on page: " + e);
            Assert.fail("Element is present in API response but absent on page");
            return null;
        }
    }

    public void compareCurrenciesFromApiAndSite(CurrencyDTO currencyAPI, CurrencyDTO currencySite) {
        try {
            Assert.assertEquals("Buy exchange rate API vs UI: ",
                    Double.parseDouble(currencyAPI.getBuy()),
                    Double.parseDouble(currencySite.getBuy()),
                    0.01);
            Assert.assertEquals("Sell exchange rate API vs UI: ",
                    Double.parseDouble(currencyAPI.getSale()),
                    Double.parseDouble(currencySite.getSale()),
                    0.01);
        } catch (RuntimeException e) {
            logger.error("Cannot convert exchange rate to double: " + e);
            Assert.fail("Cannot convert exchange rate to double");
        }
    }
}
