package pages;

import libs.TestData;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    WebElement inputLogin;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;
    @FindBy(xpath = ".//input[@id='username-register']")
    private WebElement inputLoginForReg;
    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement inputEmailForReg;
    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement inputPasswordForReg;
    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
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

    private String displayedWarningsXpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
//    WebDriverWait wait = new WebDriverWait(webDriver, 10);

    public void enterLoginInSignIn(String login) {
        enterTextToElement(inputLogin, login);
    }

    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    public void enterUsernameInSignUp(String regLogin) {
        enterTextToElement(inputLoginForReg, regLogin);
    }

    public void enterEmailInSignUp(String regEmail) {
        enterTextToElement(inputEmailForReg, regEmail);
    }

    public void enterPasswordInSignUp(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void clickonFieldUsernameSignUp() {
        clickOnElement(inputLoginForReg);
    }

    public void clickOnPasswordFieldSignUp() {
        clickOnElement(inputPasswordForReg);
    }


    public void fillLoginAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    private void fillRegAndSubmit(String Login, String Email, String Password) {
        openLoginPage();
        enterUsernameInSignUp(Login);
        enterEmailInSignUp(Email);
        enterPasswordInSignUp(Password);
        clickOnButtonSignUp();
    }


    public HomePage loginWithValidCred() {
        fillLoginAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public HomePage RegWithInvalidCred() {
        fillRegAndSubmit(TestData.INVALID_LOGIN, TestData.INVALID_EMAIL, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public void checkErrors(String warnings) {
//        String[] expectedWarnings = warnings.split(";");
        List<WebElement> displayedWarnings = webDriver.findElements(By.xpath(displayedWarningsXpath));
        List<String> expectedWarnings = Arrays.asList(warnings.split(";"));
        System.out.println(expectedWarnings.size());
        System.out.println(displayedWarnings.size());
        if (expectedWarnings.size() == displayedWarnings.size()){
            System.out.println("Message amount is matching");
        }else{
            System.out.println("Message amount is not matching");
        }
    }
}
