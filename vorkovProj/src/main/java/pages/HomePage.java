package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.Button;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public HomePage checkIsRedirectOnHomePage() {
        checkURL();
        checkIsButtonSignOutVisible();
        return this;
    }

    // --------------------------------------------------------------------------------------------------
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private Button buttonSignOut;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private Button buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username / password')]")
    private WebElement alertInvalidLogin;

    @FindBy(xpath = ".//div[contains(text(),'Username must be at least 3 characters.')]")
    private WebElement validationMessageUsernameRegistration;

    @FindBy(xpath = ".//div[contains(text(),'You must provide a valid email address.')]")
    private WebElement validationMessageEmailRegistration;

    @FindBy(xpath = ".//div[contains(text(),'Password must be at least 12 characters.')]")
    private WebElement validationMessagePasswordRegistration;

    @FindBy(xpath = ".//a[text()='Create Post']")
    WebElement buttonCreatePost;

    @FindBy(xpath = ".//img[@data-original-title='My Profile']")
    private WebElement buttonProfile;

    // --------------------------------------------------------------------------------------------------
    @Step
    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }

    @Step
    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    @Step
    public boolean isAlertPresent() {
        return isElementPresent(alertInvalidLogin);
    }

    @Step
    public boolean isValidationMessageUsernameRegistrationPresent() {
        return isElementPresent(validationMessageUsernameRegistration);
    }

    @Step
    public boolean isValidationMessageEmailRegistrationPresent() {
        return isElementPresent(validationMessageEmailRegistration);
    }

    @Step
    public boolean isValidationMessagePasswordRegistrationPresent() {
        return isElementPresent(validationMessagePasswordRegistration);
    }

    @Step
    public HomePage checkIsButtonSignOutVisible() {
        Assert.assertTrue("Button sign out is not displayed", isButtonSignOutPresent());
        return this;
    }

    @Step
    public CreatePostPage clickOnButtonCreatePost() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public HomePage openHomePage() {
        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.openLoginPage();
        if (!isButtonSignOutPresent()) {
            loginPage.loginWithValidCred();
        }
        return this;
    }

    @Step
    public ProfilePage clickOnButtonProfile(){
        clickOnElement(buttonProfile);
        return new ProfilePage(webDriver);
    }
}
