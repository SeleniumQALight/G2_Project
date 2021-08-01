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


    @Test
    public void signUpFieldValidationAlertMessages(){
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123456qwerty");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.;");
    }
}