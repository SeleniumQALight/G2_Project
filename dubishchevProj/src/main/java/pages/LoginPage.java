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
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;
    //private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Pass ") //alternative way to name elements then textinput
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private WebElement invalidUsernamePasswordMessage;

    @FindBy(xpath = ".//input[@id='username-register']")
    private TextInput signUpUsername;

    @FindBy(xpath = ".//input[@id='email-register']")
    private TextInput signUpEmail;

    @FindBy(xpath = ".//input[@id='password-register']")
    private TextInput signUpPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button signUpButton;

    @FindBy(xpath = ".//input[@id='username-register']/../div")
    private WebElement invalidSignUpUsernameMessage;

    @FindBy(xpath = ".//input[@id='email-register']/../div")
    private WebElement invalidSignUpEmailMessage;

    @FindBy(xpath = ".//input[@id='password-register']/../div")
    private WebElement invalidSignUpPasswordMessage;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> listOfSignUpErrorMessage;

    final String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";


    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get("https://qa-complex-app-for-testing.herokuapp.com/");
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can not work with LoginPage " + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }


    public void fillSignUpFormAndSubmit(String username, String email, String password) {
        openLoginPage();
        enterUsernameInSignUp(username);
        enterEmailInSignUp(email);
        enterPasswordInSignUp(password);
        clickOnButtonSignUp();
    }


    public void enterLoginInSignIn(String login) {
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

    public boolean isWarningMessagePresent() {
        return isElementPresent(invalidUsernamePasswordMessage);
    }

    public LoginPage enterUsernameInSignUp(String username) {
        enterTextToElement(signUpUsername, username);
        return this;
    }

    public LoginPage enterPasswordInSignUp(String password) {
        enterTextToElement(signUpPassword, password);
        return this;
    }

    public LoginPage enterEmailInSignUp(String email) {
        enterTextToElement(signUpEmail, email);
        return this;
    }

    public void clickOnButtonSignUp() {
        clickOnElement(signUpButton);
    }

    public String getSignUpUsernameWarningText() {
        return getElementText(invalidSignUpUsernameMessage);
    }

    public String getSignUpEmailWarningText() {
        return getElementText(invalidSignUpEmailMessage);
    }

    public String getSignUpPasswordWarningText() {
        return getElementText(invalidSignUpPasswordMessage);
    }


    public void checkErrors(String stringOfErrorMessages) {
        String[] arrayOfErrorMessages = stringOfErrorMessages.split(";");
        Util.waitABit(1); //Use waiter because sometimes not  find text in warning messages
        List<WebElement> listOfSignUpErrorMessage = webDriver.findElements(By.xpath(".//div[contains(@class,'liveValidateMessage--visible')]"));
        Assert.assertEquals("Number of error messages not equal", arrayOfErrorMessages.length, listOfSignUpErrorMessage.size());
        for (int i = 0; i < arrayOfErrorMessages.length; i++) {
            Assert.assertEquals(i+1 +" pair of error messages not equal", arrayOfErrorMessages[i], listOfSignUpErrorMessage.get(i).getText());
        }
    }

    public void checkErrorMessages(String stringOfErrorMessages) {
        String[] arrayOfErrorMessages = stringOfErrorMessages.split(";");
        webDriverWait10.withMessage("Number of messages ").until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), arrayOfErrorMessages.length));
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element:listOfSignUpErrorMessage) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < arrayOfErrorMessages.length; i++) {
            softAssertions.assertThat(arrayOfErrorMessages[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();
    }

}
