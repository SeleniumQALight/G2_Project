package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends ParentPage{
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertSignIn;

    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginSignUpForm;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailSignUpForm;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordSignUpForm;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[contains(text(), 'Username must be at least 3 characters')]")
    private WebElement usernameSignUpAlert;

    @FindBy(xpath = ".//div[contains(text(), 'You must provide a valid email address')]")
    private WebElement emailSignUpAlert;

    @FindBy(xpath = ".//div[contains(text(), 'Password must be at least 12 characters')]")
    private WebElement passwordSignUpAlert;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
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
//            logger.info(login + " was entered in SignIn input login");
//        }catch (Exception e){
//            logger.error("Cannot work with element" + e);
//            Assert.fail("Cannot work with element" + e);
//        }
        enterTextToElement(inputLogin, login);
    }

    public void enterPassWordInSignIn(String passWord) {
        enterTextToElement(inputPassWord, passWord);
    }


    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }


    public void fillLoginFormAndSubmit(String login, String passWord){
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();
    }


    public boolean isButtonSignInPresent(){
        return isElementPresent(buttonSignIn);
    }

    public boolean isSignInAlertPresent(){
        return isElementPresent(alertSignIn);
    }

    public void enterLoginInSignUp(String login) {
        enterTextToElement(inputLoginSignUpForm, login);
    }

    public void enterEmailInSignUp(String email) {
        enterTextToElement(inputEmailSignUpForm, email);
    }

    public void enterPassWordInSignUp(String passWord) {
        enterTextToElement(inputPasswordSignUpForm, passWord);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillSignUpFormAndSubmit(String login, String email, String passWord){
        enterLoginInSignUp(login);
        enterEmailInSignUp(email);
        enterPassWordInSignUp(passWord);
        clickOnButtonSignUp();
    }

    public boolean isUsernameSignUpAlertPresent(){
        return isElementPresent(usernameSignUpAlert);
    }

    public boolean isEmailSignUpAlertPresent(){
        return isElementPresent(emailSignUpAlert);
    }

    public boolean isPasswordSignUpAlertPresent(){
        return isElementPresent(passwordSignUpAlert);
    }

}