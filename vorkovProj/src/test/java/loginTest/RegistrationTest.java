package loginTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {

    @Test
    public void errorMessagesInRegistrationForm() {
        loginPage.openLoginPage();
        loginPage.enterUsernameRegistration("tr");
        loginPage.enterEmailRegistration("test.com");
        loginPage.enterPasswordRegistration("123");
        loginPage.clickOnButtonSignUpRegistration();

        checkExpectedResult("Validation message for input 'Username' isn't visible", homePage.isValidationMessageUsernameRegistrationPresent(), true);
        checkExpectedResult("Validation message for input 'Email' isn't visible", homePage.isValidationMessageEmailRegistrationPresent(), true);
        checkExpectedResult("Validation message for input 'Password' isn't visible", homePage.isValidationMessagePasswordRegistrationPresent(), true);
    }

}
