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
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath= ".//div[@class='alert alert-danger text-center']")
    private WebElement signInAlert;


    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement userNameInput;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement emailInput;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement passwordInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signUpButton;

    @FindBy(xpath = ".//*[text() ='Username must be at least 3 characters']")
    private WebElement errorUserName;

    @FindBy(xpath = ".//*[text() = 'You must provide a valid email address']")
    private WebElement errorEmail;

    @FindBy(xpath = ".//*[text() = 'Password must be at least 12 characters']")
    private WebElement errorPassword;



    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("A Login page was opened.");
        } catch (Exception e) {
            logger.error("Cannot work with a login Page" + e);
            Assert.fail("Cannot work with a login Page");
        }
    }

    public void enterLoginInSignIn(String login) {
//        try{
//            //WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in the SignIn input login.");
//        } catch (Exception e) {
//            logger.error("Cannot work with an element" + e);
//            Assert.fail("Cannot work with an element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);

    }

    public void enterLoginInSignUp(String loginSignUp) {
        enterTextToElement(userNameInput, loginSignUp);
    }

    public void enterEmailInSignUp(String emailSignUp) {
        enterTextToElement(emailInput, emailSignUp);
    }

    public void enterPasswordInSignUp(String passwordSignUp) {
        enterTextToElement(passwordInput, passwordSignUp);
    }

    public void clickOnSignUpButton() {
        clickOnElement(signUpButton);
    }

    public boolean isErrorUserNamePresent() {
        return isElementPresent(userNameInput);

    }

    public boolean isErrorEmailPresent() {
        return  isElementPresent(emailInput);
    }

    public boolean isErrorPasswordPresent() {
        return isElementPresent(passwordInput);
    }

//    public void fillLoginFormAndSubmit(String login, String password) {
//        openLoginPage();
//        enterLoginInSignIn(login);
//        enterPasswordInSignIn(password);
//        clickOnButtonSignIn();
//    }
//
//    public boolean isButtonSignInPresent() {
//     return isElementPresent(buttonSignIn);
//    }
//
//    public boolean isSignInAlertPresent() {
//        return isElementPresent(signInAlert);
//    }


    }

