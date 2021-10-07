package pages;

import libs.DriverHelper;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PrivatBankPage extends ParentPage {
    String privatBankUrl = "https://privatbank.ua";
    Logger logger = Logger.getLogger(getClass());

    @FindBy (xpath=".//*[@id='EUR_buy']")
    private WebElement EURBuyValue;

    @FindBy (xpath=".//*[@id='EUR_sell']")
    private WebElement EURSellValue;

    @FindBy (xpath=".//*[@id='USD_buy']")
    private WebElement USDBuyValue;

    @FindBy (xpath=".//*[@id='USD_sell']")
    private WebElement USDSellValue;

    @FindBy (xpath=".//*[@id='RUB_buy']")
    private WebElement RubBuyValue;

    @FindBy (xpath=".//*[@id='RUB_sell']")
    private WebElement RubSellValue;

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
            logger.info("PrivatBank page was opened");
        } catch (Exception e){
            logger.error("Can not work with PrivatBank page" + e);
            Assert.fail("Can not work with PrivatBank");
        }
    }

    public double getCurrencyBuyValueByUI(String currency) {
        String currencyBuyValue = "";
        if (currency.equals("USD")) {
            currencyBuyValue = USDBuyValue.getText();
        }
        else if (currency.equals("EUR")) {
            currencyBuyValue = EURBuyValue.getText();
        }
        else if (currency.equals("RUR")) {
            currencyBuyValue = RubBuyValue.getText();
        }
        double currencyBuy = Double.parseDouble(currencyBuyValue);
        return currencyBuy;
    }

    public double getCurrencySellValueByUI(String currency) {
        String currencySaleValue = "";
        if (currency.equals("USD")) {
            currencySaleValue = USDSellValue.getText();
        }
        else if (currency.equals("EUR")) {
            currencySaleValue = EURSellValue.getText();
        }
        else if (currency.equals("RUR")) {
            currencySaleValue = RubSellValue.getText();
        }
        double currencySale = Double.parseDouble(currencySaleValue);
        return currencySale;
    }
}

