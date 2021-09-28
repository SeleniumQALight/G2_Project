package pages;

import io.qameta.allure.Step;
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
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;
    
    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Password")
    private TextInput inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//div[text()='Invalid username / password']")
    private HtmlElement alertInvalidSignIn;

    @FindBy(xpath = ".//*[contains(@class,'danger text-center')]")
    private HtmlElement messageInvalidSignIn;

    @FindBy(id = "username-register")
    private TextInput regLogin;

    @FindBy(id = "email-register")
    private TextInput regEmail;

    @FindBy(id = "password-register")
    private TextInput regPassword;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

    @FindBy(xpath = ".//div[text()='Username must be at least 3 characters.']")
    private HtmlElement alertValidateSignUpLogin;

    @FindBy(xpath = ".//div[text()='You must provide a valid email address.']")
    private HtmlElement alertValidateSignUpEmail;

    @FindBy(xpath = ".//div[text()='Password must be at least 12 characters.']")
    private HtmlElement alertValidateSignUpPassword;

    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> errorsList;

    String actualErrorsListLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/";
    }

    @Step
    public void openLoginPage(){
        try{
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
    @Step
    public void enterPasswordInSignIn(String password) {
        enterTextToElement(inputPassword, password);
    }
    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }
    @Step
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }
    @Step
    public boolean isAlertInvalidSingInPresent() {
        return isElementPresent(alertInvalidSignIn);
    }
    @Step
    public void enterLoginInRegLogin(String login) {
        enterTextToElement(regLogin, login);
    }
    @Step
    public void enterEmailInRegEmail(String email) {
        enterTextToElement(regEmail, email);
    }
    @Step
    public void enterPasswordInRegPassword(String password) {
        enterTextToElement(regPassword, password);
    }
    @Step
    public void clickOnButtonSignUp() {
        clickOnElement(buttonSignUp);
    }
    @Step
    public boolean isAlertInvalidSingUpLoginPresent() {
        return isElementPresent(alertValidateSignUpLogin);
    }
    @Step
    public boolean isAlertInvalidSingUpEmailPresent() {
        return isElementPresent(alertValidateSignUpEmail);
    }
    @Step
    public boolean isAlertInvalidSingUpPasswordPresent() {
        return isElementPresent(alertValidateSignUpPassword);
    }
    @Step
    public void fillLoginFormAndSubmit(String login, String password) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPasswordInSignIn(password);
        clickOnButtonSignIn();
    }
    @Step
    public HomePage loginWithValidCred(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }
    @Step
    public void checkErrors(String errors) {
        String[] expectedErrors = errors.split(";");
        List<String> actualErrorsList = new ArrayList<>();
        webDriverWait10.withMessage("Number of messages ")
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(actualErrorsListLocator), expectedErrors.length)
                );
        for (WebElement error: errorsList) {
            actualErrorsList.add(error.getText());
        }
//        Assert.assertEquals("Number of errors - " + errorsList.size() + ", not as expected", errorsList.size(), expectedErrors.length);
//        Assert.assertThat("Error test is not as expected", actualErrorsList, hasItems(expectedErrors));
        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrors.length; i++) {
            softAssertions.assertThat(expectedErrors[i]).isIn(actualErrorsList);
        }
        softAssertions.assertAll();
    }

    public void checkAlertMessageText(String messageText) {
        Assert.assertEquals("Message in Center ", messageText, messageInvalidSignIn.getText());
    }
}
