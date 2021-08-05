package registrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {

    @Test
    @Parameters({
            "12,test.com,123," +
                    "Username must be at least 3 characters.;" +
                    "You must provide a valid email address.;" +
                    "Password must be at least 12 characters.",
            "12,test.com,123456qwerty," +
                    "Username must be at least 3 characters.;" +
                    "You must provide a valid email address."
    })
    @TestCaseName("Registration Errors for: login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String username, String email, String password, String errors) {
        loginPage.openLoginPage();
        loginPage
                .enterUsernameInRegistrationForm(username)
                .enterEmailInRegistrationForm(email)
                .enterPasswordInRegistrationForm(password)
                .clickOnSignUpButton() //TBU: will be removed after added wait (lesson 8)
                .checkErrorsMessages(errors);
    }
}
