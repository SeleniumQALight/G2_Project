package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    // --------------------------------------------------------------------------------------------------
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//input[@id='username-register' and @placeholder='Pick a username']")
    private TextInput inputUsernameRegistration;

    @FindBy(xpath = ".//input[@id='email-register' and @placeholder='you@example.com']")
    private TextInput inputEmailRegistration;

    @FindBy(xpath = ".//input[@id='password-register' and @placeholder='Create a password']")
    private TextInput inputPasswordRegistration;

    @FindBy(xpath = ".//button[contains(text(),'Sign up for OurApp')]")
    private Button buttonSignUpRegistration;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;
    final String listOfErrorLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    // --------------------------------------------------------------------------------------------------
    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "";
    }

    public void openLoginPage() {
        try {
            webDriver.get(baseURL);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
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

    public void enterUsernameRegistration(String usernameRegistration) {
        enterTextToElement(inputUsernameRegistration, usernameRegistration);
    }

    public void enterEmailRegistration(String emailRegistration) {
        enterTextToElement(inputEmailRegistration, emailRegistration);
    }

    public void enterPasswordRegistration(String passwordRegistration) {
        enterTextToElement(inputPasswordRegistration, passwordRegistration);
    }

    public void clickOnButtonSignUpRegistration() {
        clickOnElement(buttonSignUpRegistration);
    }

    public void fillLoginAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    public HomePage loginWithValidCred() {
        fillLoginAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);

        return new HomePage(webDriver);
    }

    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of messages ")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listOfErrorLocator), errorsArray.length));
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : actualListOfErrors) {
            actualTextFromErrors.add(element.getText());
        }
        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

    }
}
