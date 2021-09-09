package registrationTest;

import baseTest.BaseTest;
import categories.SmokeTestFilter;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import pages.LoginPage;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest extends BaseTest {
    @Test
    public void checkAlertMassageOnRegistrationForm() {
        loginPage.fillRegistrarionFormAndSubmit("tr", "test.com", "123");

        checkExpectedResult("Login live validate message is not visible", loginPage.checkLoginLiveValidateMessage(), true);
        checkExpectedResult("Email live validate message is not visible", loginPage.checkEmailLiveValidateMessage(), true);
        checkExpectedResult("Password live validate message is not visible", loginPage.checkPasswordLiveValidateMessage(), true);

    }


    @Category(SmokeTestFilter.class)
    @Test
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,12345qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String password, String errors) {
        loginPage.openLoginPage();
        loginPage.enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordInRegistration(password)
                .checkErrorsMessages(errors);
    }
}
