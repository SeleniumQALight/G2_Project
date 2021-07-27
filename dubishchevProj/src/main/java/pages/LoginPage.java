package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement invalidUsernamePasswordMessage;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement signUpUsername;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement signUpEmail;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement signUpPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement signUpButton;

    @FindBy(xpath = ".//input[@id='username-register']/../div")
    private WebElement invalidSignUpUsernameMessage;

    @FindBy(xpath = ".//input[@id='email-register']/../div")
    private WebElement invalidSignUpEmailMessage;

    @FindBy(xpath = ".//input[@id='password-register']/../div")
    private WebElement invalidSignUpPasswordMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with LoginPage " + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public void fillSignUpFormAndSubmit(String username, String email, String password) {
        openLoginPage();
        enterUsernameInSignUp(username);
        enterEmailInSignUp(email);
        enterPasswordInSignUp(password);
        clickOnButtonSignUp();
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

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isWarningMessagePresent() {
        return isElementPresent(invalidUsernamePasswordMessage);
    }

    public void enterUsernameInSignUp(String username) {
        enterTextToElement(signUpUsername, username);
    }

    public void enterPasswordInSignUp(String password) {
        enterTextToElement(signUpPassword, password);
    }

    public void enterEmailInSignUp(String email) {
        enterTextToElement(signUpEmail, email);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(signUpButton);
    }

    public String getSignUpUsernameWarningText() {
        return getElementText(invalidSignUpUsernameMessage);
    }

    public String getSignUpEmailWarningText() {
        return getElementText(invalidSignUpEmailMessage);
    }

    public String getSignUpPasswordWarningText() {
        return getElementText(invalidSignUpPasswordMessage);
    }
}
