package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginForReg;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailForReg;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordForReg;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");


            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with LoginPage" + e);
            Assert.fail("Cannot work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    private void enterUsernameInSignUp(String regLogin) {
        enterTextToElement(inputLoginForReg, regLogin);
    }

    private void enterEmailInSignUp(String regEmail) {
        enterTextToElement(inputEmailForReg, regEmail);
    }

    public void enterPasswordInSignUp(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillLoginAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    private void fillRegAndSubmit(String invalidLogin, String invalidEmail, String password) {
        openLoginPage();
        enterUsernameInSignUp(invalidLogin);
        enterEmailInSignUp(invalidEmail);
        enterPasswordInSignUp(password);
        clickOnButtonSignUp();
    }

    public HomePage loginWithValidCred() {
        fillLoginAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public HomePage RegWithInvalidCred() {
        fillRegAndSubmit(TestData.INVALID_LOGIN, TestData.INVALID_EMAIL, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public HomePage checkErrors(String s) {


        return new HomePage(webDriver);
    }
}
