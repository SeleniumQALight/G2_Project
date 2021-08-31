package registrationPage;

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
public class RegistrationPage extends BaseTest {

    @Test
    @Parameters({
            "12,nnn,345,Username must be at least 3 characters.;You must provide a valid email address.;Password must be at least 12 characters.",
            "12,nnn,12345,Username must be at least 3 characters.;You must provide a valid email address."
    })
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")

    public void restrationErrors(String login, String email, String password, String errors){
        loginPage.openLoginPage();
        loginPage
                .enterLoginInRegistration(login)
                .enterEmailInRegistration(email)
                .enterPassWordRegistration(password)
                .checkErrorsMessages(errors)
                ;

    }
}


