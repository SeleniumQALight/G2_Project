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

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(id = "username-register")
    private WebElement inputUserNameRegister;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegister;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegister;

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
//        try{
////            WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + "was input in SignIn input login");
//
//        }catch (Exception e){
//            logger.error("Can't work with element" + e);
//            Assert.fail("Can't work with element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }
    public void enterLoginInSignUp(String login) {
        enterTextToElement(inputUserNameRegister, login);
    }
    public void enterEmailInSignUp(String email) {
        enterTextToElement(inputEmailRegister, email);
    }
    public void enterPasswordInSignUp(String password) {
        enterTextToElement(inputPasswordRegister, password);
    }
    public void enterPassWordInSignIn(String password) {
        enterTextToElement(inputPassWord, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(password);
        clickOnButtonSignIn();
    }
    public void fillRegistrationFormAndSubmit(String login, String email, String password) {
        openLoginPage();
        enterLoginInSignUp(login);
        enterEmailInSignUp(email);
        enterPasswordInSignUp(password);
        clickOnButtonSignUp();
    }
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }
    public boolean isLabelMessageInvalidLoginPresent(){
        return isElementPresent(webDriver.findElement(
                By.xpath("//div[contains(text(),'Invalid username / password')]")));
    }
    public boolean isLabelMessageShortUsernamePresent(){
        return isElementPresent(webDriver.findElement(
                By.xpath("//div[contains(text(),'Username must be at least 3 characters.')]")));
    }
    public boolean isLabelMessageValidEmailPresent() {
        return isElementPresent(webDriver.findElement(
                By.xpath("//div[contains(text(),'You must provide a valid email address.')]")));
    }
}