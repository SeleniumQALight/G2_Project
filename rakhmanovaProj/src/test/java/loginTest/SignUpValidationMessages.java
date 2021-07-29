package loginTest;

import baseTest.BaseTest;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.ParentPage;

public class SignUpValidationMessages extends BaseTest {

    @Test
    public void signUpValidationMessages() {
        loginPage.openLoginPage();
        loginPage.enterLoginInSignUp("tr");
        loginPage.enterEmailInSignUp("test.com");
        loginPage.enterPasswordInSignUp("123");
        loginPage.clickOnSignUpButton();

        checkExpectedResult( "Username must be at least 3 characters.", loginPage.isErrorUserNamePresent(),true);
        checkExpectedResult("You must provide a valid email address.", loginPage.isErrorEmailPresent(), true);
        checkExpectedResult("Password must be at least 12 characters.", loginPage.isErrorPasswordPresent(), true);
    }

}
