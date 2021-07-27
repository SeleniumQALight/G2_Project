package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends ParentPage {

    public HomePage(WebDriver webDriver) {
        super(webDriver);
    }

    // --------------------------------------------------------------------------------------------------
    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;

    @FindBy(xpath = ".//button[text()='Sign In']")
    private WebElement buttonSignIn;

    @FindBy(xpath = ".//div[contains(text(),'Invalid username / password')]")
    private WebElement alertInvalidLogin;

    @FindBy(xpath = ".//div[contains(text(),'Username must be at least 3 characters.')]")
    private WebElement validationMessageUsernameRegistration;

    @FindBy(xpath = ".//div[contains(text(),'You must provide a valid email address.')]")
    private WebElement validationMessageEmailRegistration;

    @FindBy(xpath = ".//div[contains(text(),'Password must be at least 12 characters.')]")
    private WebElement validationMessagePasswordRegistration;

    // --------------------------------------------------------------------------------------------------
    public boolean isButtonSignOutPresent() {
        return isElementPresent(buttonSignOut);
    }

    public boolean isButtonSignInPresent() {
        return isElementPresent(buttonSignIn);
    }

    public boolean isAlertPresent() {
        return isElementPresent(alertInvalidLogin);
    }

    public boolean isValidationMessageUsernameRegistrationPresent() {
        return isElementPresent(validationMessageUsernameRegistration);
    }

    public boolean isValidationMessageEmailRegistrationPresent() {
        return isElementPresent(validationMessageEmailRegistration);
    }

    public boolean isValidationMessagePasswordRegistrationPresent() {
        return isElementPresent(validationMessagePasswordRegistration);
    }
}