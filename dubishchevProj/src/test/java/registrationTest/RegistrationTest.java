package registrationTest;

import baseTest.BaseTest;
import libs.Util;
import org.junit.Test;

public class RegistrationTest extends BaseTest {
    @Test
    public void signUpValidationMessageTest() {
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123");
        Util.waitABit(1);
        checkExpectedResult("Username actual warning message not equals to expected", "Username must be at least 3 characters.", loginPage.getSignUpUsernameWarningText());
        checkExpectedResult("Email actual warning message not equals to expected", "You must provide a valid email address.", loginPage.getSignUpEmailWarningText());
        checkExpectedResult("Password actual warning message not equals to expected", "Password must be at least 12 characters.", loginPage.getSignUpPasswordWarningText());
    }
}
