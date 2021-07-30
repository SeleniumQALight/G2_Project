package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = "//div[contains(text(),'Invalid username / password')]")
    private WebElement labelMessageInvalidLogin;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
        enterTextToElement(inputLogin, login);
    }
    public void enterPassWordInSignIn(String password) {
        enterTextToElement(inputPassWord, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(password);
        clickOnButtonSignIn();
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isLabelMessageInvalidLoginPresent(){
        return isElementPresent(labelMessageInvalidLogin);
    }
}