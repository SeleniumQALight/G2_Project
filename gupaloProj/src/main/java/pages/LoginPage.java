package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Pass")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement alertText;

    @FindBy(xpath = ".//input[@placeholder='Pick a username']")
    private TextInput inputLoginInForm;

    @FindBy(xpath = ".//input[@placeholder='you@example.com']")
    private TextInput inputEmail;

    @FindBy(xpath = ".//input[@placeholder='Create a password']")
    private TextInput inputPasswordInForm;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private WebElement loginValidMessageInForm;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private WebElement emailValidMessageInForm;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private WebElement passwordValidMessageInForm;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

    final String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
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

    public boolean isLoginValidMessageInFormPresent() {
        return isElementPresent(loginValidMessageInForm);
    }

    public boolean isEmailValidMessageInFormPresent() {
        return isElementPresent(emailValidMessageInForm);
    }

    public boolean isPasswordValidMessageInFormPresent() {
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

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public LoginPage enterLoginInRegForm(String login) {
        enterTextToElement(inputLoginInForm, login);
        return this;
    }

    public LoginPage enterEmailInRegForm(String email) {
        enterTextToElement(inputEmail, email);
        return this;
    }

    public LoginPage enterPasswordInRegForm(String password) {
        enterTextToElement(inputPasswordInForm, password);
        return this;
    }

    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }

    public void fillRegFormAndSubmit(String login, String email, String password) {
        openLoginPage();
        enterTextToElement(inputLoginInForm, login);
        enterTextToElement(inputEmail, email);
        enterTextToElement(inputPasswordInForm, password);
        clickOnButtonSignUp();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public void checkErrors(String expectedErrors) {
        String[] arrErrorMessages = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of Message")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), arrErrorMessages.length));

//        Assert.assertEquals("Numbers of messages are not equals", arrErrorMessages.length, errorsListMessage.size());
//        for (int i = 0; i < arrErrorMessages.length; i++) {
//            Assert.assertEquals("Messages are not equals", arrErrorMessages[i], errorsListMessage.get(i).getText());
//        }

        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : actualListOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < arrErrorMessages.length; i++) {
            softAssertions.assertThat(arrErrorMessages[i]).isIn(actualTextFromErrors);
        }

        softAssertions.assertAll();

    }
}




