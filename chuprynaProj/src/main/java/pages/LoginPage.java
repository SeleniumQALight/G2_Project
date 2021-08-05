package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@placeholder='Username']")
    private WebElement inputLogin;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputRegistrationUsername;

    @FindBy(id = "email-register")
    private WebElement inputRegistrationEmail;

    @FindBy(id = "password-register")
    private WebElement inputRegistrationPassword;

    @FindBy(xpath = ".//button[text() = 'Sign up for OurApp']")
    private WebElement buttonSignUp;

    final String listErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";
    @FindBy(xpath = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']")
    private List<WebElement> actualListOfErrors;

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
            logger.info("Login Page was opened");
        } catch (Exception e) {
            logger.error("Cannot work with Login Page:" + e); // writes into log file
            Assert.fail("Cannot work with Login Page"); // to stop test in case of error; writes into reports
        }
    }

    public void enterLoginInSignInForm(String userName) {
//        try {
//            inputLogin.clear();
//            inputLogin.sendKeys(userName);
//            logger.info(userName + " was inputted in SignIn input Username");
//        } catch (Exception e){
//            logger.error("Cannot enter username in SingIn form:" + e);
//            Assert.fail("Cannot enter username in SingIn form");
//        }
        enterTextToElement(inputLogin, userName);
    }

    public void enterPasswordInSignInForm(String password) {
        enterTextToElement(inputPassword, password);
    }

    public void clickOnSignInButton() {
        clickOnElement(buttonSignIn);
    }

    public void fillLoginFormAndSubmit(String userName, String password){
        openLoginPage();
        enterLoginInSignInForm(userName);
        enterPasswordInSignInForm(password);
        clickOnSignInButton();
    }

    public HomePage loginWithValidCredentials(){
        fillLoginFormAndSubmit(TestData.VALID_LOGIN,TestData.VALID_PASSWORD);
        return new HomePage(webDriver);
    }

    public LoginPage enterUsernameInRegistrationForm(String username) {
        enterTextToElement(inputRegistrationUsername, username);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email) {
        enterTextToElement(inputRegistrationEmail, email);
        return this;
    }

    public LoginPage enterPasswordInRegistrationForm(String password) {
        enterTextToElement(inputRegistrationPassword, password);
        return this;
    }

    public void checkErrorsMessages(String expectedErrors) {
        String[] errorsArray = expectedErrors.split(";");
//        webDriverWait10.withMessage("Number of Errors ").
//              until(ExpectedConditions.numberOfElementsToBe(By.xpath(listErrorsLocator), errorsArray.length));
//        TDU: will be uncommented after lesson 8;
//        - check the exact number of elements, i.e. will fail if actualErrors.length > expectedErrors.length
        Assert.assertEquals(actualListOfErrors.size(), errorsArray.length); // is redundant if the previous row with WAIT is uncommented, does the same check
        SoftAssertions softAssertions = new SoftAssertions();
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement errorElement: actualListOfErrors) {
            actualTextFromErrors.add(errorElement.getText());
        }

        for (int i = 0; i < errorsArray.length; i++) {
            softAssertions.assertThat(errorsArray[i]).isIn(actualTextFromErrors);
        }
    }

    public LoginPage clickOnSignUpButton() {
        clickOnElement(buttonSignUp);
        return this;
    }
}
