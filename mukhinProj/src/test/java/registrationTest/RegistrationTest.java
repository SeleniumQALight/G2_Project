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
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address.",

    })
    @TestCaseName("registrationErrors: login = {0}, email = {1}, password = {2}")
    public void registrationErrors(String login, String email, String passWord, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(passWord)
                .checkErrorsMessages(errors)

        ;
    }

    @Test
    public void checkValidationMessageInFormRegistration() {
        loginPage.fillSignOutFormAndSubmit("tr", "test.com", "123");
        checkExpectedResult("Message 'Username must be at least 3 characters' is not visible", homePage.isMessageFailUserNamePresent(), true);
        checkExpectedResult("Message 'You must provide a valid email address.' is not visible", homePage.isMessageFailEmailPresent(), true);
        checkExpectedResult("Message 'Password must be at least 12 characters.' is not visible", homePage.isMessageFailPassWordPresent(), true);

    }
}
