package signUpTest;

import baseTest.BaseTest;
import org.junit.Test;

public class SignUpTest extends BaseTest {

    @Test
    public void signUpFieldValidationAlerts(){
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123");
        checkExpectedResult("Invalid username alert is not visible", loginPage.isUsernameSignUpAlertPresent(), true);
        checkExpectedResult("Invalid email alert is not visible", loginPage.isEmailSignUpAlertPresent(),true);
        checkExpectedResult("Invalid password alert is not visible", loginPage.isPasswordSignUpAlertPresent(),true);
    }
}