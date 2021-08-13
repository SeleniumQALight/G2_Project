package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTestHW extends BaseTest {
    @Test
    public void fieldsValidation(){
        loginPage.openLoginPage();
        loginPage.enterUsernameInRegistrationForm("tr");
        loginPage.enterEmailInRegistrationForm("test.com");
        loginPage.enterPasswordInRegistrationForm("123");
        loginPage.clickOnSignUpButton();

        checkExpectedResult("Username validation message is not displayed",
                loginPage.isUsernameValidationMessagePresent(), true);
        checkExpectedResult("Email validation message is not displayed",
                loginPage.isEmailValidationMessagePresent(), true);
        checkExpectedResult("Password validation message is not displayed",
                loginPage.isPasswordValidationMessagePresent(), true);

    }
}
