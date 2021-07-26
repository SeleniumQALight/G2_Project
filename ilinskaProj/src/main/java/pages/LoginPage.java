package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage<errorMessage> extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//div[@class='alert alert-danger text-center' and text()='Invalid username / password']")
    private WebElement errorSignIN;
    @FindBy(xpath = ".//input[@placeholder='Pick a username']")
    private WebElement inputLOgin;
    @FindBy(xpath = ".//input[@placeholder='you@example.com']")
    private WebElement inputEmail;
    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputPass;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement OurApp;

    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Username must be at least 3 characters.']")
    private WebElement errorMessage;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='You must provide a valid email address.']")
    private WebElement errorEmailMessage;
    @FindBy(xpath = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='Password must be at least 12 characters.']")
    private WebElement errorPassword;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Сannot work with LoginPage" + e);
            Assert.fail("Сannot work with LoginPage");
        }
    }

    public void enterLoginIn(String login) {

        enterTextToElement(inputLogin, login);

    }

    public void enterLoginSignUp(String login) {
        enterTextToElement(inputLOgin, login);
    }

    public void enterEmail(String email) {
        enterTextToElement(inputEmail, email);

    }

    public void enterPasswwordInSign(String password) {
        enterTextToElement(inputPassword, password);


    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);

    }
//    public void isButtonSignOutPresent(){
//        if(isElementPresent(buttonSignOut)){
//            logger.info("Button is present");
//        } else {
//            logger.info("Please try again!");
//        }
//
//    }

    public void clickOnOurApp() {
        clickOnElement(OurApp);
    }

    public boolean isPopupDisplay(String message) {
        return isElementPresent(buttonSignIn);

    }

    public void enterPassSignUp(String password) {
        enterTextToElement(inputPass, password);
    }

    public boolean errorPopUpUserName(String message) {
        return isElementPresent(errorMessage);

    }
    public boolean errorPopupEmail(String message){
        return  isElementPresent(errorEmailMessage);

    }

    public boolean errorPopupPass(String message){
        return isElementPresent(errorPassword);
    }
}
