package registrationTest2;

import categories.SmokeTestFilter;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

@RunWith(JUnitParamsRunner.class)
public class RegistrationTest2 extends BaseTest {
    @Category(SmokeTestFilter.class)
    @Test
    @Parameters({
            "12,qqq,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,qqq,123456qwerty,Username must be at least 3 characters.;You must provide a valid email address."
    })
    public void registrationErrors(String login, String email, String passWord, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(passWord)
                .checkErrorsMessages(errors)
                ;
    }
}
