package pages;

import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {

    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[@class='alert alert-danger text-center']")
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

    String errorUserNameLocator = ".//div[@class ='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private WebElement buttonSignUp;

   @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
   private List<WebElement> actualListOfErrors;

   final String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


//    @FindBy(xpath = ".//div[@class ='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
//    private WebElement errorUsernameMessage;


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void openLoginPage() {
        try {
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

    public void clickOnButtonSignIn() { clickOnElement(buttonSignIn); }

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
        return isElementPresent(emailInput);
    }

    public boolean isErrorPasswordPresent() {
        return isElementPresent(passwordInput);
    }

    public void fillLoginFormAndSubmit(String login, String password) { //String email
        openLoginPage();
        enterLoginInSignIn(login);
        //enterEmailInSignUp(email);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public boolean isButtonSignInPresent() {
     return isElementPresent(buttonSignIn);
    }

    public boolean isSignInAlertPresent() {
        return isElementPresent(signInAlert);
    }

    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public LoginPage checkErrors(String errorMessages) {

        String [] expectedErrorMessages = errorMessages.split(";");
        webDriverWait10.until(ExpectedConditions.numberOfElementsToBe(By.xpath(errorUserNameLocator), expectedErrorMessages.length));
        List<WebElement> actualErrorList = webDriver.findElements(
                By.xpath(errorUserNameLocator)
                                         );

        Assert.assertEquals("Number of messages are not equal", expectedErrorMessages.length, actualErrorList.size());
        for (int i = 0; i < expectedErrorMessages.length; i++) {
            Assert.assertEquals("Messages are not equal", expectedErrorMessages[i], actualErrorList.get(i).getText());
        }
       return this;
    }

    public LoginPage enterLoginInRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }

    public LoginPage enterEmailInRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordRegistration(String password) {
        enterTextToElement(inputPasswordRegistration, password);
        return this;
    }

    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), errorsArray.length ));
        // Assert.assertEquals(actualListOfErrors.size(), errorsArray.length);

        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: actualListOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

    }
}

