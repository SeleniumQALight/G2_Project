package pages;

import io.qameta.allure.Step;
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
import java.util.Arrays;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath=".//input[@placeholder='Username']")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Pass")
    private TextInput inputPassword;

    @FindBy(xpath=".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy (xpath = ".//div[text() = 'Invalid username / password']")
    private WebElement invalidCredentialsAlert;

    @FindBy (xpath = ".//input[@id='username-register']")
    private TextInput inputRegisterUsername;


    @FindBy (xpath=".//input[@id='email-register']")
    private TextInput inputRegisterEmail;

    @FindBy (xpath = ".//input[@id='password-register']")
    private TextInput inputRegisterPassword;

    @FindBy (xpath = ".//button[@type='submit']")
    private Button signUpForOurApp;

    @FindBy (xpath = ".//div[text() = 'Username must be at least 3 characters.']")
    private WebElement registrationUsernameAlert;

    @FindBy (xpath = ".//div[text() = 'You must provide a valid email address.']")
    private WebElement registrationEmailAlert;

    @FindBy (xpath = ".//div[text() = 'Password must be at least 12 characters.']")
    private WebElement registrationPasswordAlert;

    @FindBy(xpath=".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

    @FindBy (xpath=".//*[contains(@class,'danger text-center')]")
    private WebElement alertInCenter;

    final String listErrorsLocators = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webdriver) {
        super(webdriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e){
            logger.error("Can not work with LoginPage" + e);
            Assert.fail("Can not work with LoginPage");
        }
    }

    @Step
    public void enterLoginInSignIn(String login) {
//        try{
//          //  WebElement element = webdriver.findElement(By.xpath(".//input[@placeholder='Username']"));
//            inputLogin.clear();
//            inputLogin.sendKeys(login);
//            logger.info(login + " was inputted in SignIn input login");
//        } catch(Exception e){
//            logger.error("Can not work with element" + e);
//            Assert.fail("Can not work with element" + e);
//        }
        enterTextToElement(inputLogin, login);

    }

    @Step
    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }

    @Step
    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    @Step
    public boolean isInvalidCredentialsAlertPresent() {
        return isElementPresent(invalidCredentialsAlert);
    }

    @Step
    public void enterRegistrationName(String username) {
        enterTextToElement(inputRegisterUsername, username);
    }

    @Step
    public void enterRegistrationEmail(String email) {
        enterTextToElement(inputRegisterEmail, email);
    }

    @Step
    public void enterRegistrationPassword(String password) {
        enterTextToElement(inputRegisterPassword, password);
    }

    public void clickSignUpButton() {
        clickOnElement(signUpForOurApp);
    }

    public void fillRegistrationFormAndSubmit(String username, String email, String password) {
        enterRegistrationName(username);
        enterRegistrationEmail(email);
        enterRegistrationPassword(password);
        clickSignUpButton();
    }

    public boolean isRegistrationUsernameAlertPresent() {
        return isElementPresent(registrationUsernameAlert);
    }

    public boolean isRegistrationEmailAlertPresent() {
        return isElementPresent(registrationEmailAlert);
    }

    public boolean isRegistrationPasswordAlertPresent() {
        return isElementPresent(registrationPasswordAlert);
    }

    public LoginPage checkErrors(String alerts) {
        List <String> expectedListOfAlerts = Arrays.asList(alerts.split(";"));
        List <WebElement> actualListOfAlertsObjects = webDriver.findElements(
                By.xpath(".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']"));
        List<String> actualListOfAlerts = new ArrayList<String>();

        for (WebElement element: actualListOfAlertsObjects) {
            actualListOfAlerts.add(element.getText());
        }

        Assert.assertTrue(
                String.format("Number of expected and actual alerts is not equal. Expected number: %d, actual number: %d",
                expectedListOfAlerts.size(), actualListOfAlerts.size()),
                expectedListOfAlerts.size() == actualListOfAlerts.size());
          //variant # 1: checking alerts text
//        int n = 0;
//        for (String text: expectedListOfAlerts) {
//            Assert.assertTrue( "Expected alert is not present: " + text,text.equals(actualListOfAlerts.get(n)));
//            n++;
//        }

        //variant # 2: checking alerts text
        for (String text: expectedListOfAlerts) {
            Assert.assertTrue("Expected alert is not present: " + text, actualListOfAlerts.contains(text));
        }
        return this;
    }

    public void checkErrorsMessages(String expectedErrors) {
        Util.waitABit(3);
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of Messages")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocators), errorsArray.length));
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

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center", messageText, alertInCenter.getText());
    }
}
