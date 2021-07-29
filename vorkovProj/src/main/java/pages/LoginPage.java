package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static libs.TestData.VALID_LOGIN;
import static libs.TestData.VALID_PASSWORD;

public class LoginPage extends ParentPage {
    // --------------------------------------------------------------------------------------------------
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register' and @placeholder='Pick a username']")
    private WebElement inputUsernameRegistration;

    @FindBy(xpath = ".//input[@id='email-register' and @placeholder='you@example.com']")
    private WebElement inputEmailRegistration;

    @FindBy(xpath = ".//input[@id='password-register' and @placeholder='Create a password']")
    private WebElement inputPasswortRegistration;

    @FindBy(xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private WebElement buttonSignUpRegistration;

    // --------------------------------------------------------------------------------------------------
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
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void enterUsernameRegistration(String usernameRegistration) {
        enterTextToElement(inputUsernameRegistration, usernameRegistration);
    }

    public void enterEmailRegistration(String emailRegistration) {
        enterTextToElement(inputEmailRegistration, emailRegistration);
    }

    public void enterPasswordRegistration(String passwordRegistration) {
        enterTextToElement(inputPasswortRegistration, passwordRegistration);
    }

    public void clickOnButtonSignUpRegistration() {
        clickOnElement(buttonSignUpRegistration);
    }

    public void fillLoginAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginAndSubmit(VALID_LOGIN, VALID_PASSWORD);

        return new HomePage(webDriver);
    }
}
