package pages;

import java.util.List;

import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import libs.TestData;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.ArrayList;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private TextInput inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    @Name("Input Pass ")
    private TextInput inputPassWord;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = "//div[contains(text(),'Invalid username / password')]")
    private TextInput labelMessageInvalidLogin;

    @FindBy(id = "username-register")
    private TextInput inputLoginRegistration;

    @FindBy(id = "email-register")
    private TextInput inputEmailRegistration;

    @FindBy(id = "password-register")
    private TextInput inputPassWordRegistration;

    @FindBy(xpath = ".//button[text()='Sign up for OurApp']")
    private Button buttonSignUp;

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

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(baseUrl);
            logger.info("Login page was opened");
        } catch (Exception e) {
            logger.error("Can't work with LoginPage" + e);
            Assert.fail("Can't work with LoginPage");
        }
    }

    @Step
    public void enterLoginInSignIn(String login) {
        enterTextToElement(inputLogin, login);
    }

    @Step
    public void enterPassWordInSignIn(String password) {
        enterTextToElement(inputPassWord, password);
    }

    @Step
    public void clickOnButtonSignIn() {
        clickOnElement(buttonSignIn);
    }

    @Step
    public void fillLoginFormAndSubmit(String login, String passWord) {
        openLoginPage();
        enterLoginInSignIn(login);
        enterPassWordInSignIn(passWord);
        clickOnButtonSignIn();
    }

    @Step
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    @Step
    public boolean isLabelMessageInvalidLoginPresent() {
        return isElementPresent(labelMessageInvalidLogin);
    }


    public HomePage loginWithValidCred() {
        fillLoginFormAndSubmit(TestData.VALID_LOGIN, TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    @Step
    public LoginPage enterLoginInRegistration(String login) {
        enterTextToElement(inputLoginRegistration, login);
        return this;
    }

    @Step
    public LoginPage enterEmailInRegistration(String email) {
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    @Step
    public LoginPage enterPassWordRegistration(String passWord) {
        enterTextToElement(inputPassWordRegistration, passWord);
        return this;
    }

    @Step
    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
        webDriverWait10.withMessage("Number of Messages")
                .until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), errorsArray.length));
    //    Assert.assertEquals(actualListOfErrors.size(), errorsArray.length);

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

