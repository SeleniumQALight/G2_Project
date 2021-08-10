package registrationTest;

import baseTest.BaseTest;
import libs.TestData;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void checkValidationMessageInFormRegistration() {
        loginPage.fillSignOutFormAndSubmit("tr", "test.com", "123");
        checkExpectedResult("Message 'Username must be at least 3 characters' is not visible", homePage.isMessageFailUserNamePresent(), true);
        checkExpectedResult("Message 'You must provide a valid email address.' is not visible", homePage.isMessageFailEmailPresent(), true);
        checkExpectedResult("Message 'Password must be at least 12 characters.' is not visible", homePage.isMessageFailPassWordPresent(), true);

    }
}