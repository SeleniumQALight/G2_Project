package loginTest;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;

public class SignUpValidationMessages extends ParentPage {

    @FindBy(xpath = ".//input[@id='username-register']]")
    private WebElement userNameInput;

    @FindBy(xpath = ".//input[@id='email-register']")
    private WebElement emailInput;

    @FindBy(xpath = ".//input[@id='password-register']")
    private WebElement passwordInput;

    @FindBy(xpath = ".//button[@type='submit']")
    private WebElement signUpButton;

    public SignUpValidationMessages(WebDriver webDriver) {
        super(webDriver);
    }

    @Test
    public void signUpValidationMessages() {
        signUpValidationMessages();
        enterTextToElement(userNameInput,"tr");
        enterTextToElement(emailInput, "test.com");
        enterTextToElement(passwordInput, "123");
        clickOnElement(signUpButton);
        isElementPresent(userNameInput);
        isElementPresent(emailInput);
        isElementPresent(passwordInput);

       //checkExpectedResult( "Username must be at least 3 characters.");
        // checkExpectedResult("You must provide a valid email address.");
        // checkExpectedResult("Password must be at least 12 characters.");

    }


}
