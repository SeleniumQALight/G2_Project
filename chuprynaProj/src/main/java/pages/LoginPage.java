package pages;

import libs.TestData;
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

    @FindBy(xpath = ".//div[text() = 'Invalid username / password']")
    private WebElement alertMessage;

    @FindBy(id = "username-register")
    private WebElement inputRegistrationUsername;

    @FindBy(id = "email-register")
    private WebElement inputRegistrationEmail;

    @FindBy(id = "password-register")
    private WebElement inputRegistrationPassword;

    @FindBy(xpath = ".//button[text() = 'Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement usernameValidationMsg;

    @FindBy(xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement emailValidationMsg;

    @FindBy(xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement passwordValidationMsg;

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
//        try {
//            inputLogin.clear();
//            inputLogin.sendKeys(userName);
//            logger.info(userName + " was inputted in SignIn input Username");
//        } catch (Exception e){
//            logger.error("Cannot enter username in SingIn form:" + e);
//            Assert.fail("Cannot enter username in SingIn form");
//        }
        enterTextToElement(inputLogin, userName);
    }

    public void enterPasswordInSignInForm(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnSignInButton() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String userName, String password){
        openLoginPage();
        enterLoginInSignInForm(userName);
        enterPasswordInSignInForm(password);
        clickOnSignInButton();
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isAlertMessagePresent() {
        return isElementPresent(alertMessage);
    }

    public void enterUsernameInRegistrationForm(String username) {
        enterTextToElement(inputRegistrationUsername, username);
    }

    public void enterEmailInRegistrationForm(String email) {
        enterTextToElement(inputRegistrationEmail, email);
    }

    public void enterPasswordInRegistrationForm(String password) {
        enterTextToElement(inputRegistrationPassword, password);
    }

    public void clickOnSignUpButton() {
        clickOnElement(buttonSignUp);
    }

    public boolean isUsernameValidationMessagePresent() {
        return isElementPresent(usernameValidationMsg);
    }

    public boolean isEmailValidationMessagePresent() {
        return isElementPresent(emailValidationMsg);
    }

    public boolean isPasswordValidationMessagePresent() {
        return isElementPresent(passwordValidationMsg);
    }

    public HomePage loginWithValidCredentials(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}
