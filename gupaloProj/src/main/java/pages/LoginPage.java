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

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertText;

    @FindBy(xpath = ".//input[@placeholder='Pick a username']")
    private WebElement inputLoginInForm;

    @FindBy(xpath = ".//input[@placeholder='you@example.com']")
    private WebElement inputEmail;

    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private WebElement inputPasswordInForm;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy (xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement loginValidMessageInForm;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement emailValidMessageInForm;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement passwordValidMessageInForm;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isAlertIsPresent() {
        return isElementPresent(alertText);
    }

    public boolean isLoginValidMessageInFormPresent (){
        return isElementPresent(loginValidMessageInForm);
    }

    public boolean isEmailValidMessageInFormPresent (){
        return isElementPresent(emailValidMessageInForm);
    }

    public boolean isPasswordValidMessageInFormPresent (){
        return isElementPresent(passwordValidMessageInForm);
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
//        try{
//         //   WebElement element = webDriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + "was inputted in SignIn input login");
//
//        }catch (Exception e){
//            logger.error("Cannot work with element" +e);
//            Assert.fail("Cannot work with element" +e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit (String login, String password) {
        enterTextToElement(inputLogin,login);
        enterTextToElement(inputPassword, password);
        clickOnButtonSignIn();
    }

    public void enterLoginInRegForm (String login){
        enterTextToElement(inputLoginInForm, login);
    }

    public void enterEmailInRegForm (String email){
        enterTextToElement(inputEmail, email);
    }

    public void enterPasswordInRegForm (String password){
        enterTextToElement(inputPasswordInForm, password);
    }

    public void clickOnButtonSignUp (){
        clickOnElement(buttonSignUp);
    }

    public void fillRegFormAndSubmit(String login, String email, String password){
        enterTextToElement(inputLoginInForm, login);
        enterTextToElement(inputEmail, email);
        enterTextToElement(inputPasswordInForm, password);
        clickOnButtonSignUp();
    }
}




