package registrationTest;

import baseTest.BaseTest;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void checkValidMessagesInRegForm() {
        loginPage.openLoginPage();
        loginPage.fillRegFormAndSubmit("tr", "test.com", "123");

        checkExpectedResult("Message  'Username must be at least 3 characters.' is not visible", loginPage.isLoginValidMessageInFormPresent(), true);
        checkExpectedResult("Message  'You must provide a valid email address.' is not visible", loginPage.isEmailValidMessageInFormPresent(), true);
        checkExpectedResult("Message  'Password must be at least 12 characters.' is not visible", loginPage.isPasswordValidMessageInFormPresent(), true);
    }

    @Test
    public void checkErrorsMessages() {
        loginPage.fillRegFormAndSubmit("tr", "test.com", "123456qwerty");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
    }
}
