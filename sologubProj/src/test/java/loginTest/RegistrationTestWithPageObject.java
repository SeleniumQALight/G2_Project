package loginTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTestWithPageObject extends BaseTest {
    @Test
    @Parameters({
            "12,test.com,11,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,test.com,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors: login = {0}, email ={1}, password = {2}")
    public void ValidationMessagesInRegistrationFields(String login, String email, String password, String errors) {
        loginPage.openLoginPage();
        loginPage.fillRegistrationFormAndSubmit(login, email, password);
//        checkExpectedResult("Registration username alert is missed", loginPage.isRegistrationUsernameAlertPresent(), true);
//        checkExpectedResult("Registration email alert is missed", loginPage.isRegistrationEmailAlertPresent(), true);
//        checkExpectedResult("Registration password alert is missed", loginPage.isRegistrationPasswordAlertPresent(), true);
        //loginPage.checkErrors("Username must be at least 3 characters.;You must provide a valid email address.");
        loginPage.checkErrorsMessages(errors);

    }
}
