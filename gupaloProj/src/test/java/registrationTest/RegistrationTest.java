package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
@Category(SmokeTestFilter.class)

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
    @Parameters({
            "12, qqq, 345, Username must be at least 3 characters.;You must provide a valid email address.; Password must be at least 12 characters.;",
            "12, qqq, 123456qwerty, Username must be at least 3 characters.;You must provide a valid email address."

    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrorsMessages(String login, String email, String password, String errors) {
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegForm(login)
                .enterEmailInRegForm(email)
                .enterPasswordInRegForm(password)
                .checkErrors(errors);

    }
}
