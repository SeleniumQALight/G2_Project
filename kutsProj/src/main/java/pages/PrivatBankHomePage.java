package pages;

import api.PrivatBankApiHelper;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Select;

import static libs.TestData.*;

public class PrivatBankHomePage extends ParentPage{

    Actions actions = new Actions(webDriver);
    PrivatBankApiHelper privatBankApiHelper = new PrivatBankApiHelper();

    @FindBy(xpath = ".//select[@id='courses_type']")
    private Select dropDownCoursesType;

    private String currency_ui;

    public PrivatBankHomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openPrivatBankHomePage(){
        try {
            webDriver.get(baseUrlPrivatBank);
            logger.info("PrivatBank Home page was opened");
        }catch (Exception e){
            logger.error("Can not work with PrivatBank Home page" + e);
            Assert.fail("Can not work with PrivatBank Home page");
        }
    }

    public void scrollToExchangeRatesBlock() {
        try {
            WebElement blockExchangeRates = webDriver.findElement(By.xpath(".//*[@class='block_content courses']"));
            actions.moveToElement(blockExchangeRates).perform();
            logger.info("Exchange rates block is displayed");
        }catch (Exception e){
            logger.error("Can not work with Exchange rates block");
            Assert.fail("Can not work with Exchange rates block");
        }
    }

    public void selectInBranchesValueInDDCoursesType() {
        selectValueInDD(dropDownCoursesType, "posts_course");
    }

    public void getAllExchangeRatesViaAPI() {
        privatBankApiHelper.getAllExchangeRates();
    }

    public void checkExchangeRatesForCurrency(String currency) {
        privatBankApiHelper.getExchangeRatesForCurrency(currency);
        if (currency.equalsIgnoreCase("RUR")) {
            currency_ui = "RUB";
            currency_buy_ui = privatBankApiHelper.convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_buy']", currency_ui))).getText().trim());
            currency_sale_ui = privatBankApiHelper.convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_sell']", currency_ui))).getText().trim());
        } else {
            currency_buy_ui = privatBankApiHelper.convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_buy']", currency))).getText().trim());
            currency_sale_ui = privatBankApiHelper.convertStringToDouble(webDriver.findElement(By.xpath(String.format(".//*[@id='%s_sell']", currency))).getText().trim());
        }
        logger.info(String.format("Exchange buying rate for %s currency from API is: ", currency) + currency_buy_api);
        logger.info(String.format("Exchange buying rate for %s currency from UI is: ", currency) + currency_buy_ui);
        Assert.assertEquals(currency_buy_ui, currency_buy_api);
        Assert.assertEquals(currency_sale_ui, currency_sale_api);
    }
}
