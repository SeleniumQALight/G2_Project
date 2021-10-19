package pages;

import libs.DriverHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankPage extends ParentPage {
    String privatBankUrl = "https://privatbank.ua";
    Logger logger = Logger.getLogger(getClass());

    String currencyBuyValue = ".//*[@id='%s_buy']";

    String currencySellValue = ".//*[@id='%s_sell']";

    public PrivatBankPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return null;
    }

    public void openPrivatBankPage() {
        try {
            DriverHelper.getWebDriver().get(privatBankUrl);
            logger.info("PrivatBank page is opened");
        } catch (Exception e){
            logger.error("Cannot work with a PrivatBank page" + e);
            Assert.fail("Cannot work with a PrivatBank");
        }
    }

    public double getCurrencyBuyValueInUI(String currency) {
        if (currency.equals("RUR")) {
            currency = "RUB";
        }
        String currencyUIValue = webDriver.findElement(By.xpath((String.format(currencyBuyValue, currency)))).getText();
        double currencyUIDoubleValue = Double.parseDouble(currencyUIValue);
        return currencyUIDoubleValue;
    }

    public double getCurrencySellValueInUI(String currency) {
        if (currency.equals("RUR")) {
            currency = "RUB";
        }
        String currencyUIValue = webDriver.findElement(By.xpath((String.format(currencySellValue, currency)))).getText();
        double currencyUIDoubleValue = Double.parseDouble(currencyUIValue);
        return currencyUIDoubleValue;
    }
}
