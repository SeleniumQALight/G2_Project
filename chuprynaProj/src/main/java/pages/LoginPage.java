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
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with Login Page:" + e); // writes into log file
            Assert.fail("Cannot work with Login Page"); // to stop test in case of error; writes into reports
        }
    }

    public void enterLoginInSignInForm(String userName) {
        try {
            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
            element.clear();
            element.sendKeys(userName);
            logger.info(userName + " was inputted in SignIn input Username");
        } catch (Exception e){
            logger.error("Cannot enter username in SingIn form:" + e);
            Assert.fail("Cannot enter username in SingIn form");
        }
    }
}
