package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import libs.Util;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {
    @Test
    public void signUpValidationMessageTest() {
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123");
        Util.waitABit(1);
        checkExpectedResult("Username actual warning message not equals to expected", "Username must be at least 3 characters.", loginPage.getSignUpUsernameWarningText());
        checkExpectedResult("Email actual warning message not equals to expected", "You must provide a valid email address.", loginPage.getSignUpEmailWarningText());
        checkExpectedResult("Password actual warning message not equals to expected", "Password must be at least 12 characters.", loginPage.getSignUpPasswordWarningText());
    }

    @Test
    public void validateErrorMessage(){
        loginPage.fillSignUpFormAndSubmit("tr", "test.com", "123456qwerty");
        loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
    }

    @Test
    @Parameters({
            "tr,test.com,123,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "tr,test.com,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("validate error messages : login = {0}, email = {1}, password = {2}")
    public void validateErrorMessageWithSoftAssertion(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage.
                enterUsernameInSignUp(login).
                enterEmailInSignUp(email).
                enterPasswordInSignUp(password);
        loginPage.checkErrorMessages(errors);
    }
}
