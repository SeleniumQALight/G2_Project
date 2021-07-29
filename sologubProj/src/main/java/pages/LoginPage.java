package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage {
    @FindBy(xpath=".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath=".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy (xpath = ".//div[text() = 'Invalid username / password']")
    private WebElement invalidCredentialsAlert;

    @FindBy (xpath = ".//input[@id='username-register']")
    private WebElement inputRegisterUsername;


    @FindBy (xpath=".//input[@id='email-register']")
    private WebElement inputRegisterEmail;

    @FindBy (xpath = ".//input[@id='password-register']")
    private WebElement inputRegisterPassword;

    @FindBy (xpath = ".//button[@type='submit']")
    private WebElement signUpForOurApp;

    @FindBy (xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement registrationUsernameAlert;

    @FindBy (xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement registrationEmailAlert;

    @FindBy (xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement registrationPasswordAlert;

    public LoginPage(WebDriver webdriver) {
        super(webdriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
//          //  WebElement element = webdriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in SignIn input login");
//        } catch(Exception e){
//            logger.error("Can not work with element" + e);
//            Assert.fail("Can not work with element" + e);
//        }
        enterTextToElement(inputLogin, login);

    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isInvalidCredentialsAlertPresent() {
        return isElementPresent(invalidCredentialsAlert);
    }

    public void enterRegistrationName(String username) {
        enterTextToElement(inputRegisterUsername, username);
    }

    public void enterRegistrationEmail(String email) {
        enterTextToElement(inputRegisterEmail, email);
    }

    public void enterRegistrationPassword(String password) {
        enterTextToElement(inputRegisterPassword, password);
    }

    public void clickSignUpButton() {
        clickOnElement(signUpForOurApp);
    }

    public void fillRegistrationFormAndSubmit(String username, String email, String password) {
        enterRegistrationName(username);
        enterRegistrationEmail(email);
        enterRegistrationPassword(password);
        clickSignUpButton();
    }

    public boolean isRegistrationUsernameAlertPresent() {
        return isElementPresent(registrationUsernameAlert);
    }

    public boolean isRegistrationEmailAlertPresent() {
        return isElementPresent(registrationEmailAlert);
    }

    public boolean isRegistrationPasswordAlertPresent() {
        return isElementPresent(registrationPasswordAlert);
    }


}
