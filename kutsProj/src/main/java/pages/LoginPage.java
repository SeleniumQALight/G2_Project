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
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertInvalidUsernamePassword;

    @FindBy(xpath = ".//input[@placeholder='Pick a username']")
    private WebElement inputLoginRegistration;

    @FindBy(xpath = ".//input[@placeholder='you@example.com']")
    private WebElement inputEmailRegistration;

    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputPassWordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement alertInvalidUsernameRegistration;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement alertInvalidEmailRegistration;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement alertInvalidPasswordRegistration;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        }catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
////            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in SignIn input login");
//
//        }catch (Exception e){
//            logger.error("Can not work with element" + e);
//            Assert.fail("Can not work with element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPassWordInSignIn(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isAlertInvalidUsernamePasswordDisplayed() {
        return isElementPresent(alertInvalidUsernamePassword);
    }

    public void enterLoginInRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
    }

    public void enterEmailRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
    }

    public void enterPassWordInRegistration(String passWord) {
        enterTextToElement(inputPassWordRegistration, passWord);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean isAlertInvalidUsernameRegistrationDisplayed() {
        return isElementPresent(alertInvalidUsernameRegistration);
    }

    public boolean isAlertInvalidEmailRegistrationDisplayed() {
        return isElementPresent(alertInvalidEmailRegistration);
    }

    public boolean isAlertInvalidPasswordRegistrationDisplayed() {
        return isElementPresent(alertInvalidPasswordRegistration);
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
}