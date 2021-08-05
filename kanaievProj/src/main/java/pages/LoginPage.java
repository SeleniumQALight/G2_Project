package pages;

import libs.TestData;
import libs.Util;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;
    
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertInvalidSignIn;

    @FindBy(id = "username-register")
    private WebElement regLogin;

    @FindBy(id = "email-register")
    private WebElement regEmail;

    @FindBy(id = "password-register")
    private WebElement regPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement alertValidateSignUpLogin;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement alertValidateSignUpEmail;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement alertValidateSignUpPassword;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorsList;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage(){
        try{
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e){
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
//        } catch (Exception e){
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

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isAlertInvalidSingInPresent() {
        return isElementPresent(alertInvalidSignIn);
    }

    public void enterLoginInRegLogin(String login) {
        enterTextToElement(regLogin, login);
    }

    public void enterEmailInRegEmail(String email) {
        enterTextToElement(regEmail, email);
    }

    public void enterPasswordInRegPassword(String password) {
        enterTextToElement(regPassword, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public boolean isAlertInvalidSingUpLoginPresent() {
        return isElementPresent(alertValidateSignUpLogin);
    }

    public boolean isAlertInvalidSingUpEmailPresent() {
        return isElementPresent(alertValidateSignUpEmail);
    }

    public boolean isAlertInvalidSingUpPasswordPresent() {
        return isElementPresent(alertValidateSignUpPassword);
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public void checkErrors(String errors) {
        Util.waitABit(1);
        String[] expectedErrors = errors.split(";");
        List<String> actualErrorsList = new ArrayList<>();
        for (WebElement error: errorsList) {
            actualErrorsList.add(error.getText());
        }
        Assert.assertEquals("Number of errors - " + errorsList.size() + ", not as expected", errorsList.size(), expectedErrors.length);
        Assert.assertThat("Error test is not as expected", actualErrorsList, hasItems(expectedErrors));
    }
}
