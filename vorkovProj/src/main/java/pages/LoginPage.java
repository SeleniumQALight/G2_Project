package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends ParentPage {
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");

        }

    }


    public void enterLoginInSignIn(String login) {
        try {
            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
            element.clear();
            element.sendKeys(login);
            logger.info("'" + login + "'" + " was inputted in SignIn input login");
        } catch (Exception e) {
            logger.error("Can't work with element" + e);
            Assert.fail("Can't work with element");
        }
    }
}
