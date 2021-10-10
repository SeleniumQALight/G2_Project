package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

public class PrivatBankMainPage extends ParentPage {
    private final String baseUrlPB = "https://privatbank.ua/";

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

}
